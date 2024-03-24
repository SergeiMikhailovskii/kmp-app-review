plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("com.mikhailovskii.kmp.KMPModuleConventionPlugin") {
            id = "com.mikhailovskii.kmp.module"
            implementationClass = "KMPModuleConventionPlugin"
        }
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
}