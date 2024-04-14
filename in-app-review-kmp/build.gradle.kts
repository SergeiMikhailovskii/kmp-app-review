plugins {
    id("com.mikhailovskii.kmp.module")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity)
            implementation(libs.androidx.fragment)
        }
        commonMain.dependencies {
            api(libs.coroutines.core)
        }
    }
}
