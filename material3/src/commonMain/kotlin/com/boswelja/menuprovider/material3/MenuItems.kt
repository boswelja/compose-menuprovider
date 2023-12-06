package com.boswelja.menuprovider.material3

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.boswelja.menuprovider.MenuItem

/**
 * An opinionated [androidx.compose.material3.IconButton] used to display a single [MenuItem].
 */
@Composable
public fun IconButtonMenuItem(
    menuItem: MenuItem,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    IconButton(
        onClick = menuItem.onClick,
        modifier = modifier,
        colors = colors,
        interactionSource = interactionSource,
    ) {
        Icon(menuItem.imageVector, menuItem.label)
    }
}

/**
 * An opinionated [androidx.compose.material3.DropdownMenuItem] used to display a single [MenuItem].
 */
@Composable
public fun DropdownMenuItem(
    menuItem: MenuItem,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: MenuItemColors = MenuDefaults.itemColors(),
    contentPadding: PaddingValues = MenuDefaults.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    androidx.compose.material3.DropdownMenuItem(
        text = {
            Text(menuItem.label)
        },
        leadingIcon = {
            Icon(menuItem.imageVector, null)
        },
        onClick = menuItem.onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    )
}
