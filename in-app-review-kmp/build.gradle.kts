plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

version = System.getenv("LIBRARY_VERSION") ?: libs.versions.pluginVersion.get()

kotlin {
    androidTarget { publishLibraryVariants("release", "debug") }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "in-app-review"
        homepage = "https://github.com/SergeiMikhailovskii/kmp-app-review"
        version = project.version.toString()
        ios.deploymentTarget = "12.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "inAppReviewKMP"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity)
            implementation(libs.androidx.fragment)
        }
        commonMain.dependencies {
            api(libs.coroutines.core)
        }
    }
}

android {
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
