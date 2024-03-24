plugins {
    id("com.mikhailovskii.kmp.module")
    id("maven-publish")
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

kmpPublishingToMaven {
    url = "https://maven.pkg.github.com/SergeiMikhailovskii/kmp-app-review"
}
