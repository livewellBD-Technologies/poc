
plugins {
    alias(libs.plugins.lwbdapp.jvm.library)
    alias(libs.plugins.lwbdapp.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)

}