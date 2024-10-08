
plugins {
    alias(libs.plugins.lwbdapp.android.library)
    alias(libs.plugins.lwbdapp.android.library.compose)
    alias(libs.plugins.lwbdapp.android.library.jacoco)
    alias(libs.plugins.roborazzi)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.lwbd.lwbdpoc.core.designsystem"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
//    lintPublish(projects.lint)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.material3.navigationSuite)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.util)
    api(libs.material)

    implementation(libs.coil.kt.compose)

    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.androidx.compose.ui.test.manifest)
    
    testImplementation(libs.hilt.android.testing)
//    testImplementation(libs.robolectric)
//    testImplementation(projects.core.screenshotTesting)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
}
