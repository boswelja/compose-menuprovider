plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)

    alias(libs.plugins.detekt)

    id("maven-publish")
}

group = "io.github.boswelja.menuprovider"
version = "1.0"

android {
    namespace = "com.boswelja.menuprovider.material3"

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

    androidTarget {
        publishLibraryVariants("release")
    }

    sourceSets {
        commonMain {
            dependencies {
                api(project(":core"))
                implementation(compose.material3)
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

    publications.withType<MavenPublication> {
        pom {
            description = "An AndroidX MenuHost & MenuProvider-like API for Jetpack Compose!"
            url = "https://github.com/boswelja/compose-menuprovider"
            licenses {
                license {
                    name = "MIT"
                    url = "https://github.com/boswelja/compose-menuprovider/blob/main/LICENSE"
                }
            }
            developers {
                developer {
                    id = "boswelja"
                    name = "Jack Boswell (boswelja)"
                    email = "boswelja@outlook.com"
                    url = "https://github.com/boswelja"
                }
            }
            scm {
                connection.set("scm:git:github.com/boswelja/compose-menuprovider.git")
                developerConnection.set("scm:git:ssh://github.com/boswelja/compose-menuprovider.git")
                url.set("https://github.com/boswelja/compose-menuprovider")
            }
        }
    }
}
