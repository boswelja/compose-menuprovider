package com.boswelja.menuprovider.material3

import androidx.compose.runtime.Composable
import com.boswelja.menuprovider.MenuItem
import io.github.boswelja.menuprovider.material3.generated.resources.Res
import io.github.boswelja.menuprovider.material3.generated.resources.menu_item_more
import io.github.boswelja.menuprovider.material3.generated.resources.more_vert
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

/**
 * Contains many "default case" [MenuItem]s used by the library. For example, the standard overflow
 * MenuItem can be created here.
 */
public object DefaultMenuItems {

    /**
     * Retrieves the "more options" [MenuItem]. This is used to present the user with a list of
     * items when pressed, usually as a dropdown menu.
     */
    @Composable
    public fun moreOptions(onClick: () -> Unit): MenuItem =
        MenuItem(
            label = stringResource(Res.string.menu_item_more),
            imageVector = vectorResource(Res.drawable.more_vert),
            onClick = onClick,
            isImportant = true
        )
}
