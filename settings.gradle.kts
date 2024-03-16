enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "InAppReview"
include(":androidApp", ":in-app-review-kmp", ":in-app-review-kmp-sample")
include(":in-app-review-kmp-google-play")
