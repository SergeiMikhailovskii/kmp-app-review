plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
}

tasks.register("buildAndPublish", DefaultTask::class) {
    dependsOn(":in-app-review-kmp:build")
    dependsOn(":in-app-review-kmp:publish")
    tasks.findByPath(":in-app-review-kmp:publish")?.mustRunAfter(":in-app-review-kmp:build")
}
