import java.net.URI

plugins {
    alias(libs.plugins.kotlinJvm)
    `maven-publish`
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
            name = "GitHubPackages"
            url = URI("https://maven.pkg.github.com/Pointyware/Painted-Dogs")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
