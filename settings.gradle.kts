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
    ":core:data",
    ":core:entities",
    ":core:interactors",
    ":core:local",
    ":core:remote",
    ":core:ui",
    ":core:view-models"
)

include(
    ":feature",
    ":feature:chat",
    ":feature:collections",
    ":feature:collections:core",
    ":feature:collections:crowdfunding",
    ":feature:events",
    ":feature:groups",
    ":feature:login",
    ":feature:payments",
    ":feature:payments:core",
    ":feature:profiles"
)

include(
    ":app-shared",
    ":app-android",
    // ":app-ios",
    ":app-desktop"
)
