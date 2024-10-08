package com.lwbd.lwbdpoc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.lwbd.lwbdpoc.R
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdBackground
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdGradientBackground
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdNavigationSuiteScaffold
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdTopAppBar
import com.lwbd.lwbdpoc.core.designsystem.theme.GradientColors
import com.lwbd.lwbdpoc.core.designsystem.theme.LocalGradientColors
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTypography
import com.lwbd.lwbdpoc.feature.finddoctor.navigation.FIND_DOCTOR_ROUTE
import com.lwbd.lwbdpoc.feature.home.navigation.HOME_ROUTE
import com.lwbd.lwbdpoc.feature.menu.navigation.MENU_ROUTE
import com.lwbd.lwbdpoc.feature.prescriptions.navigation.PRESCRIPTIONS_ROUTE
import com.lwbd.lwbdpoc.navigation.LwbdNavHost
import com.lwbd.lwbdpoc.navigation.TopLevelDestination
import com.lwbd.lwbdpoc.core.designsystem.R as designSystemR


@Composable
fun LwbdApp(
    appState: LwbdAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val shouldShowGradientBackground =
        appState.currentTopLevelDestination == TopLevelDestination.HOME
    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

    LwbdBackground(modifier = modifier) {
        LwbdGradientBackground(
            gradientColors = if (shouldShowGradientBackground) {
                LocalGradientColors.current
            } else {
                GradientColors()
            },
        ) {
            val snackbarHostState = remember { SnackbarHostState() }

            val isOffline by appState.isOffline.collectAsStateWithLifecycle()

            // If user is not connected to the internet show a snack bar to inform them.
            val notConnectedMessage = stringResource(R.string.not_connected)
            LaunchedEffect(isOffline) {
                if (isOffline) {
                    snackbarHostState.showSnackbar(
                        message = notConnectedMessage,
                        duration = Indefinite,
                    )
                }
            }

            LwbdApp(
                appState = appState,
                snackbarHostState = snackbarHostState,
                showSettingsDialog = showSettingsDialog,
                onSettingsDismissed = { showSettingsDialog = false },
                onTopAppBarActionClick = { showSettingsDialog = true },
                windowAdaptiveInfo = windowAdaptiveInfo,
            )
        }
    }
}

@Composable
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
)
internal fun LwbdApp(
    appState: LwbdAppState,
    snackbarHostState: SnackbarHostState,
    showSettingsDialog: Boolean,
    onSettingsDismissed: () -> Unit,
    onTopAppBarActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {


    // List of routes where the NavigationSuite should be shown
    val showNavigationSuiteRoutes = listOf(HOME_ROUTE, FIND_DOCTOR_ROUTE, PRESCRIPTIONS_ROUTE, MENU_ROUTE)

    val currentDestination = appState.currentDestination
    val currentRoute = appState.currentDestination?.route ?: ""
    if (currentRoute in showNavigationSuiteRoutes) {
        LwbdNavigationSuiteScaffold(
            modifier = Modifier
                .testTag("LwbdNavigationSuiteScaffold")
                .background(Color.White)
                .shadow(elevation = 2.dp),
            navigationSuiteItems = {
                appState.topLevelDestinations.forEach { destination ->

                    val selected = currentDestination
                        .isTopLevelDestinationInHierarchy(destination)
                    item(
                        selected = selected,
                        onClick = { appState.navigateToTopLevelDestination(destination) },
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(18.dp),
                                imageVector = ImageVector.vectorResource(destination.selectedIconResId),
                                contentDescription = null,
                            )
                        },
                        selectedIcon = {
                            Icon(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(18.dp),
                                imageVector = ImageVector.vectorResource(destination.selectedIconResId),
                                contentDescription = null,

                                )
                        },
                        label = {
                            Text(
                                stringResource(destination.iconTextId),
                                style = LwbdTypography.bodySmall.copy(
                                    fontSize = 10.sp,
                                    fontWeight = when (selected) {
                                        true -> FontWeight.W700
                                        else -> FontWeight.W400
                                    },
                                    lineHeight = 15.sp,
                                ),
                            )
                        },
                        modifier =
                        Modifier
                            .testTag("LwbdNavItem")
                            .then(Modifier),
                    )
                }
            },
            windowAdaptiveInfo = windowAdaptiveInfo,
        ) {
            Scaffold(
                modifier = modifier.semantics {
                    testTagsAsResourceId = true
                },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = { SnackbarHost(snackbarHostState) },
            ) { padding ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        ),
                ) {
                    // Show the top app bar on top level destinations.
                    val showBarsRoutes =
                        listOf("home_route", "find_doctor_route", "prescriptions_route")
                    val currentTopLevelDestination = appState.currentTopLevelDestination

                    val destination = appState.currentDestination?.route ?: ""

                    val shouldShowTopAppBar = (destination in showBarsRoutes)
                    if (shouldShowTopAppBar) {
                        LwbdTopAppBar(
                            titleRes = currentTopLevelDestination?.titleTextId ?: R.string.app_name,
                            navigationIcon = ImageVector.vectorResource(com.lwbd.lwbdpoc.core.designsystem.R.drawable.core_designsystem_search_icon),
                            navigationIconContentDescription = stringResource(
                                id = R.string.app_name,
                            ),
                            actionIcon = designSystemR.drawable.core_designsystem_notfication_icon,
                            actionIconContentDescription = stringResource(
                                id = R.string.app_name,
                            ),
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.Transparent,
                            ),
                            onActionClick = { onTopAppBarActionClick() },
                            onNavigationClick = { },
                            modifier = Modifier.testTag("LwbdTopAppBar"),
                        )
                    }

                    Box(
                        // Workaround for https://issuetracker.google.com/338478720
                        modifier = Modifier.consumeWindowInsets(
                            if (shouldShowTopAppBar) {
                                WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                            } else {
                                WindowInsets(0, 0, 0, 0)
                            },
                        ),
                    ) {
                        LwbdNavHost(
                            appState = appState,
                            onShowSnackbar = { message, action ->
                                snackbarHostState.showSnackbar(
                                    message = message,
                                    actionLabel = action,
                                    duration = Short,
                                ) == ActionPerformed
                            },
                        )
                    }

                    // TODO: We may want to add padding or spacer when the snackbar is shown so that
                    //  content doesn't display behind it.
                }
            }
        }
    } else{

        LwbdNavHost(
            appState = appState,
            onShowSnackbar = { message, action ->
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = action,
                    duration = Short,
                ) == ActionPerformed
            },
        )
    }
}

private fun Modifier.notificationDot(): Modifier =
    composed {
        val tertiaryColor = MaterialTheme.colorScheme.tertiary
        drawWithContent {
            drawContent()
            drawCircle(
                tertiaryColor,
                radius = 5.dp.toPx(),
                // This is based on the dimensions of the NavigationBar's "indicator pill";
                // however, its parameters are private, so we must depend on them implicitly
                // (NavigationBarTokens.ActiveIndicatorWidth = 64.dp)
                center = center + Offset(
                    64.dp.toPx() * .45f,
                    32.dp.toPx() * -.45f - 6.dp.toPx(),
                ),
            )
        }
    }

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
