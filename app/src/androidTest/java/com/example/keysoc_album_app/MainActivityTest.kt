package com.example.keysoc_album_app

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayTab() {
        composeRule.onNodeWithText("ALBUM").assertIsDisplayed()
        composeRule.onNodeWithText("BOOKMARK").assertIsDisplayed()
    }

    @Test
    fun swipeToSecondTab() {
        composeRule.onRoot().performTouchInput {
            swipeLeft()
        }
        composeRule.onNodeWithText("Artist: Jack Johnson")
            .assertIsNotDisplayed()
    }

    @Test
    fun clickToSecondTab() {
        composeRule.onNodeWithText("BOOKMARK").performClick()
        composeRule.onNodeWithText("Artist: Jack Johnson")
            .assertIsNotDisplayed()
    }

}