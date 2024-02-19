plugins {
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
}

android {
    namespace = "org.pointyware.painteddogs.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "org.pointyware.painteddogs.android"
        minSdk = 21
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
    }
    signingConfigs {
        val keystoreReleasePass: String? by project
        val keystoreReleasePaintedDogsPass: String? by project
        keystoreReleasePass ?: throw IllegalStateException("Please set keystoreReleasePass as a gradle property")
        keystoreReleasePaintedDogsPass ?: throw IllegalStateException("Please set keystoreReleasePaintedDogsPass as a gradle property")
        create("release") {
            storeFile = rootProject.file("release.keystore")
            storePassword = keystoreReleasePass
            keyAlias = "painteddogs"
            keyPassword = keystoreReleasePaintedDogsPass
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:entities"))

    implementation(libs.androidx.activityCompose)
    implementation(compose.preview)
    implementation(compose.ui)
    implementation(compose.material3)
}
