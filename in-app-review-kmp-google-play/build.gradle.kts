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
        commonTest.dependencies {
            implementation(libs.kotlinx.coroutines.test)
            implementation(kotlin("test"))
        }
        androidInstrumentedTest {
            dependsOn(commonTest.get())
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.junit.ktx)
                implementation(libs.androidx.core.ktx)
            }
        }
    }
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
