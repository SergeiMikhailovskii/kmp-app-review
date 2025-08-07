plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.kotlinCompose).apply(false)
    alias(libs.plugins.mavenPublish).apply(false)
}

tasks.register("buildAndPublish", DefaultTask::class) {
    dependsOn(":in-app-review-kmp:buildAndPublish")
    dependsOn(":in-app-review-kmp-google-play:buildAndPublish")
    dependsOn(":in-app-review-kmp-rustore:buildAndPublish")
    tasks.findByPath(":in-app-review-kmp-google-play:buildAndPublish")?.mustRunAfter(":in-app-review-kmp:build")
    tasks.findByPath(":in-app-review-kmp-rustore:buildAndPublish")?.mustRunAfter(":in-app-review-kmp:build")
}
