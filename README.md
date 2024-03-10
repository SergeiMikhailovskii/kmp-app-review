# kmp-app-review

[![Publish new version](https://github.com/SergeiMikhailovskii/kmp-app-review/actions/workflows/publish-new-version.yml/badge.svg?branch=master)](https://github.com/SergeiMikhailovskii/kmp-app-review/actions/workflows/publish-new-version.yml)
![GitHub Tag](https://img.shields.io/github/v/tag/SergeiMikhailovskii/kmp-app-review)

## What is it?
Library that allows to launch in app (or in market) review from the KMP shared code

## Which platforms are supported?
Android and iOS

## How to integrate?
1) Add the repository in settings.gradle.kts:
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/SergeiMikhailovskii/kmp-app-review")
        }
    }
}
```
2) Add the dependency into `commonMain`:
```kotlin
implementation("com.mikhailovskii.kmp:in-app-review-kmp:$latest_tag")
```

3) Create an instance of class that implements `InAppReviewDelegate` interface.
   Now library supports 3 implementations: `AppGalleryInAppReviewManager`, `AppStoreInAppReviewManager` and `GooglePlayInAppReviewManager` (depends on the market u need).

4) To launch in-app review call
   ```kotlin
   fun requestInAppReview(): Flow<ReviewCode>
   ```

  By listening the returned flow events u can receive the [ResultCode](https://github.com/SergeiMikhailovskii/kmp-app-review/blob/master/in-app-review-kmp/src/commonMain/kotlin/com/mikhailovskii/inappreview/ReviewCode.kt) which indicates the result the process finished with
   
   - For Google Play used [in-app-review api](https://developer.android.com/guide/playcore/in-app-review)
   - For App Gallery used [in-app-comments function](https://developer.huawei.com/consumer/en/doc/AppGallery-connect-Guides/agc-comments-develop-0000001062858332)
   - For App Store used [StoreKit SKStoreReviewViewController](https://developer.apple.com/documentation/storekit/skstorereviewcontroller)
  
5) To launch in-market review call
   ```kotlin
   fun requestInMarketReview(): Flow<ReviewCode>
   ```

### Note 1
For App Gallery need to call
```kotlin
fun init()
```
this method registers activity result listener so u should invoke it before the fragment or activity is created

### Note 2
U can use a convinient function
```kotlin
fun getDefaultReviewManager(params: DefaultInAppReviewInitParams): InAppReviewDelegate
```

it provides `GooglePlayInAppReviewManager` (in Android) and `AppStoreInAppReviewManager` (in iOS) by default

## Here u can see the sample integrations:
- [iOS UI part](https://github.com/SergeiMikhailovskii/kmp-app-review/blob/master/iosApp/iosApp/ContentView.swift)
- [Android UI part](https://github.com/SergeiMikhailovskii/kmp-app-review/blob/master/androidApp/src/main/java/com/mikhailovskii/inappreview/android/MainActivity.kt)
- [Shared part](https://github.com/SergeiMikhailovskii/kmp-app-review/blob/master/in-app-review-kmp-sample/src/commonMain/kotlin/com/mikhailovskii/inappreviewkmp_sample/ReviewComponent.kt)

## Which markets are supported?
App Store, Google Play and App Gallery

## What to do if I need to support some other market?
Just create your own implementation of the `InAppReviewDelegate` interface.
There are no differences between your own implementation and the implementation out of the library.
Main idea of the library is to provide the vision how the review process can be shared.
Also it aims to collect as much implementations as it can be, so PRs are welcome :) 
