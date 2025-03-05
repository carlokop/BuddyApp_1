package nl.ou.buddyapp.ui.theme

import android.os.Build
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import nl.ou.buddyapp.R
import nl.ou.buddyapp.ui.theme.Primary

// Definieer een aangepast lettertype voor je app
val robotoFamily  = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

//voorlopig is heeft darkmode dezelfde kleuren
private val DarkColorScheme = darkColorScheme(
    primary = Primary,  // Hoofdkleur van je app
    secondary = Secondary,  // Secundaire kleur
    tertiary = Teriary,  // Tertiaire kleur
    background = White,  // Achtergrondkleur
    surface = White,  // Surface-kleur (achtergrond van componenten zoals kaarten)
    onPrimary = White,  // Kleur voor tekst of iconen op primaire achtergrond
    onSecondary = White,  // Kleur voor tekst of iconen op secundaire achtergrond
    onTertiary = White,  // Kleur voor tekst of iconen op tertiaire achtergrond
    onBackground = Black,  // Kleur voor tekst op achtergrond
    onSurface = Dark,  // Kleur voor tekst op surface
    error = Error,  // Kleur voor foutmeldingen
    onError = White,  // Kleur voor tekst op error-achtergrond
    outline = Accent,  // Kleur voor outlines (randkleuren),
    surfaceVariant = Primary,  //kleur lichte variant surface
    onSurfaceVariant = White, //kleur lichte variant surface,
    outlineVariant = SecondaryGradient
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,  // Hoofdkleur van je app
    secondary = Secondary,  // Secundaire kleur
    tertiary = Teriary,  // Tertiaire kleur
    background = White,  // Achtergrondkleur
    surface = White,  // Surface-kleur (achtergrond van componenten zoals kaarten)
    onPrimary = White,  // Kleur voor tekst of iconen op primaire achtergrond
    onSecondary = White,  // Kleur voor tekst of iconen op secundaire achtergrond
    onTertiary = White,  // Kleur voor tekst of iconen op tertiaire achtergrond
    onBackground = Black,  // Kleur voor tekst op achtergrond
    onSurface = Dark,  // Kleur voor tekst op surface
    error = Error,  // Kleur voor foutmeldingen
    onError = White,  // Kleur voor tekst op error-achtergrond
    outline = Accent,  // Kleur voor outlines (randkleuren)
    surfaceVariant = Primary,  //kleur lichte variant surface
    onSurfaceVariant = White, //kleur lichte variant surface
    outlineVariant = SecondaryGradient
)

@Composable
fun BuddyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,              //uitgeschakeld
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
