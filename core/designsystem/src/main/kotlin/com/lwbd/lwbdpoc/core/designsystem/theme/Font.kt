package com.lwbd.lwbdpoc.core.designsystem.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.lwbd.lwbdpoc.core.designsystem.R

// CREATE THE FONT FAMILY
val Roboto = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.roboto_regular, FontWeight.Normal, FontStyle.Normal),
//    Font(R.font.roboto_bold, FontWeight.SemiBold, FontStyle.Normal),
)

val Exo = FontFamily(
    Font(R.font.exo_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.exo_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.exo_regular, FontWeight.Normal, FontStyle.Normal),
)

