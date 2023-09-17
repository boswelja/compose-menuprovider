plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)

    alias(libs.plugins.detekt)

    id("maven-publish")
}

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

kotlin {
    jvmToolchain(17)
    explicitApi()

    androidTarget()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.animation)
            }
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$rootDir/config/detekt.yml")
    basePath = rootDir.absolutePath
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.boswelja.menuprovider"
            artifactId = "core"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        if (System.getenv("PUBLISHING") == "true") {
            maven("https://maven.pkg.github.com/boswelja/Compose-MenuProvider") {
                val githubUsername: String? by project.properties
                val githubToken: String? by project.properties
                name = "github"
                credentials {
                    username = githubUsername
                    password = githubToken
                }
            }
            maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
                val ossrhUsername: String? by project
                val ossrhPassword: String? by project
                name = "oss"
                credentials {
                    username = ossrhUsername
                    password = ossrhPassword
                }
            }
        }
    }
}
