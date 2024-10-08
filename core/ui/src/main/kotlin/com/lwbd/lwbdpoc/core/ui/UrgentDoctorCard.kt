package com.lwbd.lwbdpoc.core.ui


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavOptions
import coil.compose.AsyncImage
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdButton
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTheme
import com.lwbd.lwbdpoc.core.designsystem.theme.Roboto
import com.lwbd.lwbdpoc.core.model.data.UrgentDoctor
import kotlin.reflect.KFunction1

/**
 * An extension on [LazyListScope] defining a feed with news resources.
 * Depending on the [urgentDoctorState], this might emit no items.
 */
@OptIn(ExperimentalFoundationApi::class)
fun LazyStaggeredGridScope.urgentDoctorCard(
    urgentDoctorState: UrgentDoctorUiState,
    onCallNowClicked: KFunction1<NavOptions?, Unit>? = null,
    backgroundImage: Painter,
) {
    when (urgentDoctorState) {
        UrgentDoctorUiState.Loading -> item {
            val configuration = LocalConfiguration.current
            val screenWidthDp = configuration.screenWidthDp.dp
            val screenWidth = configuration.screenWidthDp
            Box( modifier = Modifier
                .width(screenWidthDp)
                .height((screenWidth * .78133).dp)
                .background(Color.Black.copy(alpha = .1f)), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        is UrgentDoctorUiState.Success -> {
            item(span = StaggeredGridItemSpan.FullLine, contentType = "urgentDoctor") {
                val configuration = LocalConfiguration.current
                val screenWidthDp = configuration.screenWidthDp.dp
                val screenWidth = configuration.screenWidthDp

                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            fontFamily = Roboto,
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xff2B1A52),
                                    Color(0xff2B1A52)
                                )
                            )
                        )
                    ) {
                        append(stringResource(R.string.core_ui_card_consult_a_doctor))
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            fontFamily = Roboto,

                            brush = Brush.linearGradient(colors = listOf(Color.White, Color.White))

                        )
                    ) {
                        append(" ৳${urgentDoctorState.urgentDoctor.fee}")
                    }
                }

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {

                    AsyncImage(
                        model = urgentDoctorState.urgentDoctor.specializations?.bannerUrlSqr?:"",
                        modifier = Modifier
                            .width(screenWidthDp)
                            .height((screenWidth * .78133).dp)
                            .background(Color.Black.copy(alpha = .3f)),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Box(contentAlignment = Alignment.CenterStart) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(.65f),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                annotatedString,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                                style = MaterialTheme.typography.titleMedium.copy(lineHeight = 36.sp),
                            )

                            Spacer(Modifier.height(32.dp))
                            Column(modifier = Modifier.padding(start = 24.dp)) {
                                Text(
                                    stringResource(R.string.core_ui_card_instantly_consult_a),
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = Color(0xff2B1A52),
                                        lineHeight = 23.sp,
                                    ),
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    urgentDoctorState.urgentDoctor.specializations?.name ?: "",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = Color.White,
                                        lineHeight = 23.sp
                                    ),
                                )
                            }
                            Spacer(Modifier.height(32.dp))
                            LwbdButton(
                                modifier = Modifier
                                    .height(42.dp)

                                    .padding(start = 24.dp),
                                onClick = { onCallNowClicked?.invoke(null) },
                            ) {
                                Text(
                                    text = stringResource(R.string.core_ui_card_call_now),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }


                        }

                    }
                }
            }

        }
    }
}

//fun launchCustomChromeTab(context: Context, uri: Uri, @ColorInt toolbarColor: Int) {
//    val customTabBarColor = CustomTabColorSchemeParams.Builder()
//        .setToolbarColor(toolbarColor).build()
//    val customTabsIntent = CustomTabsIntent.Builder()
//        .setDefaultColorSchemeParams(customTabBarColor)
//        .build()
//
//    customTabsIntent.launchUrl(context, uri)
//}

/**
 * A sealed hierarchy describing the state of the feed of urgent doctor.
 */
sealed interface UrgentDoctorUiState {
    /**
     * The feed is still loading.
     */
    data object Loading : UrgentDoctorUiState

    /**
     * The feed is loaded with the given urgent.
     */
    data class Success(
        /**
         * The urgent doctor contained in this feed.
         */
        val urgentDoctor: UrgentDoctor,
    ) : UrgentDoctorUiState
}


@Preview
@Composable
private fun UrgentDoctorLoadingPreview() {
    val backgroundImage: Painter = painterResource(id = R.drawable.core_ui_doctor_2_1)
    LwbdTheme {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(300.dp)) {
            urgentDoctorCard(
                urgentDoctorState = UrgentDoctorUiState.Loading,
                backgroundImage = backgroundImage,
                onCallNowClicked = null,
            )
        }
    }
}


