//
//import app.cash.turbine.test
//import com.lwbd.lwbdapp.core.base.BaseIntent
//import com.lwbd.lwbdapp.core.base.BaseState
//import com.lwbd.lwbdapp.core.base.BaseViewModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class BaseViewModelTest {
//
//    private lateinit var viewModel: TestViewModel
//
//    @Before
//    fun setUp() {
//        viewModel = TestViewModel() // Your implementation of the BaseViewModel
//    }
//
//    @Test
//    fun `initial state should be correct`(): Unit = runTest {
//        viewModel.state.test {
//            assertEquals(TestState.Initial, awaitItem()) // Expecting the initial state
//            cancelAndIgnoreRemainingEvents() // Cancel after first state to avoid collecting indefinitely
//        }
//    }
//
//    @Test
//    fun `processIntent should update state correctly`(): Unit = runTest {
//        viewModel.state.test {
//            viewModel.processIntent(TestIntent.SomeAction)
//            assertEquals(TestState.Initial, awaitItem()) // Ensure it starts from Initial
//            assertEquals(TestState.Loading, awaitItem()) // State should update to Loading
//            assertEquals(TestState.Success, awaitItem()) // Final state is Success
//            cancelAndIgnoreRemainingEvents() // Stop collecting after the final state
//        }
//    }
//}
//
//// Example ViewModel for testing
//class TestViewModel : BaseViewModel<TestIntent, TestState>() {
//
//    override fun initialState(): TestState = TestState.Initial
//
//    override fun handleIntent(intent: TestIntent) {
//        when (intent) {
//            TestIntent.SomeAction -> {
//                setState(TestState.Loading)
//                setState(TestState.Success)
//            }
//
//            else -> {}
//        }
//    }
//}
//
//// Sample Intent and State classes for testing
//sealed class TestIntent: BaseIntent {
//    data object SomeAction : TestIntent()
//}
//
//sealed class TestState:BaseState {
//    data object Initial : TestState()
//    data object Loading : TestState()
//    data object Success : TestState()
//}