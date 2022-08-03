package com.example.keysoc_album_app.ui.tab

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.keysoc_album_app.MainActivity
import com.example.keysoc_album_app.data.api.repository.MockAlbumRepository
import com.example.keysoc_album_app.di.DatabaseModule
import com.example.keysoc_album_app.di.NetworkingModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalSerializationApi::class)
@HiltAndroidTest
@UninstallModules(NetworkingModule::class, DatabaseModule::class)
class AlbumScreenTest {
    private lateinit var viewModel: AlbumScreenViewModel
    private lateinit var mockAlbumRepository: MockAlbumRepository

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setup() {
        mockAlbumRepository = MockAlbumRepository()
        hiltRule.inject()
    }

    @Test
    fun showAlbumList() {
        composeRule.onNodeWithText("Artist: Jack Johnson")
            .assertIsDisplayed()
    }

}