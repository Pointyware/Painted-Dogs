
plugins {
    `kotlin-dsl`
}

group = "org.pointyware.painteddogs.buildlogic.conventions"

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        create("koin-dependency-injection-convention") {
            id = "org.pointyware.painteddogs.koin-dependency-injection-convention"
            version = "0.1.0"
            implementationClass = "org.pointyware.painteddogs.buildlogic.KoinDependencyInjectionConventionPlugin"
        }

        create("kmp-convention") {
            id = "org.pointyware.painteddogs.kmp-convention"
            version = "0.1.0"
            implementationClass = "org.pointyware.painteddogs.buildlogic.KmpTargetsConventionPlugin"
        }
    }
}
