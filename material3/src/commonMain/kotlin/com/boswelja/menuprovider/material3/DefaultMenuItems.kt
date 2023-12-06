package com.boswelja.menuprovider.material3

import androidx.compose.runtime.Composable
import com.boswelja.menuprovider.MenuItem

/**
 * Contains many "default case" [MenuItem]s used by the library. For example, the standard overflow
 * MenuItem can be created here.
 */
public expect object DefaultMenuItems {

    /**
     * Retrieves the "more options" [MenuItem]. This is used to present the user with a list of
     * items when pressed, usually as a dropdown menu.
     */
    @Composable
    public fun moreOptions(onClick: () -> Unit): MenuItem
}
