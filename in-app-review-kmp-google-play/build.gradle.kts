plugins {
    id("com.mikhailovskii.kmp.module")
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
