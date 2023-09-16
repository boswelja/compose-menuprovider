package com.boswelja.menuprovider

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

/**
 * A CompositionLocal that holds a [MenuHost]. THis provides convenient access from any
 * Composable that has a parent providing a menu.
 */
public val LocalMenuHost: ProvidableCompositionLocal<MenuHost> = compositionLocalOf { error("No MenuProvider found!") }
