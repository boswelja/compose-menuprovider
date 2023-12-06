package com.boswelja.menuprovider.material3

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.boswelja.menuprovider.MenuItem

/**
 * Contains many "default case" [MenuItem]s used by the library. For example, the standard overflow
 * MenuItem can be created here.
 */
public actual object DefaultMenuItems {

    /**
     * Retrieves the "more options" [MenuItem]. This is used to present the user with a list of
     * items when pressed, usually as a dropdown menu.
     */
    @Composable
    public actual fun moreOptions(onClick: () -> Unit): MenuItem =
        MenuItem(
            label = stringResource(R.string.menu_item_more),
            imageVector = Icons.Default.MoreVert,
            onClick = onClick,
            isImportant = true
        )
}
