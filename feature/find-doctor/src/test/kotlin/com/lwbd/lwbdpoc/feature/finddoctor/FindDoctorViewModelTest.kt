//
//
//package com.lwbd.lwbdpoc.feature.finddoctor
//
//import com.lwbd.lwbdpoc.core.testing.data.newsResourcesTestData
//import com.lwbd.lwbdpoc.core.testing.repository.TestNewsRepository
//import com.lwbd.lwbdpoc.core.testing.repository.TestUserDataRepository
//import com.lwbd.lwbdpoc.core.testing.util.MainDispatcherRule
//import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState.Loading
//import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState.Success
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.test.UnconfinedTestDispatcher
//import kotlinx.coroutines.test.runTest
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertIs
//
///**
// * To learn more about how this test handles Flows created with stateIn, see
// * https://developer.android.com/kotlin/flow/test#statein
// */
//class FindDoctorViewModelTest {
//    @get:Rule
//    val dispatcherRule = MainDispatcherRule()
//
//    private val userDataRepository = TestUserDataRepository()
//    private val newsRepository = TestNewsRepository()
//    private val userNewsResourceRepository = CompositeUserNewsResourceRepository(
//        newsRepository = newsRepository,
//        userDataRepository = userDataRepository,
//    )
//    private lateinit var viewModel: FindDoctorViewModel
//
//    @Before
//    fun setup() {
//        viewModel = FindDoctorViewModel(
//            userDataRepository = userDataRepository,
//            userNewsResourceRepository = userNewsResourceRepository,
//        )
//    }
//
//    @Test
//    fun stateIsInitiallyLoading() = runTest {
//        assertEquals(Loading, viewModel.feedUiState.value)
//    }
//
//    @Test
//    fun oneBookmark_showsInFeed() = runTest {
//        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.feedUiState.collect() }
//
//        newsRepository.sendNewsResources(newsResourcesTestData)
//        userDataRepository.setNewsResourceBookmarked(newsResourcesTestData[0].id, true)
//        val item = viewModel.feedUiState.value
//        assertIs<Success>(item)
//        assertEquals(item.urgentDoctor.size, 1)
//
//        collectJob.cancel()
//    }
//
//    @Test
//    fun oneBookmark_whenRemoving_removesFromFeed() = runTest {
//        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.feedUiState.collect() }
//        // Set the news resources to be used by this test
//        newsRepository.sendNewsResources(newsResourcesTestData)
//        // Start with the resource saved
//        userDataRepository.setNewsResourceBookmarked(newsResourcesTestData[0].id, true)
//        // Use viewModel to remove saved resource
//        viewModel.removeFromSavedResources(newsResourcesTestData[0].id)
//        // Verify list of saved resources is now empty
//        val item = viewModel.feedUiState.value
//        assertIs<Success>(item)
//        assertEquals(item.urgentDoctor.size, 0)
//
//        collectJob.cancel()
//    }
//}
