package org.pointyware.painteddogs.core.ui.design

/**
 * Defines typography for Painted Dogs
 */

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TextStyleTokens {
    val weightExtraLight = FontWeight(200)
    val weightLight = FontWeight(300)
    val weightMedium = FontWeight(500)
    val weightHeavy = FontWeight(700)
    val weightExtraHeavy = FontWeight(800)
}

/**
 * { display, headline, title, body, label }
 * x
 * { large, medium, small }
 */
val typography = Typography(
    displayLarge = TextStyle(
        fontSize = 48.sp,
        fontWeight = TextStyleTokens.weightExtraHeavy
    ),
    headlineLarge = TextStyle(
        fontSize = 36.sp,
        fontWeight = TextStyleTokens.weightHeavy
    ),
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    bodyLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    labelLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),

    displayMedium = TextStyle(
        fontSize = 36.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    headlineMedium = TextStyle(
        fontSize = 32.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    titleMedium = TextStyle(
        fontSize = 20.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),

    displaySmall = TextStyle(
        fontSize = 30.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    headlineSmall = TextStyle(
        fontSize = 24.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    titleSmall = TextStyle(
        fontSize = 18.sp,
        fontWeight = TextStyleTokens.weightMedium
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = TextStyleTokens.weightLight
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = TextStyleTokens.weightExtraLight
    ),
)
