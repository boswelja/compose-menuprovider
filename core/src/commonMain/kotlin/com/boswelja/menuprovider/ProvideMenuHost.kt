package com.boswelja.menuprovider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * A convenience function for providing a new MenuHost.
 */
@Composable
public fun ProvideMenuHost(
    menuHost: MenuHost,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMenuHost provides menuHost,
        content = content,
    )
}

/**
 * A convenience function for providing a cumulative MenuHost. See [rememberCumulativeMenuHost] for
 * details. To provide your own MenuHost, use [ProvideMenuHost].
 */
@Composable
public fun ProvideCumulativeMenuHost(content: @Composable () -> Unit) {
    ProvideMenuHost(menuHost = rememberCumulativeMenuHost(), content = content)
}

/**
 * A convenience function for providing a single-top MenuHost. See [rememberCumulativeMenuHost] for
 * details. To provide your own MenuHost, use [ProvideMenuHost].
 */
@Composable
public fun ProvideSingleTopMenuHost(content: @Composable () -> Unit) {
    ProvideMenuHost(menuHost = rememberSingleTopMenuHost(), content = content)
}
