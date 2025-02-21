package uz.aigroup.trustiddemo.ui.designsystem.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(1.dp),
    small = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(15.dp),
    extraLarge = RoundedCornerShape(20.dp)
)

val BottomBarContentPadding = PaddingValues(
    start = 20.dp,
    end = 20.dp,
    top = 20.dp,
    bottom = 132.dp
)