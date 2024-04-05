plugins {
    id("maven-publish")
    id("signing")
}

interface CustomPublishingExtension {
    val description: Property<String>
    val repositoryUrl: Property<String>
    val license: Property<String>
}

val extension = project.extensions.create<CustomPublishingExtension>("publish")

group = findProperty("group")!!
version = findProperty("version")!!

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

afterEvaluate {
    publishing {
        repositories {
            if (System.getenv("PUBLISHING") == "true") {
                maven("https://maven.pkg.github.com/boswelja/${extension.repositoryUrl.get().split("/").last()}") {
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
                name = project.name
                description = extension.description
                url = extension.repositoryUrl.get()
                licenses {
                    license {
                        name = extension.license.get()
                        url = "${extension.repositoryUrl}/blob/main/LICENSE"
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
                    url.set(extension.repositoryUrl.get())
                }
            }
        }
    }
}
