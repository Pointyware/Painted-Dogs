plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeHelper)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(projects.appShared)

    implementation(compose.desktop.currentOs)
    implementation(compose.ui)
    implementation(compose.preview)
    implementation(compose.material3)
    implementation(compose.components.resources)

    implementation(libs.koin.core)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "org.pointyware.painteddogs.desktop"
    generateResClass = always
}

compose.desktop {
    application {
        mainClass = "org.pointyware.painteddogs.desktop.MainKt"
    }
}
