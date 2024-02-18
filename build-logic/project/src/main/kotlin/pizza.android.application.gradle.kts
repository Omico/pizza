import me.omico.gradle.project.configureCommonAndroid
import me.omico.gradle.project.generateVersionCode

plugins {
    kotlin("android")
    id("com.android.application")
}

configureCommonAndroid(
    domain = "me.omico",
    compileSdk = 34,
    minSdk = 30,
)

android {
    defaultConfig {
        versionCode = generateVersionCode(2024, 1, 1)
        versionName = version.toString()
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
