import com.android.build.gradle.LibraryExtension
import org.gradle.api.DefaultTask
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
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
                apply("maven-publish")
            }
            group = "com.mikhailovskii.kmp"
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
                publications {
                    matching {
                        return@matching it.name in listOf(
                            "iosArm64",
                            "iosX64",
                            "kotlinMultiplatform"
                        )
                    }.all {
                        tasks.withType<AbstractPublishToMaven>()
                            .matching { it.publication == this@all }
                            .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
                    }
                }
            }
            tasks.register("buildAndPublish", DefaultTask::class) {
                dependsOn("build")
                dependsOn("publish")
                tasks.findByPath("publish")?.mustRunAfter("build")
            }
        }
    }
}