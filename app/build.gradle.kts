import com.lwbd.lwbdpoc.LwbdBuildType

plugins {
    alias(libs.plugins.lwbdapp.android.application)
    alias(libs.plugins.lwbdapp.android.application.compose)
    alias(libs.plugins.lwbdapp.android.application.flavors)
    alias(libs.plugins.lwbdapp.android.application.jacoco)

    alias(libs.plugins.lwbdapp.hilt)

    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.lwbd.lwbdpoc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lwbd.lwbdpoc"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = LwbdBuildType.DEBUG.applicationIdSuffix
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = true
            applicationIdSuffix = LwbdBuildType.RELEASE.applicationIdSuffix
            signingConfig = signingConfigs.getByName("debug")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
dependencyGuard {
    configuration("freeReleaseRuntimeClasspath")
}