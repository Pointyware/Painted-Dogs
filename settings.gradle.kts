enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
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
include(":assertions")
include(":core")
include(":core:ads")
include(":core:analytics")
include(":core:data")
include(":core:entities")
include(":core:interactors")
include(":core:local")
include(":core:navigation")
include(":core:remote")
include(":core:ui")
include(":core:view-models")

include(":feature")
include(":feature:chat")
include(":feature:collections")
include(":feature:collections:core")
include(":feature:collections:crowdfunding")
include(":feature:events")
include(":feature:groups")
include(":feature:login")
include(":feature:payments")
include(":feature:payments:core")
include(":feature:profiles")
include(":app-android")
//include(":app-ios")
include(":app-desktop")
