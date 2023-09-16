# Compose MenuProvider

An AndroidX MenuHost & MenuProvider-like API for Jetpack Compose!

## Setup

TODO

## Usage

```kotlin
@Composable
fun MyComposable() {
    val menuHost = rememberMenuHost()
    ProvideMenuHost(menuHost) {
        // From here, we can provide MenuItems from any child Composable
        MyMenuProvidingComposable()
    }
    
    // Let's create some kind of menu
    Row {
        menuHost.menuItems.forEach { menuItem ->
            MyMenuItem(menuItem)
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
        ),
    )
}
```
