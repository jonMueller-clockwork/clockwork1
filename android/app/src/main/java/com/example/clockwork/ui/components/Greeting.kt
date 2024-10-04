package com.example.clockwork.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockwork.ui.theme.ClockworkTheme
import com.example.clockwork.ui.theme.DMSansFontFamily
import com.example.clockwork.ui.theme.SoftGray

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello,",
            modifier = modifier,
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$name!",
                modifier = modifier
                    .weight(1f)
                    .alignBy(FirstBaseline),
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "May 28",
                modifier = Modifier.alignBy(FirstBaseline),
                fontFamily = DMSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = SoftGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClockworkTheme {
        Greeting("Jon Mueller")
    }
}