# Package org.pointyware.painteddogs.core.navigation

## Concepts
- Content: A collection of UI components that are meant to display or collect information for the user.
- Location: A specific place in the app that hosts some Content.
- org.pointyware.painteddogs.core.navigation.Route: The directions a user can take to reach a set of Locations expressed by placeholders.
- Path: A org.pointyware.painteddogs.core.navigation.Route with all placeholders filled in, representing a specific Location.
- Arguments: The values that fill in the placeholders in a Path.
- Router: The system that takes a org.pointyware.painteddogs.core.navigation.Route and displays the Content at the Location.

## String list router
```kotlin
Root {
    location(org.pointyware.painteddogs.core.navigation.route("funds", "create")) { navController, args ->
        // content
        navController.navigate(org.pointyware.painteddogs.core.navigation.route("funds", "$fundId"))
    }
    location(org.pointyware.painteddogs.core.navigation.route("funds", "$fundId")) { navController, args ->
        // content
    }
}
```

## Class list router
```kotlin
object Funds {
    object Create
}

Root {
    location(org.pointyware.painteddogs.core.navigation.route(Funds, Create)) { navController, args ->
        // content
        navController.navigate(viewFundRoute.provide(fundId))
    }
    location(org.pointyware.painteddogs.core.navigation.route(Funds, org.pointyware.painteddogs.core.navigation.variable<Uuid>)) { navController, args ->
        // content
    }
}

interface VariableSegment<K> {
    val type: KClass<K>
}

fun org.pointyware.painteddogs.core.navigation.route(vararg parts: Any): org.pointyware.painteddogs.core.navigation.Route {
    return org.pointyware.painteddogs.core.navigation.Route(parts.toList())
}

class Router {
    fun org.pointyware.painteddogs.core.navigation.route(A, B, C) {
        
    }
}
```

## Typed Key Routes
```kotlin
val home = org.pointyware.painteddogs.core.navigation.route<Unit>("home")

val fundsRoute = org.pointyware.painteddogs.core.navigation.route<Unit>("funds")
val createFundsRoute = org.pointyware.painteddogs.core.navigation.route("funds", "create")
val searchFundsRoute = org.pointyware.painteddogs.core.navigation.route("funds", "search")
val fundDetailsRoute = org.pointyware.painteddogs.core.navigation.route<FundArg>("funds", "\$fundId")
val contributeRoute = org.pointyware.painteddogs.core.navigation.route<Unit>("funds", "\$fundId", "contribute")
val viewContributionRoute = org.pointyware.painteddogs.core.navigation.route<ContributionArg>("funds", "\$fundId", "contributions", "\$contributionId")

val typedRoute = org.pointyware.painteddogs.core.navigation.route(org.pointyware.painteddogs.core.navigation.static("funds"), org.pointyware.painteddogs.core.navigation.variable<Uuid>("fundId"))
```
