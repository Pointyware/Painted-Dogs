package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.layout.Row
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
    Row(
        modifier = modifier
    ) {
        ContactImage(
            value = value,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = value.username
        )
    }
}
