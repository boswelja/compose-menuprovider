plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.compose.multiplatform) apply false

    alias(libs.plugins.dokka)
}

repositories {
    mavenCentral()
}
