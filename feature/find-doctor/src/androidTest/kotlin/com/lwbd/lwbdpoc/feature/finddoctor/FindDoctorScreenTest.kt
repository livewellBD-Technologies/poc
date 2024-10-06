//
//
//package com.lwbd.lwbdpoc.feature.finddoctor
//
//import androidx.activity.ComponentActivity
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.lifecycle.compose.LocalLifecycleOwner
//import androidx.compose.ui.test.assertCountEquals
//import androidx.compose.ui.test.assertHasClickAction
//import androidx.compose.ui.test.filter
//import androidx.compose.ui.test.hasAnyAncestor
//import androidx.compose.ui.test.hasScrollToNodeAction
//import androidx.compose.ui.test.hasText
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.compose.ui.test.onAllNodesWithContentDescription
//import androidx.compose.ui.test.onFirst
//import androidx.compose.ui.test.onNodeWithContentDescription
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performScrollToNode
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.testing.TestLifecycleOwner
//import com.lwbd.lwbdpoc.core.testing.data.userNewsResourcesTestData
//import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState
//import kotlinx.coroutines.test.runTest
//import org.junit.Rule
//import org.junit.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertTrue
//
///**
// * UI tests for [FindDoctorScreen] composable.
// */
//class FindDoctorScreenTest {
//
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
//
//    @Test
//    fun loading_showsLoadingSpinner() {
//        composeTestRule.setContent {
//            FindDoctorScreen(
//                feedState = UrgentDoctorUiState.Loading,
//                onShowSnackbar = { _, _ -> false },
//                removeFromBookmarks = {},
//                onTopicClick = {},
//                onNewsResourceViewed = {},
//            )
//        }
//
//        composeTestRule
//            .onNodeWithContentDescription(
//                composeTestRule.activity.resources.getString(R.string.feature_finddoctor_loading),
//            )
//            .assertExists()
//    }
//
//    @Test
//    fun feed_whenHasBookmarks_showsBookmarks() {
//        composeTestRule.setContent {
//            FindDoctorScreen(
//                feedState = UrgentDoctorUiState.Success(
//                    userNewsResourcesTestData.take(2),
//                ),
//                onShowSnackbar = { _, _ -> false },
//                removeFromBookmarks = {},
//                onTopicClick = {},
//                onNewsResourceViewed = {},
//            )
//        }
//
//        composeTestRule
//            .onNodeWithText(
//                userNewsResourcesTestData[0].title,
//                substring = true,
//            )
//            .assertExists()
//            .assertHasClickAction()
//
//        composeTestRule.onNode(hasScrollToNodeAction())
//            .performScrollToNode(
//                hasText(
//                    userNewsResourcesTestData[1].title,
//                    substring = true,
//                ),
//            )
//
//        composeTestRule
//            .onNodeWithText(
//                userNewsResourcesTestData[1].title,
//                substring = true,
//            )
//            .assertExists()
//            .assertHasClickAction()
//    }
//
//    @Test
//    fun feed_whenRemovingBookmark_removesBookmark() {
//        var removeFromBookmarksCalled = false
//
//        composeTestRule.setContent {
//            FindDoctorScreen(
//                feedState = UrgentDoctorUiState.Success(
//                    userNewsResourcesTestData.take(2),
//                ),
//                onShowSnackbar = { _, _ -> false },
//                removeFromBookmarks = { newsResourceId ->
//                    assertEquals(userNewsResourcesTestData[0].id, newsResourceId)
//                    removeFromBookmarksCalled = true
//                },
//                onTopicClick = {},
//                onNewsResourceViewed = {},
//            )
//        }
//
//        composeTestRule
//            .onAllNodesWithContentDescription(
//                composeTestRule.activity.getString(
//                    com.lwbd.lwbdpoc.core.ui.R.string.core_ui_unbookmark,
//                ),
//            ).filter(
//                hasAnyAncestor(
//                    hasText(
//                        userNewsResourcesTestData[0].title,
//                        substring = true,
//                    ),
//                ),
//            )
//            .assertCountEquals(1)
//            .onFirst()
//            .performClick()
//
//        assertTrue(removeFromBookmarksCalled)
//    }
//
//    @Test
//    fun feed_whenHasNoBookmarks_showsEmptyState() {
//        composeTestRule.setContent {
//            FindDoctorScreen(
//                feedState = UrgentDoctorUiState.Success(emptyList()),
//                onShowSnackbar = { _, _ -> false },
//                removeFromBookmarks = {},
//                onTopicClick = {},
//                onNewsResourceViewed = {},
//            )
//        }
//
//        composeTestRule
//            .onNodeWithText(
//                composeTestRule.activity.getString(R.string.feature_bookmarks_empty_error),
//            )
//            .assertExists()
//
//        composeTestRule
//            .onNodeWithText(
//                composeTestRule.activity.getString(R.string.feature_bookmarks_empty_description),
//            )
//            .assertExists()
//    }
//
//    @Test
//    fun feed_whenLifecycleStops_undoBookmarkedStateIsCleared() = runTest {
//        var undoStateCleared = false
//        val testLifecycleOwner = TestLifecycleOwner(initialState = Lifecycle.State.STARTED)
//
//        composeTestRule.setContent {
//            CompositionLocalProvider(LocalLifecycleOwner provides testLifecycleOwner) {
//                FindDoctorScreen(
//                    feedState = UrgentDoctorUiState.Success(emptyList()),
//                    onShowSnackbar = { _, _ -> false },
//                    removeFromBookmarks = {},
//                    onTopicClick = {},
//                    onNewsResourceViewed = {},
//                    clearUndoState = {
//                        undoStateCleared = true
//                    },
//                )
//            }
//        }
//
//        assertEquals(false, undoStateCleared)
//        testLifecycleOwner.handleLifecycleEvent(event = Lifecycle.Event.ON_STOP)
//        assertEquals(true, undoStateCleared)
//    }
//}
