

package com.lwbd.lwbdpoc

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class LwbdBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
