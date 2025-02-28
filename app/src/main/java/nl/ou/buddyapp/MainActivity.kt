package nl.ou.buddyapp

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.ou.buddyapp.comp.NotificationIcon
import nl.ou.buddyapp.ui.theme.BuddyAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuddyAppTheme {
                Scaffold(
                    topBar = {
                        Header() // Je header-component
                    },
                    bottomBar = {
                        Footer() // Je footer-component
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding), // Zorgt dat de inhoud niet onder de header/footer komt
                        verticalArrangement = Arrangement.Top, // Zorgt dat content bovenaan begint
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Home(
                            name = "Android",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Home(name: String, modifier: Modifier = Modifier) {

    Text(text = "Titel", style = MaterialTheme.typography.titleLarge)

    //categorieen

    //populaire buddies

    //aanbevolen zoekopdrachten

    //jouw buddies

}

@Composable
fun Header() {
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        HeaderTopRow()
        SearchBar(
            placeholder = "Category name",  // Placeholder text
            onQueryChange = { query -> println("Searching for: $query") },  // Callback om zoekterm bij te houden
            modifier = Modifier
        )
    }
}

@Composable
fun HeaderTopRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 50.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Linker icoon
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = Color.White,
            modifier = Modifier
                .size(32.dp)
                .alpha(0.7f)
        )

        // Middelste tekst of content
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Find your buddies",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(start = 10.dp, top = 50.dp, end = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        NotificationIcon(icon = Icons.Default.Notifications, count = 5)
    }
}

@Composable
fun SearchBar(
    placeholder: String = "Search...", // Voeg placeholder toe als parameter
    onQueryChange: (String) -> Unit, // Callback om de query bij te houden
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }  // Opslaan van de zoekterm

    // Haal de breedte van het scherm op
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val maxWidth = (0.7f * screenWidthDp.value).dp

    Row(
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp,
            top = 10.dp,
            bottom = 100.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

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
                onQueryChange(newValue) // Update de zoekterm
            },
            placeholder = {
                Text(
                    text = placeholder, // Voeg de placeholder tekst toe
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        color = Color.Black.copy(alpha = 0.5f) // Maak de placeholder lichtgrijs
                    )
                )
            },
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
                focusedIndicatorColor = Color.Transparent, // Verwijder de rand bij focus
                unfocusedIndicatorColor = Color.Transparent // Verwijder de rand bij geen focus
            ),
            shape = RoundedCornerShape(16.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp) // Pas fontSize aan
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


@Composable
fun Footer() {

}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BuddyAppTheme {
        println("Secondary color: ${MaterialTheme.colorScheme.secondary}")

        Home("Android")
    }
}