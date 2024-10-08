package com.lwbd.lwbdpoc.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lwbd.lwbdpoc.core.designsystem.R


@Composable
fun IconWithBadge(
    iconResId: Int,
    badgeCount: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp), // Adjust the size as needed
        contentAlignment = Alignment.Center // Align the badge at the top right
    ) {
        // The Icon

        Icon(

            painter = painterResource(id = iconResId),
            contentDescription = "Icon with badge",

            modifier = Modifier
                .size(22.dp),// Adjust the size as needed),
            tint = Color.Unspecified // If you want the original icon colors
        )


        // Badge
        if (badgeCount > 0) {
            // Shadow radius in px
            Box(
                modifier = Modifier
                    .size(29.dp) // Badge size

                    .background(
                        shape = CircleShape,

                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.Red, // Inner color (center)
                                Color.Red,
                                Color.Red,
                                Color.Red,
                                Color.Red,
                                Color.Red,
                                Color.Red,
                                Color.Red,
                                Color.Red,
                                Color.Red,
                                Color.Red,
                                Color.Red.copy(alpha = .4f),
                                Color.Red.copy(alpha = .1f),
                                Color.Red.copy(alpha = .01f),
                                Color.Red.copy(alpha = 0f), // Outer color (edge)
                            ),
                            tileMode = TileMode.Clamp,
                            radius = 29f // Radius of the gradient
                        )
                    )

                    // Badge background color
                    .align(Alignment.TopEnd), // Position badge at the top-end corner
                contentAlignment = Alignment.Center // Align text inside the badge
            ) {
                Text(
                    text = badgeCount.toString(),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIconWithBadge() {
    IconWithBadge(
        iconResId = R.drawable.core_designsystem_notfication_icon, // Replace with your own icon resource
        badgeCount = 5, // Replace with the badge count
        modifier = Modifier.size(48.dp) // Optional: Set size for the icon with badge
    )
}