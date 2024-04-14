plugins {
    id("com.mikhailovskii.kmp.module")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.rustore.review)
        }
        commonMain.dependencies {
            api(projects.inAppReviewKmp)
        }
    }
}
