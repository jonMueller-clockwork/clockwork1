package com.example.clockwork.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.clockwork.R

val SoftBlack = Color(0x04, 0x04, 0x15)
val SoftGray = Color(0x04, 0x04, 0x15, 0x7F)

enum class BarButtonType(val buttonColors: ButtonColors) {
    Primary(PrimaryButtonColors),
    Secondary(SecondaryButtonColors)
}

val PrimaryButtonColors = ButtonColors(
    containerColor = SoftBlack,
    contentColor = Color.White,
    disabledContentColor = Color(0xFF757575),
    disabledContainerColor = Color(0xFFE0E0E0)
)

val SecondaryButtonColors = ButtonColors(
    containerColor = Color.White,
    contentColor = SoftBlack,
    disabledContentColor = Color(0xFF757575),
    disabledContainerColor = Color(0xFFE0E0E0)
)

val Blue = Color(0x33, 0x95, 0xE1)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0x04, 0x04, 0x15),
    secondary = Color(0x33, 0x95, 0xE1),
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val clockworkTypography = Typography(
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gilroy_bold)),
        fontSize = 24.sp,
        color = SoftBlack
    )
)

@Composable
fun ClockworkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = clockworkTypography,
        content = content
    )
}