package com.example.wallpapertest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.wallpapertest.R


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val salsa = GoogleFont("salsa")
val sansita = GoogleFont("sansita")

val salsaFontFamily = FontFamily(
    Font(googleFont = salsa, fontProvider = provider), Font(resId = R.font.salsa_regular)
)

val sansitaFontFamily = FontFamily(
    Font(googleFont = sansita, fontProvider = provider), Font(resId = R.font.sansita_regular)
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = sansitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = sansitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = sansitaFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)
