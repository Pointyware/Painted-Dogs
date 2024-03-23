package org.pointyware.painteddogs.feature.collections.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.datetime.Instant
import org.pointyware.painteddogs.core.entities.Fund
import org.pointyware.painteddogs.core.entities.Uuid

data class FundSearchViewState(
    val query: String = "",
    val searchResults: List<FundSearchListItemState> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)

/**
 * Displays a modular view with a single search input field, submission button,  and a list of
 * search results.
 */
@Composable
fun FundSearchView(
    state: FundSearchViewState,
    modifier: Modifier = Modifier,
    onSearchQueryChanged: (String) -> Unit,
    onSearchQuerySubmitted: (String) -> Unit,
    onFundSelected: (Uuid) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Row {
            TextField(
                value = state.query,
                onValueChange = onSearchQueryChanged,
                label = { Text("Search") },
            )
            Button(onClick = { onSearchQuerySubmitted(state.query) }) {
                Text("Submit")
            }
        }
        LazyColumn {
            items(state.searchResults) { item ->
                Row(
                    modifier = Modifier.clickable { onFundSelected(item.id) }
                ) {
                    Text(text = item.title)
                    Text(text = item.description)
                }
            }
        }
    }
}

/**
 * Contains basic information about a fund that is displayed in a search result.
 * @see toFundSearchListItemState
 */
data class FundSearchListItemState(
    val id: Uuid,
    val title: String,
    val description: String,
    val startDate: String?,
    val endDate: String?,
)

/**
 * @see toDateString
 */
fun Fund.toFundSearchListItemState(): FundSearchListItemState {
    return FundSearchListItemState(
        id = id,
        title = title,
        description = description,
        startDate = start.toDateString(),
        endDate = end.toDateString(),
    )
}

private const val NULL_DATE_STRING = "-"
/**
 *
 */
fun Instant?.toDateString(): String {
    return when(this) {
        null -> NULL_DATE_STRING
        else -> this.toString()
    }
}

@Composable
fun FundSearchListItem(
    state: FundSearchListItemState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = state.title
        )
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = state.description
        )
    }
}
