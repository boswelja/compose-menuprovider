package com.boswelja.menuprovider.material3

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.boswelja.menuprovider.LocalMenuHost
import com.boswelja.menuprovider.MenuHost
import com.boswelja.menuprovider.MenuItem

/**
 * Displays a standard "overflow" button that, when pressed, reveals a dropdown menu containing all
 * items provided by [menuHost].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun ExposedDropdownMenuItems(
    expanded: Boolean,
    onExpandedChange: () -> Unit,
    modifier: Modifier = Modifier,
    menuHost: MenuHost = LocalMenuHost.current,
    dropdownItemContent: @Composable (MenuItem) -> Unit = { DropdownMenuItem(it) }
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange() }
    ) {
        IconButtonMenuItem(
            menuItem = DefaultMenuItems
                .moreOptions(onClick = onExpandedChange),
            modifier = Modifier.menuAnchor().then(modifier)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onExpandedChange
        ) {
            menuHost.menuItems.forEach {
                dropdownItemContent(it)
            }
        }
    }
}
