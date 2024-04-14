import com.android.build.gradle.LibraryExtension
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.DefaultTask
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

private class KMPModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(extensions.getPluginId("androidLibrary"))
                apply(extensions.getPluginId("kotlinMultiplatform"))
                apply(extensions.getPluginId("mavenPublish"))
            }
            group = "io.github.sergeimikhailovskii"
            version = System.getenv("LIBRARY_VERSION") ?: extensions.getVersion("pluginVersion")
            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget { publishLibraryVariants("release", "debug") }
                iosX64()
                iosArm64()
                iosSimulatorArm64()
                applyDefaultHierarchyTemplate()
                jvmToolchain(17)
            }
            extensions.configure<LibraryExtension> {
                namespace = "com.mikhailovskii.inappreview"
                compileSdk = 34
                defaultConfig {
                    minSdk = 21
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }
            extensions.configure<PublishingExtension> {
                publications.withType<MavenPublication> {
                    pom {
                        name.set("kmp-in-app-review")
                        description.set("Library that allows to launch in app (or in market) review from the KMP shared code")
                        url.set("https://github.com/SergeiMikhailovskii/kmp-app-review")

                        licenses {
                            license {
                                name.set("The Apache License, Version 2.0")
                                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            }
                        }

                        developers {
                            developer {
                                id.set("SergeiMikhailovskii")
                                name.set("Sergei Mikhailovskii")
                                email.set("mikhailovskii.s@gmail.com")
                            }
                        }

                        scm {
                            url.set("https://github.com/SergeiMikhailovskii/kmp-app-review")
                            connection.set("scm:git:git://github.com/SergeiMikhailovskii/kmp-app-review.git")
                            developerConnection.set("scm:git:git://github.com/SergeiMikhailovskii/kmp-app-review.git")
                        }
                    }
                }
            }
            extensions.configure<MavenPublishBaseExtension> {
                publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
                signAllPublications()
            }
            tasks.register("buildAndPublish", DefaultTask::class) {
                dependsOn("build")
                dependsOn("publish")
                tasks.findByPath("publish")?.mustRunAfter("build")
            }
        }
    }
}