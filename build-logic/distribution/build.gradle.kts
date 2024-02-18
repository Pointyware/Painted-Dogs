
plugins {
    kotlin("jvm")
    application
}

group = "org.pointyware.painteddogs.buildlogic.distribution"

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("com.google.apis:google-api-services-androidpublisher:v3-rev20240207-2.0.0")
}

application {
    mainClass = "org.pointyware.painteddogs.buildlogic.distribution.MainKt"
    applicationDefaultJvmArgs = listOf()
}
