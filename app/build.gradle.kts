plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.gitrepotestapp"
    compileSdk = ConfigData.compileSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion

    defaultConfig {
        applicationId = "com.gitrepotestapp"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    implementation(Deps.constraintLayout)
    implementation(Deps.lifeCycleExtensions)
    implementation(Deps.fragmentKtx)
    implementation(Deps.paging)
    implementation(Deps.progressIndicator)
    implementation(Deps.viewPager2)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.extJunit)
    androidTestImplementation(Deps.espresso)

    // Hilt
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)

    /*Navigation*/
    implementation(Deps.navigationFragmentKtx)
    implementation(Deps.navigationUiKtx)

    // VBDelegates
    implementation(Deps.vbDelegate)

    /*Network*/
    implementation(Deps.retrofit)
    implementation(Deps.retrofitJson)
    implementation(Deps.okHttpLogging)
    implementation(Deps.retrofitAdapter)

    // UI
    implementation(Deps.glideCore)
    annotationProcessor(Deps.glideCompiler)
    kapt(Deps.glideCompiler)

    // ROOM
    api(Deps.roomRuntime)
    annotationProcessor(Deps.roomCompiler)
    kapt(Deps.roomCompiler)
    implementation(Deps.roomCore)

    // Security
    implementation(Deps.security)
}
