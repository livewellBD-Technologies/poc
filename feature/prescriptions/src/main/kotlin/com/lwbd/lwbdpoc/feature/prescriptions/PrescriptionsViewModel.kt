//
//
//package com.lwbd.lwbdpoc.feature.finddoctor
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.lwbd.lwbdpoc.core.model.data.UserNewsResource
//import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState
//import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState.Loading
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.flow.onStart
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class PrescriptionsViewModel @Inject constructor(
//    private val userDataRepository: UserDataRepository,
//    userNewsResourceRepository: UserNewsResourceRepository,
//) : ViewModel() {
//
//    var shouldDisplayUndoBookmark by mutableStateOf(false)
//    private var lastRemovedBookmarkId: String? = null
//
//    val feedUiState: StateFlow<UrgentDoctorUiState> =
//        userNewsResourceRepository.observeAllBookmarked()
//            .map<List<UserNewsResource>, UrgentDoctorUiState>(UrgentDoctorUiState::Success)
//            .onStart { emit(Loading) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = Loading,
//            )
//
//    fun removeFromSavedResources(newsResourceId: String) {
//        viewModelScope.launch {
//            shouldDisplayUndoBookmark = true
//            lastRemovedBookmarkId = newsResourceId
//            userDataRepository.setNewsResourceBookmarked(newsResourceId, false)
//        }
//    }
//
//    fun setNewsResourceViewed(newsResourceId: String, viewed: Boolean) {
//        viewModelScope.launch {
//            userDataRepository.setNewsResourceViewed(newsResourceId, viewed)
//        }
//    }
//
//    fun undoBookmarkRemoval() {
//        viewModelScope.launch {
//            lastRemovedBookmarkId?.let {
//                userDataRepository.setNewsResourceBookmarked(it, true)
//            }
//        }
//        clearUndoState()
//    }
//
//    fun clearUndoState() {
//        shouldDisplayUndoBookmark = false
//        lastRemovedBookmarkId = null
//    }
//}
