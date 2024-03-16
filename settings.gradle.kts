enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://artifactory-external.vkpartner.ru/artifactory/maven")
        maven("https://androidx.dev/storage/compose-compiler/repository/")
    }
}

rootProject.name = "InAppReview"

include(
    ":androidApp",
    ":in-app-review-kmp",
    ":in-app-review-kmp-sample",
    ":in-app-review-kmp-google-play",
    ":in-app-review-kmp-rustore"
)
