package com.example.clockwork.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockwork.model.Break
import com.example.clockwork.ui.theme.BarButtonType
import com.example.clockwork.ui.theme.ClockworkTheme
import com.example.clockwork.ui.theme.DMSansFontFamily
import com.example.clockwork.ui.theme.GilroyFontFamily
import com.example.clockwork.ui.theme.SoftBlack
import com.example.clockwork.utils.DateUtils
import com.example.clockwork.R
import java.util.Date

@Composable
fun ClockInDashboard(isActive: Boolean, shiftStartTime: Date, shiftEndTime: Date, payRate: Float = 0f,
                     breaks: List<Break> = emptyList(), position: String? = null) {

    val shiftState = if (isActive) "Active shift" else "Upcoming shift"
    Column {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = shiftState,
                style = TextStyle(fontSize = 16.sp),
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.Bold,
                color = SoftBlack,
                modifier = Modifier.weight(1f),
            )
            position?.let {
                Text(
                    text = position,
                    fontFamily = DMSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = SoftBlack,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        ShiftDescription(shiftStartTime, shiftEndTime, payRate)
        Spacer(modifier = Modifier.height(16.dp))
        ShiftBreakTracker(onShiftMinutes = 90, onBreakMinutes = 10) // TODO
        ShiftTimeline(startTime = shiftStartTime, endTime = shiftEndTime)
        Spacer(modifier = Modifier.height(8.dp))
        ButtonBar(
            listOf(
                BarButton(
                    onClick = {
                        Log.d("LogButton", "Back to Work button pressed")
                    },
                    text = "Back to work",
                    iconId = R.drawable.outline_work_outline_24
                ),
                BarButton(
                    onClick = {
                        Log.d("LogButton", "Back to Work button pressed")
                    },
                    text = "End shift",
                    type = BarButtonType.Secondary
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeClockInPreview() {
    ClockworkTheme {
        ClockInDashboard(true, DateUtils.time(8), DateUtils.time(16), 10.0f,
            emptyList(), "Technician")
    }
}