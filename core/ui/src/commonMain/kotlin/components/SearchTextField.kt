package org.pointyware.painteddogs.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays a text field with search icon and description.
 */
@Composable
fun SearchTextField(
    value: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text("Search") },
        trailingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
    )
}
