import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        apiVersion = KotlinVersion.KOTLIN_2_0
    }
    jvm {

    }
    androidTarget {

    }
    val framework = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "feature_login"
            isStatic = true
            framework.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:profiles"))

            implementation(libs.koin.core)

            implementation(compose.ui)
            implementation(compose.material3)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.pointyware.painteddogs.feature.login"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}
