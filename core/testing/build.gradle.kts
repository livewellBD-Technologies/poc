
plugins {
    alias(libs.plugins.lwbdapp.android.library)
    alias(libs.plugins.lwbdapp.hilt)
}

android {
    namespace = "com.lwbd.lwbdpoc.core.testing"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.kotlinx.coroutines.test)
    api(projects.core.common)
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.datetime)

    androidTestImplementation(libs.androidx.test.core.ktx)
}
