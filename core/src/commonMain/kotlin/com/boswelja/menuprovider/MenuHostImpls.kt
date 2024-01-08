package com.boswelja.menuprovider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

internal class CumulativeMenuHost : MenuHost {
    private val _menuItems = mutableStateListOf<MenuItem>()

    override val menuItems: List<MenuItem> = _menuItems

    override fun addItems(vararg newItems: MenuItem) {
        _menuItems.addAll(newItems)
    }

    override fun removeItems(vararg items: MenuItem) {
        _menuItems.removeAll(items.toSet())
    }
}

internal class SingleTopMenuHost : MenuHost {
    private val _menuItems = mutableStateListOf<List<MenuItem>>()

    override val menuItems: List<MenuItem> = _menuItems.lastOrNull().orEmpty()

    override fun addItems(vararg newItems: MenuItem) {
        _menuItems.add(newItems.toList())
    }

    override fun removeItems(vararg items: MenuItem) {
        _menuItems.remove(items.toList())
    }
}

/**
 * Creates a new [MenuHost] for use in Composition. This should be provided via [LocalMenuHost].
 */
@Composable
@Deprecated(
    "There are MenuHosts with different behaviors out-of-the-box.\n" +
            "For a MenuHost that behaves the same, please switch to rememberCumulativeMenuHost",
    replaceWith = ReplaceWith(
        expression = "rememberCumulativeMenuHost()"
    )
)
public fun rememberMenuHost(): MenuHost {
    return rememberCumulativeMenuHost()
}

/**
 * Remembers a new [MenuHost] that "accumulates" all menu items provided from direct descendants. In
 * other words, every MenuItem provided to this MenuHost will be exposed to [MenuHost.menuItems] at
 * once.
 */
@Composable
public fun rememberCumulativeMenuHost(): MenuHost {
    return remember {
        CumulativeMenuHost()
    }
}

/**
 * Remembers a new [MenuHost] that only exposes the last set of MenuItems to [MenuHost.menuItems].
 * Inx other words, only the last group of MenuItems that were provided will be returned by
 * [MenuHost.menuItems].
 */
@Composable
public fun rememberSingleTopMenuHost(): MenuHost {
    return remember {
        SingleTopMenuHost()
    }
}
