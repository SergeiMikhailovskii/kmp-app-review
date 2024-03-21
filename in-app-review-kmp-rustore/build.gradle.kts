plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

version = System.getenv("LIBRARY_VERSION") ?: libs.versions.pluginVersion.get()

kotlin {
    androidTarget {
        compilations.all { publishLibraryVariants("release", "debug") }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "in-app-review-kmp-rustore"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.rustore.review)
        }
        commonMain.dependencies {
            api(projects.inAppReviewKmp)
        }
    }
}

android {
    namespace = "com.mikhailovskii.inappreview"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
}

publishing {
    publications {
        matching {
            return@matching it.name in listOf("iosArm64", "iosX64", "kotlinMultiplatform")
        }.all {
            tasks.withType<AbstractPublishToMaven>()
                .matching { it.publication == this@all }
                .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
        }
    }
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/SergeiMikhailovskii/kmp-app-review")
            credentials {
                username = System.getenv("GITHUB_USER")
                password = System.getenv("GITHUB_API_KEY")
            }
        }
    }
}

tasks.register("buildAndPublish", DefaultTask::class) {
    dependsOn("build")
    dependsOn("publish")
    tasks.findByPath("publish")?.mustRunAfter("build")
}
