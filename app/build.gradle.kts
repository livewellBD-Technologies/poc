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
    implementation(projects.feature.home)
    implementation(projects.feature.findDoctor)
    implementation(projects.feature.menu)
    implementation(projects.feature.prescriptions)
    implementation(projects.feature.appointmentBooking)

    implementation(projects.core.base)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    implementation(projects.core.ui)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.compose.material3.adaptive.android)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.window.core)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.ui.test.desktop)

    ksp(libs.hilt.compiler)

    kspTest(libs.hilt.compiler)

    testImplementation(libs.hilt.android.testing)

    testFreeImplementation(libs.roborazzi)

    androidTestImplementation(kotlin("test"))
    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(projects.uiTestHiltManifest)
}
dependencyGuard {
    configuration("freeReleaseRuntimeClasspath")
}