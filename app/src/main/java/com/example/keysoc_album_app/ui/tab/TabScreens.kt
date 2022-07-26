package com.example.keysoc_album_app.ui.tab

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class TabPage {
    ALBUM,
    BOOKMARK
}

@Composable
fun TabHome(selectedTabIndex: Int, onSelectedTab: (TabPage) -> Unit) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = Color.Black
    ) {
        TabPage.values().forEachIndexed { index, tabPage ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onSelectedTab(tabPage) },
                text = { Text(text = tabPage.name) }
            )
        }
    }
}