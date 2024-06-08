plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core:ads"))
    implementation(project(":core:analytics"))
    implementation(project(":core:data"))
    implementation(project(":core:entities"))
    implementation(project(":core:interactors"))
    implementation(project(":core:local"))
    implementation(project(":core:navigation"))
    implementation(project(":core:remote"))
    implementation(project(":core:ui"))
    implementation(project(":core:view-models"))

    implementation(project(":feature:chat"))
    implementation(project(":feature:funds"))
    implementation(project(":feature:events"))
    implementation(project(":feature:groups"))
    implementation(project(":feature:login"))
    implementation(project(":feature:payments"))
    implementation(project(":feature:profiles"))

    implementation(project(":app-shared"))

    implementation(libs.kotlinx.dateTime)
    implementation(libs.koin.core)

    implementation(compose.desktop.currentOs)
    implementation(compose.ui)
    implementation(compose.preview)
    implementation(compose.material3)
}

compose.desktop {
    application {
        mainClass = "org.pointyware.painteddogs.desktop.MainKt"
    }
}
