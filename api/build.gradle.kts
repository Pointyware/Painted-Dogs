
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
        implementation(project(":core:entities"))
        implementation(project(":core:ads:server"))
        implementation(project(":core:analytics:server"))

        implementation(libs.koin.ktor)

        implementation(libs.ktor.server.core)
        implementation(libs.ktor.server.netty)
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

        // TODO: Add GitHub Packages
//        maven {
//            name = "GitHubPackages"
//            url = URI("https://maven.pkg.github.com/Pointyware/Painted-Dogs")
//            credentials {
//                username = System.getenv("GITHUB_ACTOR")
//                password = System.getenv("GITHUB_TOKEN")
//            }
//        }
    }
}
