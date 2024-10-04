package com.example.clockwork.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockwork.ui.theme.ClockworkTheme
import com.example.clockwork.ui.theme.DMSansFontFamily
import com.example.clockwork.ui.theme.SoftBlack
import com.example.clockwork.utils.DateUtils
import java.util.Date

@SuppressLint("DefaultLocale")
@Composable
fun ShiftDescription(startTime: Date, endTime: Date, payRate: Float? = null, breakHours: Float = 0f)
{
    var shiftText = DateUtils.formatTime(startTime) + " - " + DateUtils.formatTime(endTime)

    payRate?.let {
        val pay = DateUtils.hoursBetween(startTime, endTime) * payRate - breakHours * payRate
        val payText = String.format("%.2f", pay)
        if (pay > 0) {
            shiftText += " · $$payText"
        }
    }

    Text(
        text = shiftText,
        style = TextStyle(fontSize = 14.sp),
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.Medium,
        color = SoftBlack,
    )
}

@Preview(showBackground = true)
@Composable
fun ShiftDescriptionPreview() {
    ClockworkTheme {
        Column {
            ShiftDescription(DateUtils.time(9), DateUtils.time(17))
            Spacer(modifier = Modifier.height(8.dp))
            ShiftDescription(startTime = DateUtils.time(9), endTime = DateUtils.time(17), payRate = 9.50f)
            Spacer(modifier = Modifier.height(8.dp))
            ShiftDescription(startTime = DateUtils.time(9), endTime = DateUtils.time(17), payRate = 9.50f, breakHours = 1f)
        }
    }
}