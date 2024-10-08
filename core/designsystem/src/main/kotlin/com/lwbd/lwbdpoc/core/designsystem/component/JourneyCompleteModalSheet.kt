package com.lwbd.lwbdpoc.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lwbd.lwbdpoc.core.designsystem.R
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTypography
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JourneyCompleteModalSheet(
    onCloseButtonClick: () -> Unit,
    sheetState: SheetState, // Controls the state of the bottom sheet
    scope: CoroutineScope, // CoroutineScope to show/hide the bottom sheet
    onDismiss: () -> Unit, // Callback to be invoked when the bottom sheet is dismissed // State to toggle the visibility of the bottom sheet
) {
    ModalBottomSheet(
        shape = RoundedCornerShape(0.dp),
        dragHandle = null,

        onDismissRequest = onDismiss, // Hide the bottom sheet when dismissed
        sheetState = sheetState // Control the bottom sheet's state
    ) {
        // Bottom sheet content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp, top = 32.dp),


            horizontalAlignment = Alignment.Start

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 0.dp, end = 0.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(stringResource(R.string.core_designsystem_completed_journey), style = LwbdTypography.bodyLarge)
                IconButton(onClick = {
                    onCloseButtonClick()
                },
                    Modifier
                        .size(30.dp)
                        .testTag("CloseButton")) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(stringResource(R.string.core_designsystem_this_is_the_end_of_the_journey), style = LwbdTypography.bodySmall)
            Spacer(modifier = Modifier.height(104.dp))

        }
    }
}