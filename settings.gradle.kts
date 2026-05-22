enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "painted-dogs"
include(
    ":core",
    ":core:ads",
    ":core:analytics",
    ":core:common",
    ":core:data",
    ":core:entities",
    ":core:interactors",
    ":core:local",
    ":core:navigation",
    ":core:remote",
    ":core:ui",
    ":core:view-models"
)

include(
    ":feature",
    ":feature:aid",
    ":feature:chat",
    ":feature:funds",
    ":feature:login",
    ":feature:payments",
    ":feature:profiles"
)

include(
    ":app-shared",
    ":app-android",
    // ":app-ios",
    ":app-desktop"
)
