package com.writer.app.design.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/** Cards 20-28dp, buttons 16-22dp as per the design brief. */
val WriterShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

object GlassCorners {
    val card = RoundedCornerShape(24.dp)
    val cardLarge = RoundedCornerShape(28.dp)
    val button = RoundedCornerShape(20.dp)
    val chip = RoundedCornerShape(18.dp)
    val field = RoundedCornerShape(16.dp)
    val sheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
}
