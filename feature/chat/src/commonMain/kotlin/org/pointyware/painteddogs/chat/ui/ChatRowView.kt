package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.pointyware.painteddogs.chat.Res
import org.pointyware.painteddogs.chat.outline_person_24

/**
 *
 */
data class ChatRowViewState(
    val participants: List<ParticipantViewState>, // TODO: replace with immutable variants
    val excerpt: String
)

/**
 * A view that displays basic information about a previous chat.
 */
@Composable
fun ChatRowView(
    state: ChatRowViewState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(Res.drawable.outline_person_24),
            contentDescription = "Profile Image" // TODO: replace with name
        )
        Column {
            val chatLabel = remember(state.participants) {
                state.participants.joinToString { it.label }
            }
            Text(
                text = chatLabel
            )
            Text(
                text = state.excerpt
            )
        }
    }
}
