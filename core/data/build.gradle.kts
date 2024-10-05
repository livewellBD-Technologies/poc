
plugins {
    alias(libs.plugins.lwbdapp.android.library)
    alias(libs.plugins.lwbdapp.android.library.jacoco)
    alias(libs.plugins.lwbdapp.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.lwbd.lwbdpoc.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.network)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
    testImplementation(projects.core.testing)
}
