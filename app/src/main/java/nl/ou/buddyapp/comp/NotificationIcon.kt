package nl.ou.buddyapp.comp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Creates notification icon with optional count
 * @param icon: IconVestor the icon
 * @param iconDescription: icon description for screenreaders
 * @param count: Interger >= 0 with number notifications. When > 0 count is shown
 * @param modifier
 */
@Composable
fun NotificationIcon(
    icon: ImageVector,
    iconDescription: String = "Notification",
    count: Int = 0,
    modifier: Modifier = Modifier
) {
    BadgedBox(
        badge = {
            if (count > 0) {
                Badge(
                    modifier = Modifier.offset(x = (-5).dp),
                    containerColor = Color.White,
                    contentColor = MaterialTheme.colorScheme.outlineVariant
                ) {
                    Text("$count")
                }
            }
        }
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        // Animate the alpha value when clicked
        val alpha = animateFloatAsState(
            targetValue = if (interactionSource.collectIsPressedAsState().value) 0.7f else 1f,
            animationSpec = tween(durationMillis = 150) // Pas de duur van de animatie aan
        )

        Box(
            modifier = Modifier
                .size(40.dp)  // Cirkelgrootte
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = alpha.value), shape = CircleShape)
                .padding(6.dp)
                .clickable(
                    indication = null, // Je kunt de ripple hier uitschakelen
                    interactionSource = interactionSource
                ) {
                    // Voeg hier de actie toe die je wilt uitvoeren wanneer er op de knop wordt geklikt
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
                tint = MaterialTheme.colorScheme.onSecondary,  // Kleur voor het icoon (gebruik de 'onSecondary' kleur voor contrast)
                modifier = Modifier.size(24.dp)  // Icoon grootte
            )
        }
    }
}

