import java.net.URI

plugins {
    alias(libs.plugins.kotlinJvm)
    `maven-publish`
}

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
