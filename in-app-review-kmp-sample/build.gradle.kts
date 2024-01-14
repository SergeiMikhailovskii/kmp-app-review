plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
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
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "12.0"
        framework {
            baseName = "inAppReviewKMP-shared"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity)
        }
        commonMain.dependencies {
            implementation(projects.inAppReviewKmp)
            implementation(libs.coroutines.core)
        }
    }
}

android {
    namespace = "com.mikhailovskii.inappreviewkmp_shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}