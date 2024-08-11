plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)

    //kotlin serializer
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"

    //KOTLIN INJECTION
    id("com.google.dagger.hilt.android") version "2.44" apply false
}
