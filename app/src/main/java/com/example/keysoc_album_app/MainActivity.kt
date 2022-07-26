package com.example.keysoc_album_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.keysoc_album_app.ui.tab.AlbumScreen
import com.example.keysoc_album_app.ui.tab.BookmarkScreen
import com.example.keysoc_album_app.ui.tab.TabHome
import com.example.keysoc_album_app.ui.tab.TabPage
import com.example.keysoc_album_app.ui.theme.KeysocalbumappTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeysocalbumappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        InitTopBar()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InitTopBar() {
    val pagerState = rememberPagerState(pageCount = TabPage.values().size)
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TabHome(
            selectedTabIndex = pagerState.currentPage,
            onSelectedTab = {
                scope.launch {
                    pagerState.animateScrollToPage(it.ordinal)
                }
            }
        )
    }) {
        HorizontalPager(state = pagerState) { index ->
            Column(Modifier.fillMaxSize()) {
                when (index) {
                    0 -> AlbumScreen()
                    1 -> BookmarkScreen()
                }
            }
        }
    }
}