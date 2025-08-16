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
            baseName = "feature_profiles"
            isStatic = true
            framework.add(this)
        }
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:common"))
                implementation(project(":core:entities"))
                implementation(project(":core:navigation"))
                implementation(project(":core:ui"))

                implementation(project(":feature:funds"))

                implementation(libs.koin.core)

                implementation(compose.ui)
                implementation(compose.material3)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val jvmSharedMain by creating {
            dependsOn(commonMain)
        }
        val jvmSharedTest by creating {
            dependsOn(commonTest)
        }

        val jvmMain by getting {
            dependsOn(jvmSharedMain)
        }
        val jvmTest by getting {
            dependsOn(jvmSharedTest)
        }

        val androidMain by getting {
            dependsOn(jvmSharedMain)
        }
        val androidUnitTest by getting {
            dependsOn(jvmSharedTest)
        }
    }
}

android {
    namespace = "org.pointyware.painteddogs.feature.profiles"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}
