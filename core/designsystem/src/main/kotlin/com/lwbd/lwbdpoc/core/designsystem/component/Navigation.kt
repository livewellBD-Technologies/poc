

package com.lwbd.lwbdpoc.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.lwbd.lwbdpoc.core.designsystem.icon.LwbdIcons
import com.lwbd.lwbdpoc.core.designsystem.theme.BlackColor80
import com.lwbd.lwbdpoc.core.designsystem.theme.Green90
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTheme
import com.lwbd.lwbdpoc.core.designsystem.theme.WhiteColor
import com.lwbd.lwbdpoc.core.designsystem.theme.WhiteColorFC

/**
 * Lwbd navigation bar item with icon and label content slots. Wraps Material 3
 * [NavigationBarItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param selectedIcon The item icon content when selected.
 * @param enabled controls the enabled state of this item. When `false`, this item will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The item text label content.
 * @param alwaysShowLabel Whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
@Composable
fun RowScope.LwbdNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = LwbdNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = LwbdNavigationDefaults.navigationContentColor(),
            selectedTextColor = LwbdNavigationDefaults.navigationSelectedTextColor(),
            unselectedTextColor = LwbdNavigationDefaults.navigationContentColor(),
            indicatorColor = LwbdNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

/**
 * Lwbd navigation bar with content slot. Wraps Material 3 [NavigationBar].
 *
 * @param modifier Modifier to be applied to the navigation bar.
 * @param content Destinations inside the navigation bar. This should contain multiple
 * [NavigationBarItem]s.
 */
@Composable
fun LwbdNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = LwbdNavigationDefaults.navigationContentColor(),
        containerColor = LwbdNavigationDefaults.navigationContainerColor(),
        tonalElevation = 2.dp,
        content = content,
    )
}

/**
 * Lwbd navigation rail item with icon and label content slots. Wraps Material 3
 * [NavigationRailItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param selectedIcon The item icon content when selected.
 * @param enabled controls the enabled state of this item. When `false`, this item will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The item text label content.
 * @param alwaysShowLabel Whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
@Composable
fun LwbdNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = LwbdNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = LwbdNavigationDefaults.navigationContentColor(),
            selectedTextColor = LwbdNavigationDefaults.navigationSelectedTextColor(),
            unselectedTextColor = LwbdNavigationDefaults.navigationContentColor(),
            indicatorColor = LwbdNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

/**
 * Lwbd navigation rail with header and content slots. Wraps Material 3 [NavigationRail].
 *
 * @param modifier Modifier to be applied to the navigation rail.
 * @param header Optional header that may hold a floating action button or a logo.
 * @param content Destinations inside the navigation rail. This should contain multiple
 * [NavigationRailItem]s.
 */
@Composable
fun LwbdNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = LwbdNavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

/**
 * Lwbd navigation suite scaffold with item and content slots.
 * Wraps Material 3 [NavigationSuiteScaffold].
 *
 * @param modifier Modifier to be applied to the navigation suite scaffold.
 * @param navigationSuiteItems A slot to display multiple items via [LwbdNavigationSuiteScope].
 * @param windowAdaptiveInfo The window adaptive info.
 * @param content The app content inside the scaffold.
 */
@OptIn(
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterial3AdaptiveApi::class,
)
@Composable
fun LwbdNavigationSuiteScaffold(
    navigationSuiteItems: LwbdNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults
        .calculateFromAdaptiveInfo(windowAdaptiveInfo)

    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = LwbdNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = LwbdNavigationDefaults.navigationContentColor(),
            selectedTextColor = LwbdNavigationDefaults.navigationSelectedTextColor(),
            unselectedTextColor = LwbdNavigationDefaults.navigationContentColor(),
            indicatorColor = LwbdNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = LwbdNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = LwbdNavigationDefaults.navigationContentColor(),
            selectedTextColor = LwbdNavigationDefaults.navigationSelectedTextColor(),
            unselectedTextColor = LwbdNavigationDefaults.navigationContentColor(),
            indicatorColor = LwbdNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = LwbdNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = LwbdNavigationDefaults.navigationContentColor(),
            selectedTextColor = LwbdNavigationDefaults.navigationSelectedTextColor(),
            unselectedTextColor = LwbdNavigationDefaults.navigationContentColor(),
        ),

    )

    Box {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                LwbdNavigationSuiteScope(
                    navigationSuiteScope = this,
                    navigationSuiteItemColors = navigationSuiteItemColors,
                ).run(navigationSuiteItems)
            },
            layoutType = layoutType,
            containerColor = LwbdNavigationDefaults.navigationContainerColor(),

            navigationSuiteColors = NavigationSuiteDefaults.colors(
                navigationBarContentColor = LwbdNavigationDefaults.navigationContentColor(),
                navigationBarContainerColor = LwbdNavigationDefaults.navigationNavContainerColor(),

                navigationRailContainerColor = LwbdNavigationDefaults.navigationContainerColor(),

                ),
            modifier = modifier.shadow(elevation = 8.dp),
        ) {
            content()
        }
    }
}

/**
 * A wrapper around [NavigationSuiteScope] to declare navigation items.
 */
@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
class LwbdNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
fun LwbdNavigationBarPreview() {
    val items = listOf("home", "find doctor", "menu")
    val icons = listOf(
        LwbdIcons.UpcomingBorder,
        LwbdIcons.BookmarksBorder,
        LwbdIcons.Grid3x3,
    )
    val selectedIcons = listOf(
        LwbdIcons.Upcoming,
        LwbdIcons.Bookmarks,
        LwbdIcons.Grid3x3,
    )

    LwbdTheme {
        LwbdNavigationBar {
            items.forEachIndexed { index, item ->
                LwbdNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun LwbdNavigationRailPreview() {
    val items = listOf("HOME", "Saved", "Interests")
    val icons = listOf(
        LwbdIcons.UpcomingBorder,
        LwbdIcons.BookmarksBorder,
        LwbdIcons.Grid3x3,
    )
    val selectedIcons = listOf(
        LwbdIcons.Upcoming,
        LwbdIcons.Bookmarks,
        LwbdIcons.Grid3x3,
    )

    LwbdTheme {
        LwbdNavigationRail {
            items.forEachIndexed { index, item ->
                LwbdNavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

/**
 * Lwbd navigation default values.
 */
object LwbdNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = Green90

    @Composable
    fun navigationSelectedTextColor() = BlackColor80

    @Composable
    fun navigationIndicatorColor() = Color.Transparent

    @Composable
    fun navigationContainerColor() = WhiteColorFC

    @Composable
    fun navigationNavContainerColor() = WhiteColor
}
