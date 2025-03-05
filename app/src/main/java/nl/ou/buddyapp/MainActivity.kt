package nl.ou.buddyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.ou.buddyapp.comp.Header
import nl.ou.buddyapp.ui.theme.BuddyAppTheme


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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 500.dp)
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

    //lijst met categorieen

}


@Composable
fun Footer() {
  //ToDo
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BuddyAppTheme {
        println("Secondary color: ${MaterialTheme.colorScheme.secondary}")

        Home("Android")
    }
}