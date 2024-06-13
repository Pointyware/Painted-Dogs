package org.pointyware.painteddogs.feature.funds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class SearchCollectionsScreenState(
    val query: String = "",
)

/**
 * TODO: move to :feature:collections; reuse SearchCollectionView from core/ui/views/SearchView.kt
 */
@Composable
fun SearchCollectionsScreen(
    state: SearchCollectionsScreenState,
    modifier: Modifier = Modifier,
) {
    /*
    TODO: Search Collections Screen
      1. Text/Button => Search [x]
      2. Tap => Collection Details View (Donor view) [x]
      3. Button => Clear Search Terms [x]
     */
    Column(
        modifier = modifier
    ) {

    }
}
