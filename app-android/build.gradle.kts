plugins {
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeHelper)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "org.pointyware.painteddogs.android"
    compileSdk = 36
    defaultConfig {
        applicationId = "org.pointyware.painteddogs.android"
        minSdk = 24
        targetSdk = 36
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.entities)
    implementation(projects.appShared)

    implementation(libs.androidx.activityCompose)
    implementation(libs.androidx.composeMaterial3)
    debugImplementation(libs.androidx.uiTooling)
    implementation(libs.androidx.uiToolingPreview)

    androidTestDebugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.kotlinx.coroutinesAndroid)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
