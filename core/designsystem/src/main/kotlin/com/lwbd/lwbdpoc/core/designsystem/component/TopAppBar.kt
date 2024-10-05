@file:OptIn(ExperimentalMaterial3Api::class)

package com.lwbd.lwbdpoc.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwbd.lwbdpoc.core.designsystem.R as designR
import com.lwbd.lwbdpoc.core.designsystem.icon.LwbdIcons
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LwbdTopAppBar(
    @StringRes titleRes: Int,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String,
    actionIcon:  Int,
    actionIconContentDescription: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(

        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {


                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {


            IconWithBadge(
                iconResId = actionIcon,
                badgeCount = 10
            )

            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(designR.drawable.core_designsystem_display_picture),
                contentDescription = null, // Content description can be null for decorative images
                modifier = Modifier.size(38.dp),
                contentScale = ContentScale.Crop, // Crop the image to fit the Box
            )
        },
        colors = colors,
        modifier = modifier
            .padding(start = 8.dp, end = 22.dp)
            .testTag("lwbdTopAppBar"),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun LwbdTopAppBarPreview() {
    LwbdTheme {
        LwbdTopAppBar(
            titleRes = android.R.string.untitled,
            navigationIcon = LwbdIcons.Search,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = designR.drawable.core_designsystem_notfication_icon,
            actionIconContentDescription = "Action icon",
        )
    }
}
