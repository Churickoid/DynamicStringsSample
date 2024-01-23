package com.churickoid.dynamicStringsSample.screen

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.churickoid.dynamicStringsSample.R
import com.churickoid.dynamicStringsSample.screen.ui.theme.CloudTheme
import ru.mail.cloud.resources.CloudResourceWrapper

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloudTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun getResources(): Resources {
        val res = applicationContext.resources
        val check = applicationContext.resources is CloudResourceWrapper
        return res
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.first_klass),
        contentDescription = "test"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CloudTheme {
        Greeting("Android")
    }
}


