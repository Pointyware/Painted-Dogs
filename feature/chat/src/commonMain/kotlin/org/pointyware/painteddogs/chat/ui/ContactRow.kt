package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.chat.entities.Contact

/**
 * Displays a contact username
 */
@Composable
fun LazyItemScope.ContactRow(
    value: Contact,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = value.username
    )
}
