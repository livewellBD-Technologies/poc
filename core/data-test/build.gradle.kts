
plugins {
    alias(libs.plugins.lwbdapp.android.library)
    alias(libs.plugins.lwbdapp.hilt)
}

android {
    namespace = "com.lwbd.lwbdpoc.core.data.test"

    buildFeatures {
        buildConfig = true
    }
}



dependencies {
    api(projects.core.data)

    implementation(libs.hilt.android.testing)
}
