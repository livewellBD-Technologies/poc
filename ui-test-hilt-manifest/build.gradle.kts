plugins {
    alias(libs.plugins.lwbdapp.android.library)
    alias(libs.plugins.lwbdapp.hilt)
}

android {
    namespace = "com.lwbd.lwbdpoc.uitesthiltmanifest"
    buildFeatures {
        buildConfig = true
    }
}
