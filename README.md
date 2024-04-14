# kmp-app-review

[![Publish new version](https://github.com/SergeiMikhailovskii/kmp-app-review/actions/workflows/publish-new-version.yml/badge.svg?branch=master)](https://github.com/SergeiMikhailovskii/kmp-app-review/actions/workflows/publish-new-version.yml)
![GitHub Tag](https://img.shields.io/github/v/tag/SergeiMikhailovskii/kmp-app-review)

## What is it?
Library that allows to launch in app (or in market) review from the KMP shared code

## Which platforms are supported?
Android and iOS

## How to integrate?
1) Add the dependency you need into `commonMain`:
```kotlin
implementation("io.github.sergeimikhailovskii:in-app-review-kmp:$latest_tag") // Amazon, App Gallery, Galaxy Store
implementation("io.github.sergeimikhailovskii:in-app-review-kmp-google-play:$latest_tag") // Google Play + Amazon, App Gallery, Galaxy Store
implementation("io.github.sergeimikhailovskii:in-app-review-kmp-rustore:$latest_tag") // RuStore + Amazon, App Gallery, Galaxy Store
```

2) Create an instance of class that implements `InAppReviewDelegate` interface.
   Now library supports 3 implementations (depends on the market u need): 
      - `AmazonInAppReviewManager`
      - `AppGalleryInAppReviewManager`
      - `AppStoreInAppReviewManager`
      - `GalaxyStoreInAppReviewManager`
      - `GooglePlayInAppReviewManager`
      - `RuStoreInAppReviewManager`

3) To launch in-app review call
   ```kotlin
   fun requestInAppReview(): Flow<ReviewCode>
   ```

  By listening the returned flow events u can receive the [ResultCode](https://github.com/SergeiMikhailovskii/kmp-app-review/blob/master/in-app-review-kmp/src/commonMain/kotlin/com/mikhailovskii/inappreview/ReviewCode.kt) which indicates the result the process finished with
   
   - Amazon opens the app page in market
   - For App Gallery used [in-app-comments function](https://developer.huawei.com/consumer/en/doc/AppGallery-connect-Guides/agc-comments-develop-0000001062858332)
   - For App Store used [StoreKit SKStoreReviewViewController](https://developer.apple.com/documentation/storekit/skstorereviewcontroller)
   - For Galaxy Store used [Galaxy Store review broadcast](https://developer.samsung.com/galaxy-store/customer-review/galaxy-store-review-broadcast.html)
   - For Google Play used [in-app-review api](https://developer.android.com/guide/playcore/in-app-review)
   - For RuStore used [in-app-review-api](https://www.rustore.ru/help/sdk/reviews-ratings/kotlin-java/2-0-0)
  
4) To launch in-market review call
   ```kotlin
   fun requestInMarketReview(): Flow<ReviewCode>
   ```

### Note 1
For App Gallery and Galaxy Store need to call
```kotlin
fun init()
```
this method registers activity result listener so u should invoke it before the fragment or activity is created

### Note 2
RuStore's impl has minSdk=26, other dependencies have minSdk=21

## Breaking changes in the version 3.0
1) Since new artifacts versions are published to the MavenCentral instead of Github Maven, the `group id` was changed:
```diff
dependencies {
+  implementation("io.github.sergeimikhailovskii:in-app-review-kmp:$latest_tag") // Amazon, App Gallery, Galaxy Store
-  implementation("com.mikhailovskii.kmp:in-app-review-kmp:$latest_tag") // Amazon, App Gallery, Galaxy Store
+  implementation("io.github.sergeimikhailovskii:in-app-review-kmp-google-play:$latest_tag") // Google Play + Amazon, App Gallery, Galaxy Store
-  implementation("com.mikhailovskii.kmp:in-app-review-kmp-google-play:$latest_tag") // Google Play + Amazon, App Gallery, Galaxy Store
+  implementation("io.github.sergeimikhailovskii:in-app-review-kmp-rustore:$latest_tag")
-  implementation("com.mikhailovskii.kmp:in-app-review-kmp-rustore:$latest_tag") // RuStore + Amazon, App Gallery, Galaxy Store
}
```
2) Separate repository can be deleted
```diff
dependencyResolutionManagement {
    repositories {
+        mavenCentral()
-        maven {
-            url = uri("https://maven.pkg.github.com/SergeiMikhailovskii/kmp-app-review")
-            credentials {
-               username = System.getenv("GITHUB_USER")
-               password = System.getenv("GITHUB_API_KEY")
-            }
-        }
    }
}
```

## Breaking changes in the version 2.0

1) Google Play's implementation was moved from the in-app-review-kmp module to the in-app-review-kmp-google-play module.
Reason is the following - starting from the version 2.0 library supports more stores, stores that use own sdk for the
review process are moved to separate modules to avoid adding unnecessary dependencies for stores that don't need any SDK
like AppGallery, Galaxy Store and Amazon Store.
2) Deleted
```kotlin
fun getDefaultReviewManager(params: DefaultInAppReviewInitParams): InAppReviewDelegate
```

## Here u can see the sample integrations:
- [iOS UI part](https://github.com/SergeiMikhailovskii/kmp-app-review/blob/master/iosApp/iosApp/ContentView.swift)
- [Android UI part](https://github.com/SergeiMikhailovskii/kmp-app-review/blob/master/androidApp/src/main/java/com/mikhailovskii/inappreview/android/MainActivity.kt)
- [Shared part](https://github.com/SergeiMikhailovskii/kmp-app-review/blob/master/in-app-review-kmp-sample/src/commonMain/kotlin/com/mikhailovskii/inappreviewkmp_sample/ReviewComponent.kt)

## What to do if I need to support some other market?
Just create your own implementation of the `InAppReviewDelegate` interface.
There are no differences between your own implementation and the implementation out of the library.
Main idea of the library is to provide the vision how the review process can be shared.
Also it aims to collect as much implementations as it can be, so PRs are welcome :) 
