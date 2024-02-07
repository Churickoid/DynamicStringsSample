package com.churickoid.dynamicStringsSample

object RemoteParams {

    val isStringResOverrideEnabled = true

    val stringResReplacements = """
            [
              {
                "type": "string",
                "enabled": true,
                "name": "ru.mail.cloud:string/sidebar_shared",
                "replacements": [
                  {
                    "locale": "en",
                    "value": "override string"
                  },
                  {
                    "locale": "ru",
                    "value": "подменненая строка"
                  }
                ]
              }
            ]
            """

    val isDrawableResOverrideEnabled = true

    val drawableResReplacements = """
          [
  {
    "enabled": true,
    "name": "com.churickoid.dynamicStringsSample:drawable/ic_launcher_background",
    "replacements": [
      {
        "density": "mdpi",
        "url": "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/SVG_Simple_Icon.svg/768px-SVG_Simple_Icon.svg.png?20180526161030"
    },
      {
        "density": "hdpi",
        "url": "https://upload.wikimedia.org/wikipedia/commons/4/4f/SVG_Logo.svg"  },
      {
        "density": "xhdpi",
        "url": "https://upload.wikimedia.org/wikipedia/commons/4/4f/SVG_Logo.svg"  },
      {
        "density": "xxhdpi",
        "url": "https://upload.wikimedia.org/wikipedia/commons/4/4f/SVG_Logo.svg" },
      {
        "density": "xxxhdpi",
        "url": "https://upload.wikimedia.org/wikipedia/commons/4/4f/SVG_Logo.svg" }
    ]
  },
  {
    "enabled": true,
    "name": "com.churickoid.dynamicStringsSample:drawable/ic_about_logo",
    "replacements": [
      {
        "density": "xhdpi",
        "url": "https://sun9-70.userapi.com/impg/33SdpyV_RMubrhclornbs9-qzfyuj-pu8RyJng/Q_v_oNxD8Q8.jpg?size=500x500&quality=96&sign=7b1d1f3c98fe6cb7a979aadd5dca47e0&type=album"
      }
    ]
  },
  {
    "enabled": true,
    "name": "com.churickoid.dynamicStringsSample:drawable/first_klass",
    "replacements": [
      {
        "density": "mdpi",
        "url": "https://sun9-15.userapi.com/impg/v9ZEeLwWgoP35w7nAuzn5WNZ1bwISXKcZsK2qg/WayvMFlagMk.jpg?size=250x250&quality=96&sign=271e9533c70f26688333b79aaec10fc3&type=album"
      },
      {
        "density": "hdpi",
        "url": "https://sun9-30.userapi.com/impg/0rqsic_cdhtuAd-jQsPM4DTh4-CdSKGsvpGqIw/g2172UvbbOU.jpg?size=375x375&quality=96&sign=3861e3aac30c9c23bae87c834b58886a&type=album"
      },
      {
        "density": "xhdpi",
        "url": "https://sun9-70.userapi.com/impg/33SdpyV_RMubrhclornbs9-qzfyuj-pu8RyJng/Q_v_oNxD8Q8.jpg?size=500x500&quality=96&sign=7b1d1f3c98fe6cb7a979aadd5dca47e0&type=album"
      },
      {
        "density": "xxhdpi",
        "url": "https://sun9-57.userapi.com/impg/G-rz9zmzUVFDsQUP5dLlwRyfYSsi5163A-3i4A/niFsuJ5R2ug.jpg?size=750x750&quality=96&sign=1c1df0710dba5653b9a0714f26d2c532&type=album"
      },
      {
        "density": "xxxhdpi",
        "url": "https://sun9-39.userapi.com/impg/9ysfjhDg_ci07hSbPV81PyrSCFQXytJre7dB1Q/m42-KKX-kCM.jpg?size=1000x1000&quality=96&sign=d6c2669a11ff6c7584550e2af5177620&type=album"
      }
    ]
  },
  {
    "enabled": true,
    "name": "com.churickoid.dynamicStringsSample:drawable/ic_blue_invi",
    "replacements": [
      {
        "density": "mdpi",
        "url": "https://sun9-15.userapi.com/impg/v9ZEeLwWgoP35w7nAuzn5WNZ1bwISXKcZsK2qg/WayvMFlagMk.jpg?size=250x250&quality=96&sign=271e9533c70f26688333b79aaec10fc3&type=album"
      },
      {
        "density": "hdpi",
        "url": "https://sun9-30.userapi.com/impg/0rqsic_cdhtuAd-jQsPM4DTh4-CdSKGsvpGqIw/g2172UvbbOU.jpg?size=375x375&quality=96&sign=3861e3aac30c9c23bae87c834b58886a&type=album"
      },
      {
        "density": "xhdpi",
        "url": "https://sun9-70.userapi.com/impg/33SdpyV_RMubrhclornbs9-qzfyuj-pu8RyJng/Q_v_oNxD8Q8.jpg?size=500x500&quality=96&sign=7b1d1f3c98fe6cb7a979aadd5dca47e0&type=album"
      },
      {
        "density": "xxhdpi",
        "url": "https://sun9-57.userapi.com/impg/G-rz9zmzUVFDsQUP5dLlwRyfYSsi5163A-3i4A/niFsuJ5R2ug.jpg?size=750x750&quality=96&sign=1c1df0710dba5653b9a0714f26d2c532&type=album"
      },
      {
        "density": "xxxhdpi",
        "url": "https://sun9-39.userapi.com/impg/9ysfjhDg_ci07hSbPV81PyrSCFQXytJre7dB1Q/m42-KKX-kCM.jpg?size=1000x1000&quality=96&sign=d6c2669a11ff6c7584550e2af5177620&type=album"
      }
    ]
  }
]
        """.trimIndent()
}