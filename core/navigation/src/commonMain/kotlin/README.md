# Package org.pointyware.painteddogs.core.navigation

## Concepts
- Content: A collection of UI components that are meant to display or collect information for the user.
- Location: A specific place in the app that hosts some content.
- Route: The directions a user must take to reach a location.
- Router: The system that takes a Route and displays the Content at the Location.

String list router
```kotlin
Root {
    location(route("funds", "create")) { navController, args ->
        // content
        navController.navigate(route("funds", "$fundId"))
    }
    location(route("funds", "$fundId")) { navController, args ->
        // content
    }
}
```

Class list router
```kotlin
Root {
    location(route(Funds, Create)) { navController, args ->
        // content
        navController.navigate(viewFundRoute.provide(fundId))
    }
    location(route(Funds, variable<Uuid>)) { navController, args ->
        // content
    }
}

interface VariableSegment<K> {
    val type: KClass<K>
}

fun route(vararg parts: Any): Route {
    return Route(parts.toList())
}

class Router {
    fun route(A, B, C) {
        
    }
}
```
