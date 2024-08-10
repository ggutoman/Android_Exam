plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)

    //kotlin serializer
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}

android {
    namespace = "com.pg.android_exam.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pg.android_exam.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    implementation(projects.shared)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //KTOR DEPENDENCY
    implementation("io.ktor:ktor-client-core:2.3.0") //core dependency
    implementation("io.ktor:ktor-client-content-negotiation:2.3.0") //network operation dependency
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0") //serializer amd deserializer data
    implementation("io.ktor:ktor-client-logging:2.3.0") //logging
    implementation("io.ktor:ktor-client-android:2.3.0") //http client engine



    debugImplementation(libs.compose.ui.tooling)
}