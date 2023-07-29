package presentation.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import presentation.theme.Color

@Composable
fun ErrorScreen(message: String) {
    var rotationAngle by remember { mutableStateOf(0f) }

    val rotation = remember {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000,
                    easing = LinearEasing
                )
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Canvas(
                modifier = Modifier.size(48.dp),
                onDraw = {
                    val strokeWidth = size.width / 16f
                    drawCircle(
                        color = Color.Danger,
                        radius = size.minDimension / 2 - strokeWidth / 2,
                        center = Offset(size.width / 2, size.height / 2),
                        style = Stroke(width = strokeWidth)
                    )
                    drawArc(
                        color = Color.secondary,
                        startAngle = rotation.value.toDouble().toFloat() - 90,
                        sweepAngle = 60f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth),
                        topLeft = Offset(strokeWidth, strokeWidth),
                        size = Size(size.width - strokeWidth * 2, size.height - strokeWidth * 2)
                    )
                }
            )
            Text(
                text = "Error",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = message,
                style = MaterialTheme.typography.body1,
                color = Color.Background,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}