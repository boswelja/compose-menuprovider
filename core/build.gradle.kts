import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.detekt)

    id("com.boswelja.publish")
}

group = findProperty("group")!!
version = findProperty("version")!!

android {
    namespace = "com.boswelja.menuprovider"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        sarifReport = true
        htmlReport = false
    }

    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
    }
}

repositories {
    google {
        content {
            includeGroupByRegex("com\\.android.*")
            includeGroupByRegex("com\\.google.*")
            includeGroupByRegex("androidx.*")
        }
    }
    mavenCentral()
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvmToolchain(21)

    explicitApi()

    // Android
    androidTarget {
        publishLibraryVariants("release")
    }

    // Apple
    macosX64()
    macosArm64()
    iosX64()
    iosArm64()

    // JVM
    jvm()

    // WASM
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.animation)
            }
        }
        commonTest {
            dependencies {
                // Since MenuItems require icons, we need to import some to test with
                implementation(compose.materialIconsExtended)
                implementation(libs.kotlin.test)
            }
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$rootDir/config/detekt.yml")
    basePath = rootDir.absolutePath
}

publish {
    description = "An AndroidX MenuHost & MenuProvider-like API for Jetpack Compose!"
    repositoryUrl = "https://github.com/boswelja/compose-menuprovider"
    license = "MIT"
}
