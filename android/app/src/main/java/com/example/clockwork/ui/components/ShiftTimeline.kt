package com.example.clockwork.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockwork.model.Break
import com.example.clockwork.ui.theme.Blue60
import com.example.clockwork.ui.theme.Blue70
import com.example.clockwork.ui.theme.Blue80
import com.example.clockwork.ui.theme.Blue90
import com.example.clockwork.ui.theme.Blue95
import com.example.clockwork.ui.theme.BluePrimary
import com.example.clockwork.ui.theme.ClockworkTheme
import com.example.clockwork.ui.theme.DMSansFontFamily
import com.example.clockwork.ui.theme.SoftGray
import com.example.clockwork.utils.DateUtils
import java.util.Date

@Composable
fun ShiftTimeline(startTime: Date, endTime: Date, breaks: List<Break> = listOf(), currentTime: Date = Date())
{
    ClockworkTheme {
        Column {
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)) {

                val shiftRectYOffset = 6.dp.toPx()
                val shiftRectSize = Size(width = size.width, height = size.height - shiftRectYOffset)
                val cornerRadius = 8.dp.toPx()
                val currentTimeInnerCircleRadius = 3.dp.toPx()
                val currentTimeOuterCircleRadius = 6.dp.toPx()
                val currentTimeStrokeWidth = 3.dp.toPx()

                drawRoundRect(
                    topLeft = Offset(0f, shiftRectYOffset),
                    color = Blue90,
                    size = shiftRectSize,
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )

                if (DateUtils.isDateInRange(currentTime, startTime, endTime)) {
                    val percentComplete = DateUtils.percentCompleted(currentTime, startTime, endTime)
                    // draw past/future time
                    drawRoundRect(
                        topLeft = Offset(0f, shiftRectYOffset),
                        color = Blue70,
                        size = Size(
                            width = size.width * percentComplete,
                            height = size.height - shiftRectYOffset
                        ),
                        cornerRadius = CornerRadius(cornerRadius),
                    )
                    // TODO: huh? try not to draw rect on top of corner radius
                    drawRect(
                        topLeft = Offset(size.width * percentComplete - 24f, shiftRectYOffset),
                        color = Blue70,
                        size = Size(
                            width = 9.dp.toPx(),
                            height = size.height - shiftRectYOffset),
                        )

                    // draw breaks
                    for (shiftBreak in breaks) {
                        val breakStartPercentage = DateUtils.percentCompleted(shiftBreak.displayStartTime(), startTime, endTime)
                        val breakEndPercentage = DateUtils.percentCompleted(shiftBreak.displayEndTime(), startTime, endTime)

                        // Create a Path to represent the rounded rectangle with the y-offset
                        val shiftPath = Path().apply {
                            addRoundRect(androidx.compose.ui.geometry.RoundRect(
                                rect = Rect(0f, shiftRectYOffset, shiftRectSize.width, shiftRectSize.height + shiftRectYOffset),
                                cornerRadius = CornerRadius(30f, 30f)
                            ))
                        }

                        clipPath(shiftPath) {
                            drawRect(
                                topLeft = Offset(size.width * breakStartPercentage, shiftRectYOffset),
                                color = if (shiftBreak.displayStartTime().before(currentTime)) Blue80 else Blue95,
                                size = Size(
                                    width = (breakEndPercentage - breakStartPercentage) * size.width,
                                    height = size.height - shiftRectYOffset
                                )
                            )

                            if (DateUtils.isBetween(currentTime, shiftBreak.displayStartTime(), shiftBreak.displayEndTime())) {
                                drawRect(
                                    topLeft = Offset(size.width * percentComplete, shiftRectYOffset),
                                    color = Blue95,
                                    size = Size(
                                        width = (breakEndPercentage - percentComplete) * size.width,
                                        height = size.height - shiftRectYOffset
                                    )
                                )
                            }
                        }
                    }

                    // draw current time
                    drawLine(
                        color = Blue60,
                        start = Offset(size.width * percentComplete, shiftRectYOffset),
                        end = Offset(size.width * percentComplete, size.height),
                        strokeWidth = currentTimeStrokeWidth
                    )
                    drawCircle(
                        center = Offset(size.width * percentComplete, shiftRectYOffset),
                        color = BluePrimary,
                        radius = currentTimeOuterCircleRadius
                    )
                    drawCircle(
                        center = Offset(size.width * percentComplete, shiftRectYOffset),
                        color = Color.White,
                        radius = currentTimeInnerCircleRadius
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = DateUtils.formatTime(startTime),
                    fontFamily = DMSansFontFamily,
                    fontSize = 12.sp,
                    color = SoftGray,
                )
                Text(
                    text = DateUtils.formatTime(endTime),
                    fontFamily = DMSansFontFamily,
                    fontSize = 12.sp,
                    color = SoftGray,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShiftTimelinePreview() {
    val break1 = Break(DateUtils.time(5), DateUtils.time(5, 10))
    val break2 = Break(DateUtils.time(6), DateUtils.time(6, 45))
    val break3 = Break(DateUtils.time(16), DateUtils.time(16, 30))
    val break4 = Break(DateUtils.time(13, 45), DateUtils.time(14, 15))

    ClockworkTheme {
        ShiftTimeline(startTime = DateUtils.time(5), endTime = DateUtils.time(17), currentTime = DateUtils.time(14),
            breaks = listOf(break1, break2, break3, break4)
        )
    }
}