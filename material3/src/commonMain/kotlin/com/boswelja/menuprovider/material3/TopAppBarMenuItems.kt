package com.boswelja.menuprovider.material3

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
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

/**
 * Takes the menu items provided to [menuHost] and displays them in such a way that is suitable for
 * a Material 3 TopAppBar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun TopAppBarMenuItems(
    modifier: Modifier = Modifier,
    menuHost: MenuHost = LocalMenuHost.current,
    maxVisibleActions: Int = 3
) {
    AnimatedMenuItems(
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        menuHost = menuHost
    ) { menuItems ->
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
                ExposedDropdownMenuBox(expanded = menuExpanded, onExpandedChange = { menuExpanded = !menuExpanded}) {
                    IconButtonMenuItem(
                        menuItem = DefaultMenuItems
                            .moreOptions {
                                menuExpanded = !menuExpanded
                            },
                        modifier = Modifier.menuAnchor()
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
}
