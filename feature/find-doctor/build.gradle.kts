plugins {
    alias(libs.plugins.lwbdapp.android.feature)
    alias(libs.plugins.lwbdapp.android.library.compose)
    alias(libs.plugins.lwbdapp.android.library.jacoco)
}

android {
    namespace = "com.lwbd.lwbdpoc.feature.finddoctor"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.data)

    testImplementation(projects.core.testing)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    androidTestImplementation(projects.core.testing)
}
