package ru.shumskii.timewidget.ui.components.time_view

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

@Composable
fun TimeView(
    state: TimeViewState,
    modifier: GlanceModifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = state.time,
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 48.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            ),
            maxLines = 1,
        )
        Text(
            text = state.day,
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            ),
            maxLines = 1,
        )
    }
}