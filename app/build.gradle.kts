plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    val nameId = "com.movtery.zalithplugin.renderer"

    namespace = nameId
    compileSdk = 34

    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = "ZALITH_RENDERER_PLUGIN"
            storeFile = file("plugin-key.jks")
            storePassword = "ZALITH_RENDERER_PLUGIN"
        }
    }

    defaultConfig {
        applicationId = nameId
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        configureEach {
            resValue("string","app_name","LTW Renderer")
            applicationIdSuffix = ".ltw"

            manifestPlaceholders["des"] = "LTW Renderer (OpenGL 4.6, 1.17+)"
            manifestPlaceholders["renderer"] = "LTW:libltw.so:libltw.so"

            manifestPlaceholders["pojavEnv"] = mutableMapOf<String,String>().apply {
                put("LIBGL_ES", "3")
                put("POJAV_RENDERER", "opengles3_ltw")
            }.run {
                var env = ""
                forEach { (key, value) ->
                    env += "$key=$value:"
                }
                env.dropLast(1)
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(files("libs/ltw-release.aar"))
}
