plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:collections:core"))

    implementation(libs.kotlinx.dateTime)

    implementation(compose.desktop.currentOs)
    implementation(compose.ui)
    implementation(compose.preview)
    implementation(compose.material3)
}

compose.desktop {
    application {
        mainClass = "org.pointyware.painteddogs.MainKt"
    }
}
