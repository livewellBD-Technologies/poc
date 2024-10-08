
plugins {
    alias(libs.plugins.lwbdapp.android.library)
    alias(libs.plugins.lwbdapp.android.library.compose)
    alias(libs.plugins.lwbdapp.android.library.jacoco)
}

android {
    namespace = "com.lwbd.lwbdpoc.core.ui"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.androidx.metrics)

    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.navigation.common.ktx)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    androidTestImplementation(projects.core.testing)
}
