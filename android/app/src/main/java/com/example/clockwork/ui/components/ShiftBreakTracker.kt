package com.example.clockwork.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockwork.ui.theme.ClockworkTheme
import com.example.clockwork.ui.theme.DMSansFontFamily
import com.example.clockwork.ui.theme.SoftBlack
import com.example.clockwork.ui.theme.SoftGray

@Composable
fun ShiftBreakTracker(onShiftMinutes: Int, onBreakMinutes: Int? = null)
{
    val shiftHours = onShiftMinutes / 60
    val shiftMinutes = onShiftMinutes % 60

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f)) {
            Row {
                Text(
                    text = "$shiftHours",
                    fontFamily = DMSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp,
                    color = SoftBlack,
                    modifier = Modifier.alignByBaseline()
                )
                Text(
                    text = "h",
                    fontFamily = DMSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = SoftBlack,
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(end = 4.dp)
                )
                Text(
                    text = "$shiftMinutes",
                    fontFamily = DMSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp,
                    color = SoftBlack,
                    modifier = Modifier.alignByBaseline()
                )
                Text(
                    text = "m",
                    fontFamily = DMSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = SoftBlack,
                    modifier = Modifier.alignByBaseline()
                )
            }
            Text(
                text = "On shift",
                fontFamily = DMSansFontFamily,
                fontSize = 13.sp,
                color = SoftGray
            )
        }

        onBreakMinutes?.let {
            val breakHours = onBreakMinutes / 60
            val breakMinutes = onBreakMinutes % 60

            Column(modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp))  {
                Row {
                    if (breakHours > 0) {
                        Text(
                            text = "$breakHours",
                            fontFamily = DMSansFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 27.sp,
                            color = SoftBlack,
                            modifier = Modifier.alignByBaseline()
                        )
                        Text(
                            text = "h",
                            fontFamily = DMSansFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = SoftBlack,
                            modifier = Modifier.alignByBaseline()
                        )
                    }
                    Text(
                        text = "$breakMinutes",
                        fontFamily = DMSansFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(
                        text = "m",
                        fontFamily = DMSansFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.alignByBaseline()
                    )
                }
                Text(
                    text = "On break",
                    fontFamily = DMSansFontFamily,
                    fontSize = 13.sp,
                    color = SoftGray,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShiftBreakTrackerPreview() {
    ClockworkTheme {
        Column {
            ShiftBreakTracker(onShiftMinutes = 0, onBreakMinutes = 0)
            Spacer(modifier = Modifier.height(8.dp))
            ShiftBreakTracker(onShiftMinutes = 90, onBreakMinutes = 30)
            Spacer(modifier = Modifier.height(8.dp))
            ShiftBreakTracker(onShiftMinutes = 275)
        }
    }
}