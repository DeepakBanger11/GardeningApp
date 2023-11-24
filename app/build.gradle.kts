plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.getstarted.flower"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.getstarted.flower"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.databinding:databinding-runtime:8.1.4")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("androidx.paging:paging-common-android:3.3.0-alpha02")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    val lifecycle_version = "2.6.2"
    val arch_version = "2.2.0"
    val appcompat_version = "1.6.1"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

//Accompanist Navigation Animation
    implementation("com.google.accompanist:accompanist-navigation-animation:0.32.0")

    //DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // ViewModel and Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-service:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-process:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.9.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    //daggar
    implementation("com.google.dagger:dagger: 2.48")
    //implementation("com.google.dagger:dagger-android-support: 2.48")
    // annotationProcessor("com.google.dagger:dagger-compiler: 2.48")
    // annotationProcessor("com.google.dagger:dagger-android-processor: 2.48")
    kapt("com.google.dagger:hilt-compiler:2.44")
    implementation("com.google.dagger:hilt-android:2.44")

    implementation("androidx.recyclerview:recyclerview:1.3.2")



    implementation("androidx.appcompat:appcompat:$appcompat_version")
    // For loading and tinting drawables on older versions of the platform
    implementation("androidx.appcompat:appcompat-resources:$appcompat_version")

    // Room components
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test:runner:1.5.2")
    kapt("androidx.room:room-compiler:2.6.0")

    //RX java
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")

    androidTestImplementation("com.google.truth:truth:1.1.4")
    testImplementation("com.google.truth:truth:1.1.4")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito:mockito-inline:3.8.0")
    androidTestImplementation("org.mockito:mockito-android:3.12.4")
// testing flow, made easy
    implementation("app.cash.turbine:turbine:1.0.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))

    testImplementation("androidx.lifecycle:lifecycle-runtime-testing:$lifecycle_version")
    testImplementation("androidx.arch.core:core-testing:$arch_version")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

fun getUnsplashAccess(): String? {
    return project.findProperty("unsplash_access_key") as? String
}
//allows reference to generated code
kapt {
    correctErrorTypes = true
}