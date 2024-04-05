# Compose MenuProvider

An AndroidX MenuHost & MenuProvider-like API for Jetpack Compose!

## Setup

```kt
kotlin {
    sourceSets {
        commonMain.dependencies {
            val menuproviderVersion = "1.2.1"

            // Core provides a generic implementation fit for any design system
            implementation("io.github.boswelja.menuprovider:core:$menuproviderVersion")

            // material3 contains opinionated components to streamline development
            implementation("io.github.boswelja.menuprovider:material3:$menuproviderVersion")
        }
    }
}
```

## Usage

```kotlin
@Composable
fun MyComposable() {
    ProvideCumulativeMenuHost {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("My Top Bar") },
                    actions = { AnimatedTopAppBarMenuItems() }
                )
            }
        ) {
            MyMenuProvidingComposable()
        }
    }
}

@Composable
fun MyMenuProvidingComposable() {
    ProvideMenuItems(
        MenuItem(
            label = "My Menu Item",
            imageVector = Icons.Default.Add,
            onClick = { performAction() },
            isImportant = true,
        )
    )
}
```

## Documentation

Docs are published with each release to https://boswelja.github.io/compose-menuprovider/.
