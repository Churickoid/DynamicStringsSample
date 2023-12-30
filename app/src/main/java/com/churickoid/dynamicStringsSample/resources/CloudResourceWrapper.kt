package ru.mail.cloud.resources

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.icu.text.PluralRules
import android.os.Build
import android.util.DisplayMetrics
import com.churickoid.dynamicStringsSample.RemoteParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.mail.cloud.resources.analytics.ResourcesAnalytics
import ru.mail.cloud.resources.cache.initializer.JsonResourcesParser
import ru.mail.cloud.resources.models.LanguageName
import ru.mail.cloud.resources.models.ResourceName
import ru.mail.cloud.resources.replacer.ResourcesReplacer
import java.util.Locale

class CloudResourceWrapper(
    resources: Resources,
    private val replacer: ResourcesReplacer,
    private val analytics: ResourcesAnalytics,
    private val parser: JsonResourcesParser,
    private val applicationScope: CoroutineScope,
) : Resources(resources.assets, resources.displayMetrics, resources.configuration) {

    private val sync = Any()

    init {
        applicationScope.launch {
            launch { subscribeToDrawableReplacements() }
            launch { subscribeToStringReplacements() }
        }
    }

    private val locale: Locale
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.locales[0]
        } else {
            configuration.locale
        }

    private val language: LanguageName
        get() = LanguageName(locale.language)

    private var _pluralRule: PluralRules? = null

    private val pluralRule: PluralRules
        get() {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                throw IllegalStateException("You are not able to use this before Android N")
            }

            if (_pluralRule != null) {
                return _pluralRule!!
            }

            synchronized(sync) {
                if (_pluralRule == null) {
                    _pluralRule = PluralRules.forLocale(locale)
                }
                return _pluralRule!!
            }
        }

    override fun getString(id: Int): String {
        val newString = replacer.getString(language, ResourceName(getResourceName(id)))
        val defaultString = super.getString(id)
        return if (newString == null) {
            defaultString
        } else {
            analytics.onStringReplaced(id, defaultString, newString)
            newString
        }
    }

    override fun getQuantityString(id: Int, quantity: Int): String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return super.getQuantityString(id, quantity)
        }

        val newString = replacer.getQuantityString(
            language,
            pluralRule,
            ResourceName(getResourceName(id)),
            quantity
        )
        val defaultString = super.getQuantityString(id, quantity)
        return if (newString == null) {
            defaultString
        } else {
            analytics.onQuantityStringReplaced(id, quantity, defaultString, newString)
            newString
        }
    }

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("getDrawable(id, null)")
    )
    override fun getDrawable(id: Int): Drawable {
        return getDrawable(id, null)
    }

    override fun getDrawable(id: Int, theme: Theme?): Drawable {
        val drawable = replacer.getDrawable(
            language,
            ResourceName(getResourceName(id)),
            displayMetrics.density
        ) { super.getDrawable(id, theme) }
        return drawable ?: super.getDrawable(id, theme)
    }

    override fun updateConfiguration(config: Configuration?, metrics: DisplayMetrics?) {
        super.updateConfiguration(config, metrics)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            synchronized(sync) {
                if (_pluralRule != null) {
                    _pluralRule = PluralRules.forLocale(locale)
                }
            }
        }
    }

    private suspend fun subscribeToStringReplacements() {
        parser.fillStringResourcesMemcache(RemoteParams.drawableResReplacements)
    }

    private suspend fun subscribeToDrawableReplacements() {
        parser.fillDrawableResourcesMemcache(RemoteParams.drawableResReplacements)

    }
}
