
plugins {
    kotlin("jvm")
    application
}

group = "org.pointyware.painteddogs.buildlogic.distribution"

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation("com.google.apis:google-api-services-androidpublisher:v3-rev20240207-2.0.0")
    implementation("com.google.api-client:google-api-client:2.2.0")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.10.0")

    testImplementation(libs.kotlin.test)
}

application {
    mainClass = "org.pointyware.painteddogs.buildlogic.distribution.MainKt"
    applicationDefaultJvmArgs = listOf()
}

distributions {
    main {
        distributionBaseName = "painted-dogs"
        contents {
            // Contain output of jar task by default
        }
    }
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "org.pointyware.painteddogs.buildlogic.distribution.MainKt")
    }
}
