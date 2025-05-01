package com.example.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

// Set of Material typography styles to start with
val InterFontFamily = FontFamily(
    Font(R.font.inter_pt_black),
    Font(R.font.inter_pt_bold, FontWeight.Bold),
    Font(R.font.inter_pt_light,FontWeight.Light),
    Font(R.font.inter_pt_thin,FontWeight.Thin),
    Font(R.font.inter_pt_regular,FontWeight.Normal),

    )

val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle( // For buttons
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle( // For subtitles, card titles
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    headlineLarge = TextStyle( // For normal headers
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp
    ),
    displayLarge = TextStyle( // For big headers
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
)