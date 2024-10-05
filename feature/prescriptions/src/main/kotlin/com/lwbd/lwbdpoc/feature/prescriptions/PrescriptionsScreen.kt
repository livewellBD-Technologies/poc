package com.lwbd.lwbdpoc.feature.prescriptions

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwbd.lwbdpoc.core.designsystem.component.LwbdLoadingWheel
import com.lwbd.lwbdpoc.core.designsystem.theme.LocalTintTheme
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTheme


@Composable
internal fun PrescriptionsRoute(
    modifier: Modifier = Modifier,
    ) {

    PrescriptionsScreen(
        modifier = modifier,
        )
}

/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun PrescriptionsScreen(
    modifier: Modifier = Modifier,
) {

    EmptyState(modifier)

}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    LwbdLoadingWheel(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .testTag("home:loading"),
        contentDesc = stringResource(id = R.string.feature_prescriptions_loading),
    )
}



@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .testTag("bookmarks:empty"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val iconTint = LocalTintTheme.current.iconTint
        Image(
            modifier = Modifier.requiredSize(160.dp),
            painter = painterResource(id = com.lwbd.lwbdpoc.core.designsystem.R.drawable.core_designsystem_prescription),
            colorFilter = if (iconTint != Color.Unspecified) ColorFilter.tint(iconTint) else null,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(id = R.string.feature_prescriptions_empty_error),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.feature_prescriptions_empty_description),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun LoadingStatePreview() {
    LwbdTheme {
        LoadingState()
    }
}

//@Preview
//@Composable
//private fun PresentationsGridPreview(
//    @PreviewParameter(UserNewsResourcePreviewParameterProvider::class)
//    userNewsResources: List<UserNewsResource>,
//) {
//    LwbdTheme {
//        PrescriptionsGrid(
//            feedState = Success(userNewsResources),
//            removeFromBookmarks = {},
//            onNewsResourceViewed = {},
//            onTopicClick = {},
//        )
//    }
//}

@Preview
@Composable
private fun EmptyStatePreview() {
    LwbdTheme {
        EmptyState()
    }
}
