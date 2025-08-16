
plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    `maven-publish`
    alias(libs.plugins.artifactRegistry)
}

tasks.named<Zip>("distZip") {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
tasks.named<Tar>("distTar") {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

description = "Painted Dogs API"
version = libs.versions.paintedDogs.get()

kotlin {
    dependencies {
        implementation(projects.core.entities)
        implementation(projects.core.ads.server)
        implementation(projects.core.analytics.server)

        implementation(libs.koin.ktor)

        implementation(libs.ktor.server.core)
        implementation(libs.ktor.server.netty)

        implementation(libs.kotlinx.coroutines)

        testImplementation(libs.kotlin.test)
        testImplementation(libs.kotlinx.coroutinesTest)
    }
}

application {
    mainClass = "org.pointyware.painteddogs.api.ServerKt"
}

ktor {
    fatJar {
        archiveFileName = "PaintedDogsAPI-${version}.jar"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.pointyware.painteddogs"
            artifactId = "painted-dogs-api"
            from(components["java"])
        }
    }
    repositories {
        maven {
            val releaseURL = "artifactregistry://us-central1-maven.pkg.dev/painted-dogs-prod-425222/painted-dogs-prod-server-repo"
            url = uri(releaseURL)
        }
    }
}
