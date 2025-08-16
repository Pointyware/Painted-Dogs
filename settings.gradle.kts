enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "painted-dogs"
include(":assertions")
include(":core:ads")
include(":core:ads:client")
include(":core:ads:server")
include(":core:analytics")
include(":core:analytics:client")
include(":core:analytics:server")
include(":core:common")
include(":core:data")
include(":core:entities")
include(":core:interactors")
include(":core:local")
include(":core:navigation")
include(":core:remote")
include(":core:ui")
include(":core:view-models")

include(":feature:chat")
include(":feature:funds")
include(":feature:events")
include(":feature:groups")
include(":feature:login")
include(":feature:payments")
include(":feature:profiles")
include(":feature:rides")

include(":api")
include(":app-shared")
include(":app-android")
//include(":app-ios")
include(":app-desktop")
