package com.neepan.boockai.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.neepan.boockai.core.designsystem.R

/**
 * Boock typography system — uses Plus Jakarta Sans for UI
 * and Literata for reader body text (from Figma).
 */

val PlusJakartaSans = FontFamily(
    Font(R.font.plus_jakarta_sans_regular, FontWeight.Normal),
    Font(R.font.plus_jakarta_sans_medium, FontWeight.Medium),
    Font(R.font.plus_jakarta_sans_semibold, FontWeight.SemiBold),
    Font(R.font.plus_jakarta_sans_bold, FontWeight.Bold)
)

val Literata = FontFamily(
    Font(R.font.literata_regular, FontWeight.Normal)
)

val BoockTypography = Typography(
    // Display / H1 - App title "Boock"
    headlineLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.025).sp
    ),
    // H2 - Section headers, book titles on cards
    headlineMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    // H3 - Book title on cards
    titleLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.025).sp
    ),
    // Body - Summary text (Literata)
    bodyLarge = TextStyle(
        fontFamily = Literata,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 29.25.sp
    ),
    // Reader body text
    bodyMedium = TextStyle(
        fontFamily = Literata,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.025.sp
    ),
    // Author name on cards
    bodySmall = TextStyle(
        fontFamily = Literata,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    // Buttons, filter tabs
    labelLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.0071.sp
    ),
    // Badges, small labels
    labelSmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.0417.sp
    )
)
