package com.lwbd.lwbdpoc.appointment_booking

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.lwbd.lwbdpoc.apointment_booking.R
import com.lwbd.lwbdpoc.core.designsystem.component.JourneyCompleteModalSheet
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdButton
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdTextButton
import com.lwbd.lwbdpoc.core.designsystem.theme.AccentColorLight
import com.lwbd.lwbdpoc.core.designsystem.theme.BlackColor80
import com.lwbd.lwbdpoc.core.designsystem.theme.GreyColor
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTypography
import com.lwbd.lwbdpoc.core.designsystem.theme.Roboto
import com.lwbd.lwbdpoc.core.designsystem.theme.WhiteColor657
import com.lwbd.lwbdpoc.core.designsystem.theme.WhiteColorFC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AppointmentBookingRoute(
    modifier: Modifier = Modifier,
    viewModel: AppointmentBookingViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    AppointmentBookingIntent.SelectOption(viewModel.mySelf)


    AppointmentBookingScreen(
        viewModel,
        state = state,
        onIntent = viewModel::processIntent,
        navController,
        modifier = modifier,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppointmentBookingScreen(
    viewModel: AppointmentBookingViewModel= hiltViewModel(),
    state: AppointmentBookingState,
    onIntent: (AppointmentBookingIntent) -> Unit,
    navController: NavHostController,
    modifier: Modifier,
) {


    val sheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = true) // Controls the state of the bottom sheet
    val scope = rememberCoroutineScope() // CoroutineScope to show/hide the bottom sheet
//    var showSheet by remember { mutableStateOf(false) } // State to toggle the visibility of the bottom sheet

    if (state.showSheet) {
        // ModalBottomSheet that is triggered when `showSheet` is true
        JourneyCompleteModalSheet(sheetState = sheetState, scope = scope, onDismiss = {
            scope.launch {
                onIntent(AppointmentBookingIntent.HideBottomSheet)// Hide the sheet programmatically
            }
        }, onCloseButtonClick = {
            scope.launch {
                sheetState.hide()
                onIntent(AppointmentBookingIntent.HideBottomSheet)// Hide the sheet programmatically
            }
        })
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AppointmentPatientBottomBar(navController, scope, sheetState, onNextButtonClick = {
                scope.launch {
                    sheetState.show() // Show the sheet programmatically
                    onIntent(AppointmentBookingIntent.ShowBottomSheet)// open sheet by toggling the state
                }
            })
        },
        content = { innerPadding ->
            AppointmentPatientContent(
                viewModel,
                state,
                onIntent,
                navController,
                innerPadding,
                modifier = Modifier,
            )
        },
    )
}

@Composable
private fun AppointmentPatientContent(
    viewModel: AppointmentBookingViewModel= hiltViewModel(),
    state: AppointmentBookingState,
    onIntent: (AppointmentBookingIntent) -> Unit,
    navController: NavHostController,
    innerPadding: PaddingValues,
    modifier: Modifier,
) {



    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top,

            ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(227.dp + innerPadding.calculateTopPadding())
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(
                                    red = 157, green = 184, blue = 217
                                ),
                                Color(red = 157, green = 184, blue = 217),
                                Color(red = 157, green = 184, blue = 217),
                                Color(red = 101, green = 139, blue = 176)
                            )
                        )
                    )
                    .padding(top = innerPadding.calculateTopPadding())

            ) {
                Image(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(end = 22.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.feature_appointment_booking_half_logo),
                    contentDescription = null
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopEnd)
                        .padding(top = 15.dp, end = 15.dp, start = 22.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .size(9.dp)
                                .clip(CircleShape)
                                .background(color = Color.Green)
                                .border(
                                    width = 1.dp, color = WhiteColorFC, shape = CircleShape
                                )
                                .clip(CircleShape)
                        ) {}
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = stringResource(R.string.feature_appointment_booking_doctor_available),
                            style = LwbdTypography.bodyMedium.copy(color = Color.White)
                        )
                    }
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }


                }
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 41.dp, start = 22.dp)
                        .width(178.dp),
                    text = stringResource(R.string.feature_appointment_booking_booking_an_immediate_call) + stringResource(
                        R.string.feature_appointment_booking_general_physician
                    ),
                    style = LwbdTypography.bodyLarge.copy(color = Color.White)
                )

            }

            Box(modifier = Modifier.height(12.dp)) {

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 20.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    text = stringResource(R.string.feature_appointment_booking_who_is_the_patient),
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 30.sp,
                        fontFamily = Roboto
                    ),
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            CustomRadioButton(
                selected = state.selectedOption==viewModel.mySelf,
                onClick = { viewModel.mySelf.also { onIntent(AppointmentBookingIntent.SelectOption(it)) } },
                unselectedColor = Color.Transparent,
            ) {
                Text(
                    stringResource(viewModel.mySelf),
                    //                            textAlign = TextAlign.Center,
                    style = LwbdTypography.bodyLarge.copy(
                        fontFamily = Roboto,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        lineHeight = 23.sp,
                    ),
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            CustomRadioButton(
                selected = state.selectedOption == viewModel.someoneElse,
                onClick = { viewModel.someoneElse.also { onIntent(AppointmentBookingIntent.SelectOption(it)) } },
                unselectedColor = Color.Transparent,
            ) {

                Text(
                    stringResource(viewModel.someoneElse),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = Roboto,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        lineHeight = 23.sp,
                    ),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppointmentPatientBottomBar(
    navController: NavHostController,
    scope: CoroutineScope,
    sheetState: SheetState,
    onNextButtonClick: () -> Unit
) {

    Box(
        Modifier
            .systemBarsPadding()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to Color.Gray.copy(.1f),
                        1f to Color.Gray
                    ), tileMode = TileMode.Clamp
                )
            )
            .fillMaxWidth()
            .height(88.dp)
            .padding(top = 2.dp)
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 22.dp, end = 22.dp, top = 15.dp)

                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LwbdTextButton(
                content = {
                    BackButtonContent(text = stringResource(R.string.feature_appointment_booking_back))
                },
                onClick = { navController.popBackStack() },
            )
            LwbdButton(
                content = {
                    Text(
                        text = stringResource(R.string.feature_appointment_booking_next),
                        style = LwbdTypography.bodyLarge
                    )
                },
                onClick = {
                    onNextButtonClick()
                },
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .5f),
                contentColor = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
private fun BackButtonContent(text: String) {
    Column(modifier = Modifier) {

        Text(text = text, style = LwbdTypography.bodyLarge)

        Spacer(modifier = Modifier.height(2.dp))

        // Calculate text width by using BoxWithConstraints and applying IntrinsicSize
        Box(
            modifier = Modifier
                .height(2.dp)
                .width(IntrinsicSize.Min) // Take minimum width based on content
                .background(Color.Green)
        ) {
            Text(
                text, color = Color.Transparent, style = LwbdTypography.bodyLarge
            )
        }
    }
}


@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color = Color.Green,
    unselectedColor: Color = Color(0x2D605858),
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    // Use a Row to arrange the radio button and its content
    Box(
        modifier = Modifier
            .background(color = Color.Transparent)
//            .padding(vertical = 12.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(height = 70.dp)
//            .shadow(shape = RoundedCornerShape(12.dp), elevation = 0.dp)
            .border(
                BorderStroke(
                    width = 1.67.dp,
                    color = when {
                        selected -> BlackColor80
                        else -> GreyColor
                    },
                ),
                shape = RoundedCornerShape(4.dp),
            )
            .clip(RoundedCornerShape(4.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(color = AccentColorLight),
                onClick = { onClick() },
            )
            .background(
                color = when {
                    selected -> WhiteColor657
                    else -> Color.White
                },
            )
            .padding(
                horizontal = 23.dp,
                vertical = 0.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            // Display the content of the radio button
            content()

            // Add spacing between the radio button and its content
//            Spacer(modifier = Modifier.width(32.dp))
            Spacer(
                modifier = Modifier,
            )


            // Create a Box to represent the radio button circle
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(if (selected) selectedColor else unselectedColor)
                    .padding(0.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                // Display a filled circle if selected
                if (selected) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(selectedColor),
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(unselectedColor),
                    )
                }
            }
        }
    }
}
