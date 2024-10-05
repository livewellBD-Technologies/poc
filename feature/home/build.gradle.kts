plugins {
    alias(libs.plugins.lwbdapp.android.feature)
    alias(libs.plugins.lwbdapp.android.library.compose)
    alias(libs.plugins.lwbdapp.android.library.jacoco)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.lwbd.lwbdpoc.feature.home"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(projects.core.data)
    implementation(projects.core.base)
    
    testImplementation(projects.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.hilt.android.testing)

}
