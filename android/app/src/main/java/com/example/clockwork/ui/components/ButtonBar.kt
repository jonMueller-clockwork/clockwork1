package com.example.clockwork.ui.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button


import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockwork.ui.theme.BarButtonType
import com.example.clockwork.ui.theme.ClockworkTheme
import com.example.clockwork.ui.theme.DMSansFontFamily

data class BarButton(val onClick: () -> Unit, val text: String, val type: BarButtonType = BarButtonType.Primary,
                     @DrawableRes val iconId: Int? = null, val contentDescription: String? = null)

@Composable
fun ButtonBar(buttons: List<BarButton>) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        for (button in buttons) {
            val border =
                if (button.type == BarButtonType.Secondary) BorderStroke(2.dp, Color(0xFFE9E9E9)) else null

            Button(
                modifier = Modifier.weight(1f).height(44.dp),
                onClick = button.onClick,
                shape = RoundedCornerShape(64.dp),
                contentPadding = PaddingValues(8.dp),
                colors = button.type.buttonColors,
                border = border
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    button.iconId?.let {
                        val description =
                            if (button.contentDescription.isNullOrEmpty()) button.text else button.contentDescription
                        Icon(
                            painter = painterResource(id = button.iconId),
                            contentDescription = description
                        )
                    }
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = button.text,
                        fontFamily = DMSansFontFamily,
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonBarPreview() {
    ClockworkTheme {
        ButtonBar(
            listOf(
                BarButton(
                    text = "Start shift",
                    onClick = {
                        Log.d("LogButton", "button press")
                    },
                    iconId = android.R.drawable.ic_menu_camera
                ),
                BarButton(
                    text = "End shift",
                    onClick = {
                        Log.d("LogButton", "button press")
                    },
                    iconId = android.R.drawable.ic_menu_camera
                )
            )
        )
    }
}