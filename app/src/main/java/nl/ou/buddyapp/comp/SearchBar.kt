package nl.ou.buddyapp.comp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * Maakt een rij met een zoekveld en een icoon met filters
 * @param placeholder String met placeholder
 * @return onQueryChange het onChange callback event die luistert naar veranderingen in het zoekveld
 */
@Composable
fun SearchBar(
    placeholder: String = "Search...", // Voeg placeholder toe als parameter
    onQueryChange: (String) -> Unit, // Callback om de query bij te houden
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }  // Opslaan van de zoekterm en wordt bewaard bij rebuild

    // Haal de breedte van het scherm op
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val maxWidth = (0.7f * screenWidthDp.value).dp

    Row(
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp,
            top = 10.dp,
            bottom = 20.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        /**
         * Dit zijn wat variablen die nodig zijn voor het click effect als je op de knop klikt
         */
        val interactionSource = remember { MutableInteractionSource() }

        // Animate the alpha value when clicked
        val alpha = animateFloatAsState(
            targetValue = if (interactionSource.collectIsPressedAsState().value) 0.7f else 1f,
            animationSpec = tween(durationMillis = 150) // Pas de duur van de animatie aan
        )
        TextField(
            value = query,
            onValueChange = { newValue ->
                query = newValue
                onQueryChange(newValue) // Update de zoekterm en hoist deze naar bovenliggende component
            },
            label = { Text(text = placeholder) },
            modifier = Modifier
                .weight(1f)
                .widthIn(max = maxWidth)
                .background(
                    color = MaterialTheme.colorScheme.surface, // Background color
                    shape = RoundedCornerShape(16.dp) // Shape of the TextField
                ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = Color.Transparent, // Verwijder de rand bij focus
                unfocusedIndicatorColor = Color.Transparent, // Verwijder de rand bij geen focus
                focusedLabelColor = MaterialTheme.colorScheme.onSurface, // Kleur van het label wanneer het focus heeft
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface // Kleur van het label wanneer het geen focus heeft
            ),
            shape = RoundedCornerShape(16.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp), // Pas fontSize aan,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send          //ga naar next
            )
        )
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = alpha.value),
                    shape = RoundedCornerShape(5.dp),
                )
                .padding(5.dp)
                .align(Alignment.CenterVertically)
                .clickable(
                    indication = null, // Je kunt de ripple hier uitschakelen
                    interactionSource = interactionSource
                ) {
                    // Voeg hier de actie toe die je wilt uitvoeren wanneer er op de knop wordt geklikt
                },
        ) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(35.dp)
            )
        }
    }

}