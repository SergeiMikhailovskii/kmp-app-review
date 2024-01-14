plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
}

tasks.register("buildAndPublish", DefaultTask::class) {
    dependsOn(":inAppReviewKMP:build")
    dependsOn(":inAppReviewKMP:publish")
    tasks.findByPath(":inAppReviewKMP:publish")?.mustRunAfter(":inAppReviewKMP:build")
}
