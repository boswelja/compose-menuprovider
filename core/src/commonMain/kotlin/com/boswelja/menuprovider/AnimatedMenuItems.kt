package com.boswelja.menuprovider

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A convenience function for animating MenuItems inside an AnimatedContent.
 */
@Composable
public fun AnimatedMenuItems(
    transitionSpec: AnimatedContentTransitionScope<List<MenuItem>>.() -> ContentTransform,
    modifier: Modifier = Modifier,
    menuHost: MenuHost = LocalMenuHost.current,
    label: String = "AnimatedMenuItems",
    content: @Composable AnimatedContentScope.(List<MenuItem>) -> Unit,
) {
    AnimatedContent(
        transitionSpec = transitionSpec,
        targetState = menuHost.menuItems,
        label = label,
        modifier = modifier,
        content = content
    )
}
