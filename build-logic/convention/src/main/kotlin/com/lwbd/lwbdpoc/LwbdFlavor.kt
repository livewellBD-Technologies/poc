package com.lwbd.lwbdpoc

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class FlavorDimension {
    contentType
}

@Suppress("EnumEntryName")
enum class LwbdFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null, val backendUrl: String) {
//    demo(FlavorDimension.contentType, applicationIdSuffix = ".demo", backendUrl = "https://server.livewellbd.com/api/v1.0/poc/"),
    free(FlavorDimension.contentType, applicationIdSuffix = ".free", backendUrl = "https://server.livewellbd.com/api/v1.0/poc/"),
    paid(FlavorDimension.contentType, applicationIdSuffix = ".paid", backendUrl = "https://server.livewellbd.com/api/v1.0/poc/"),
    premium(FlavorDimension.contentType, applicationIdSuffix = ".premium", backendUrl = "https://server.livewellbd.com/api/v1.0/poc/"),
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: LwbdFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name
        productFlavors {
            LwbdFlavor.values().forEach {
                create(it.name).apply {
                    dimension = it.dimension.name
                    flavorConfigurationBlock(this, it)
                    if (commonExtension is ApplicationExtension) {
                        applyApplicationIdSuffixIfNeeded(it)
                    }
                    addBackendUrlToBuildConfig(it)
                }
            }
        }
    }
}

// Helper function to handle applicationIdSuffix assignment
private fun ProductFlavor.applyApplicationIdSuffixIfNeeded(flavor: LwbdFlavor) {
    if (this is ApplicationProductFlavor && flavor.applicationIdSuffix != null) {
        applicationIdSuffix = flavor.applicationIdSuffix
    }
}

// Helper function to add BACKEND_URL to BuildConfig
private fun ProductFlavor.addBackendUrlToBuildConfig(flavor: LwbdFlavor) {
    buildConfigField("String", "BACKEND_URL", "\"${flavor.backendUrl}\"")
}