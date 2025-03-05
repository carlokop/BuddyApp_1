package nl.ou.buddyapp.comp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun Header() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column {
            HeaderTopRow(modifier = Modifier)
            SearchBar(
                placeholder = stringResource(nl.ou.buddyapp.R.string.category_name),  // Placeholder text
                onQueryChange = { query -> "" },  // Callback om zoekterm bij te houden
                modifier = Modifier
            )
        }

    }
}


