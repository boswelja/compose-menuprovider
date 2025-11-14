package com.boswelja.menuprovider.material3

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.boswelja.menuprovider.AnimatedMenuItems
import com.boswelja.menuprovider.LocalMenuHost
import com.boswelja.menuprovider.MenuHost
import com.boswelja.menuprovider.MenuItem

/**
 * Takes the menu items provided to [menuHost] and displays them in such a way that is suitable for
 * a Material 3 TopAppBar. Animations are applied automatically via [AnimatedMenuItems].
 */
@Composable
public fun AnimatedTopAppBarMenuItems(
    modifier: Modifier = Modifier,
    menuHost: MenuHost = LocalMenuHost.current,
    maxVisibleActions: Int = 3,
    transitionSpec: AnimatedContentTransitionScope<List<MenuItem>>.() -> ContentTransform =
        { fadeIn() togetherWith fadeOut() }
) {
    AnimatedMenuItems(
        transitionSpec = transitionSpec,
        menuHost = menuHost
    ) { menuItems ->
        TopAppBarMenuItems(
            menuItems = menuItems,
            modifier = modifier,
            maxVisibleActions = maxVisibleActions
        )
    }
}

/**
 * Takes the menu items provided to [menuHost] and displays them in such a way that is suitable for
 * a Material 3 TopAppBar.
 */
@Composable
public fun TopAppBarMenuItems(
    modifier: Modifier = Modifier,
    menuHost: MenuHost = LocalMenuHost.current,
    maxVisibleActions: Int = 3,
) {
    TopAppBarMenuItems(
        menuItems = menuHost.menuItems,
        modifier = modifier,
        maxVisibleActions = maxVisibleActions
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBarMenuItems(
    menuItems: List<MenuItem>,
    modifier: Modifier = Modifier,
    maxVisibleActions: Int = 3,
) {
    val hasOverflow by remember(menuItems, maxVisibleActions) {
        derivedStateOf { menuItems.count() > maxVisibleActions }
    }
    Row(modifier) {
        if (hasOverflow) {
            val visibleItemRange = remember(menuItems, maxVisibleActions) {
                0..<maxVisibleActions
            }
            val overflowItemRange = remember(menuItems, maxVisibleActions) {
                maxVisibleActions..<menuItems.size
            }

            visibleItemRange.forEach {
                IconButtonMenuItem(menuItems[it])
            }

            var menuExpanded by rememberSaveable { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = menuExpanded, onExpandedChange = { menuExpanded = !menuExpanded }) {
                IconButtonMenuItem(
                    menuItem = DefaultMenuItems
                        .moreOptions {
                            menuExpanded = !menuExpanded
                        },
                    modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                )
                ExposedDropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    overflowItemRange.forEach {
                        DropdownMenuItem(menuItems[it])
                    }
                }
            }
        } else {
            menuItems.forEach { menuItem ->
                IconButtonMenuItem(menuItem)
            }
        }
    }
}
