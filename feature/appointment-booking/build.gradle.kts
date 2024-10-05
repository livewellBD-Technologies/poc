plugins {
    alias(libs.plugins.lwbdapp.android.feature)
    alias(libs.plugins.lwbdapp.android.library.compose)
    alias(libs.plugins.lwbdapp.android.library.jacoco)
}

android {
    namespace = "com.lwbd.lwbdpoc.apointment_booking"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(projects.core.data)
//    implementation(projects.core.domain)
    implementation(libs.androidx.navigation.compose)
    implementation(projects.core.base)


    testImplementation(projects.core.testing)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.ui.test.desktop)
    testImplementation(libs.androidx.ui.test.junit4.android)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    androidTestImplementation(libs.androidx.ui.test.junit4.android)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.core.ktx) // Or latest version
    androidTestImplementation(libs.androidx.test.ext.junit.ktx)
}