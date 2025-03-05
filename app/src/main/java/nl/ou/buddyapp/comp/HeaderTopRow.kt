package nl.ou.buddyapp.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Balk met hamburger icoon titel en notificaties voor in de header
 */
@Composable
fun HeaderTopRow(modifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()  //offset aan de bovenkant statusbar
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
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
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 0.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        NotificationIcon(icon = Icons.Default.Notifications, count = 5)
    }
}