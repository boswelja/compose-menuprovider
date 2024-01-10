package com.boswelja.menuprovider

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ImageAspectRatio
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.filled.ImageSearch
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class CumulativeMenuHostTest {

    private lateinit var cumulativeMenuHost: CumulativeMenuHost

    @BeforeTest
    fun setUp() {
        cumulativeMenuHost = CumulativeMenuHost()
    }

    @Test
    fun whenMenuItemsAdded_stateIsUpdated() {
        val firstBatch = listOf(
            MenuItem(
                label = "Test1",
                imageVector = Icons.Default.Image,
                onClick = {}
            ),
            MenuItem(
                label = "Test2",
                imageVector = Icons.Default.ImageSearch,
                onClick = {}
            ),
            MenuItem(
                label = "Test3",
                imageVector = Icons.Default.ImageAspectRatio,
                onClick = {}
            )
        )
        cumulativeMenuHost.addItems(*firstBatch.toTypedArray())
        assertTrue(cumulativeMenuHost.menuItems.containsAll(firstBatch))

        val secondBatch = listOf(
            MenuItem(
                label = "Test4",
                imageVector = Icons.Default.BrokenImage,
                onClick = {}
            ),
            MenuItem(
                label = "Test5",
                imageVector = Icons.Default.ImageNotSupported,
                onClick = {}
            ),
            MenuItem(
                label = "Test6",
                imageVector = Icons.Default.HideImage,
                onClick = {}
            )
        )
        cumulativeMenuHost.addItems(*secondBatch.toTypedArray())
        assertTrue(cumulativeMenuHost.menuItems.containsAll(firstBatch))
        assertTrue(cumulativeMenuHost.menuItems.containsAll(secondBatch))
    }

    @Test
    fun whenMenuItemsRemoved_stateIsUpdated() {
        val firstBatch = listOf(
            MenuItem(
                label = "Test1",
                imageVector = Icons.Default.Image,
                onClick = {}
            ),
            MenuItem(
                label = "Test2",
                imageVector = Icons.Default.ImageSearch,
                onClick = {}
            ),
            MenuItem(
                label = "Test3",
                imageVector = Icons.Default.ImageAspectRatio,
                onClick = {}
            )
        )
        cumulativeMenuHost.addItems(*firstBatch.toTypedArray())
        assertTrue(cumulativeMenuHost.menuItems.containsAll(firstBatch))
        cumulativeMenuHost.removeItems(*firstBatch.toTypedArray())
        assertTrue(cumulativeMenuHost.menuItems.isEmpty())
    }
}