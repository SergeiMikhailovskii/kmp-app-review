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

publishing {
    publications {
        matching {
            return@matching it.name in listOf("iosArm64", "iosX64", "kotlinMultiplatform")
        }.all {
            tasks.withType<AbstractPublishToMaven>()
                .matching { it.publication == this@all }
                .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
        }
    }
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/SergeiMikhailovskii/kmp-app-review")
            credentials {
                username = System.getenv("GITHUB_USER")
                password = System.getenv("GITHUB_API_KEY")
            }
        }
    }
}

tasks.register("buildAndPublish", DefaultTask::class) {
    dependsOn("build")
    dependsOn("publish")
    tasks.findByPath("publish")?.mustRunAfter("build")
}
