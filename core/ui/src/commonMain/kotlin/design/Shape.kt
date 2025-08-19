package org.pointyware.painteddogs.core.ui.design

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object GeometryTokens {
    val dpExtraSmall = 2.dp
    val dpSmall = 4.dp
    val dpMedium = 8.dp
    val dpLarge = 16.dp
    val dpExtraLarge = 32.dp
}

/**
 * Defines geometry primitives and shapes for Painted Dogs
 */
val shapes = Shapes(
    extraSmall = RoundedCornerShape(GeometryTokens.dpExtraSmall),
    small = RoundedCornerShape(GeometryTokens.dpSmall),
    medium = RoundedCornerShape(GeometryTokens.dpMedium),
    large = RoundedCornerShape(GeometryTokens.dpLarge),
    extraLarge = RoundedCornerShape(GeometryTokens.dpExtraLarge)
)

data class Geometry(
    val paddingSmall: PaddingValues,
    val paddingMedium: PaddingValues,
    val paddingLarge: PaddingValues,
    val marginSmall: Dp,
    val marginMedium: Dp,
    val marginLarge: Dp,
)

val DefaultLocalGeometry = Geometry(
    paddingSmall = PaddingValues(horizontal = GeometryTokens.dpMedium, vertical = GeometryTokens.dpMedium),
    paddingMedium = PaddingValues(horizontal = GeometryTokens.dpLarge, vertical = GeometryTokens.dpLarge),
    paddingLarge = PaddingValues(horizontal = GeometryTokens.dpExtraLarge, vertical = GeometryTokens.dpExtraLarge),
    marginSmall = GeometryTokens.dpMedium,
    marginMedium = GeometryTokens.dpLarge,
    marginLarge = GeometryTokens.dpExtraLarge,
)

val LocalGeometry = compositionLocalOf<Geometry> {
    throw IllegalStateException("Geometry not provided.")
}
