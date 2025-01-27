import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization)
    alias(libs.plugins.safe.args)
}

android {
    namespace = "com.example.weatherforecast"
    compileSdk = 35

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = "com.example.weatherforecast"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            type = "string",
            name = "apiKey",
            value = properties.getProperty("apiKey")
        )

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

    buildFeatures {
        viewBinding = true
        buildConfig = true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)

    //hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    //retrofit
    implementation(libs.retrfoit)
    implementation(libs.gson.converter)

    //serialization
    implementation(libs.serialization.core)
    implementation(libs.serialization.json)

    //glide
    implementation(libs.glide.glide)
    ksp(libs.glide.compiler)

    //scalable ui
    implementation(libs.scalableUi)
}