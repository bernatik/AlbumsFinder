plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.alexbernat.albumsfinder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alexbernat.albumsfinder"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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

    val androidXCore = "1.12.0"
    val androidXAppCompat = "1.6.1"
    val googleMaterial = "1.9.0"
    val androidXConstraint = "2.1.4"
    val kotlinSerializationVersion = "1.5.1"
    val koinVersion = "3.5.0"
    val ktorVersion = "2.3.1"
    val glideVersion = "4.16.0"
    val lifecycleVersion = "2.6.2"

    val junitVersion = "4.13.2"
    val mockitoKotlinVersion = "4.1.0"
    val coroutinesVersion = "1.7.3"
    val assertionsVersion = "1.1.5"

    implementation("androidx.core:core-ktx:$androidXCore")
    implementation("androidx.appcompat:appcompat:$androidXAppCompat")
    implementation("com.google.android.material:material:$googleMaterial")
    implementation("androidx.constraintlayout:constraintlayout:$androidXConstraint")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")


    implementation("com.github.bumptech.glide:glide:$glideVersion")

    testImplementation("junit:junit:$junitVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
    testImplementation("com.google.truth:truth:$assertionsVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}