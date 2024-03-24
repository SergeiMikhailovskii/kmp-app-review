plugins {
    id("com.mikhailovskii.kmp.module")
    id("maven-publish")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.review.ktx)
            implementation(libs.coroutines.play.services)
        }
        commonMain.dependencies {
            api(projects.inAppReviewKmp)
        }
    }
}

applyPublishing()
