package com.lwbd.lwbdpoc.feature.home

import SpecialistUiState
import app.cash.turbine.test
import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository
import com.lwbd.lwbdpoc.core.testing.data.specialistTestData
import com.lwbd.lwbdpoc.core.testing.data.urgentDoctorTestData
import com.lwbd.lwbdpoc.core.testing.repository.TestDoctorRepository
import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState
import io.mockk.coEvery
import io.mockk.mockk
//import io.mockk.coEvery
//import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    // Declare our mocks
    private lateinit var doctorRepository: DoctorRepository
    private lateinit var homeViewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // Set the test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Mock the repository
        doctorRepository = TestDoctorRepository()

        // Instantiate the ViewModel with the mocked repository
        homeViewModel = HomeViewModel(doctorRepository)
    }

    @Test
    fun test_LoadUrgentDoctor_updates_state_to_Success() = runTest {
        // Arrange
        val mockDoctor = urgentDoctorTestData
//

        // Act - process the LoadUrgentDoctor intent
        homeViewModel.state.test {

            homeViewModel.processIntent(HomeIntent.LoadUrgentDoctor())

//            // Assert - Wait for states
//            assertEquals(HomeState(// Loading state
//                shouldShowOnboarding = false,
//                urgentDoctorUiState = UrgentDoctorUiState.Loading,
//                specialistUiState = SpecialistUiState.Success(specialists = listOf())
//            ), awaitItem())




            val actualState = awaitItem()
//            assertEquals(UrgentDoctorUiState.Loading  , actualState.urgentDoctorUiState)
            assertEquals(UrgentDoctorUiState.Success(urgentDoctor = mockDoctor)  , actualState.urgentDoctorUiState)


        }
    }

    @Test
    fun test_LoadSpecialists_updates_state_to_Success() = runTest {
        // Arrange
        val mockSpecialists = specialistTestData

        homeViewModel.state.test {
//            val initialState = awaitItem()
//            println("*******************************************")
//            println(initialState)

            homeViewModel.processIntent(HomeIntent.LoadSpecialists)

            val actualState = awaitItem()

            assertEquals(
                SpecialistUiState.Success(mockSpecialists), actualState.specialistUiState
            )


        }
    }

    @Test
    fun test_initialState() = runTest {
        // Assert the initial state of the ViewModel
        assertEquals(
            HomeState(
                specialistUiState = SpecialistUiState.Loading,
                urgentDoctorUiState = UrgentDoctorUiState.Loading
            ),
            homeViewModel.initialState()
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}