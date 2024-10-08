package com.lwbd.lwbdpoc.feature.home


import SpecialistScreen
import SpecialistUiState
import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdOverlayLoadingWheel
import com.lwbd.lwbdpoc.core.ui.TrackScrollJank
import com.lwbd.lwbdpoc.core.ui.UrgentDoctorUiState
import com.lwbd.lwbdpoc.core.ui.urgentDoctorCard
import kotlin.reflect.KFunction1

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    onCallNowClick: KFunction1<NavOptions?, Unit>?,

    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        urgentDoctorUiState = state.urgentDoctorUiState,
        specialistUiState = state.specialistUiState,
        onCallNowClick = onCallNowClick,
        modifier = modifier,
    )
}

@Composable
internal fun HomeScreen(

    modifier: Modifier = Modifier,
    urgentDoctorUiState: UrgentDoctorUiState,
    specialistUiState: SpecialistUiState,

    onCallNowClick: KFunction1<NavOptions?, Unit>?,

) {

    val isFeedLoading =
        (urgentDoctorUiState is UrgentDoctorUiState.Loading) || (specialistUiState is SpecialistUiState.Loading)

    // This code should be called when the UI is ready for use and relates to Time To Full Display.
    ReportDrawnWhen { !isFeedLoading }
    val backgroundImage: Painter = painterResource(id = R.drawable.feature_home_doctor_2_1)

//    val itemsAvailable = feedItemsSize(feedState, onboardingUiState)

    val state = rememberLazyStaggeredGridState()
//    val scrollbarState = state.scrollbarState(
//        itemsAvailable = itemsAvailable,
//    )
    TrackScrollJank(scrollableState = state, stateName = "home:feed")

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val config = LocalConfiguration
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(config.current.screenWidthDp.dp),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 24.dp,
            modifier = Modifier
                .testTag("home"),
            state = state,
        ) {

            urgentDoctorCard(
                urgentDoctorState = urgentDoctorUiState,
                backgroundImage = backgroundImage,
                onCallNowClicked = onCallNowClick
            )

            item {
                SpecialistScreen(
                    specialistUiState = specialistUiState,
                    modifier = Modifier
                )
            }
        }
        AnimatedVisibility(
            visible = isFeedLoading,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },
            ) + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
            ) + fadeOut(),
        ) {
            val loadingContentDescription = stringResource(id = R.string.feature_home_loading)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            ) {
                LwbdOverlayLoadingWheel(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("loading"),
                    contentDesc = loadingContentDescription,
                )
            }
        }


    }
}




