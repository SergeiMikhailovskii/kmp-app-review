plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget { publishLibraryVariants("release", "debug") }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "12.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "inAppReviewKMP"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.review.ktx)
            implementation(libs.coroutines.play.services)
        }
        commonMain.dependencies {
        }
    }
}

android {
    namespace = "com.mikhailovskii.inappreview"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}