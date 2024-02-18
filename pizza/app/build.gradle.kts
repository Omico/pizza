plugins {
    id("pizza.android.application")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "me.omico.pizza"
    defaultConfig {
        applicationId = "me.omico.pizza"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(androidx.activity.ktx)
    implementation(androidx.navigation.fragment.ktx)
    implementation(androidx.navigation.ui.ktx)
    implementation(material)
}
