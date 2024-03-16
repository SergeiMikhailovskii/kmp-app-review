plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
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
