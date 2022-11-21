object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val hilt by lazy {"com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"}
}

/**
 * To define dependencies
 */
object Deps {
    // Core
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.ktx}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    val security by lazy {"androidx.security:security-crypto:${Versions.securityVersion}"}

    val lifeCycleExtensions by lazy {"androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtensions}"}

    // Room
    val roomCore by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }

    // View
    val viewBinding by lazy {"androidx.databinding:viewbinding:${Versions.viewBinding}"}
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
    val paging by lazy { "androidx.paging:paging-runtime-ktx:${Versions.paging}" }
    val progressIndicator by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.progressIndicator}" }
    val viewPager2 by lazy { "androidx.viewpager2:viewpager2:${Versions.viewPager2}" }

    // Test
    val extJunit by lazy { "androidx.test.ext:junit:${Versions.extJunit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }

    // Hilt
    val hilt by lazy {"com.google.dagger:hilt-android:${Versions.hilt}"}
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

    // Network
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitJson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val okHttpLogging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}" }
    val retrofitAdapter by lazy { "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitAdapter}" }

    // Navigation
    val navigationFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
    val navigationUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }

    // VB delegate
    val vbDelegate by lazy { "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.vbDelegate}" }

    // Glide
    val glideCore by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
}