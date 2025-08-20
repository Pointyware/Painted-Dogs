package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.pointyware.painteddogs.chat.Res
import org.pointyware.painteddogs.chat.outline_person_24
import org.pointyware.painteddogs.core.ui.design.GeometryTokens

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
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium)
    ) {
        Image(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary,
                    MaterialTheme.shapes.medium)
                .size(GeometryTokens.dpMinimumAccessible)
                .padding(GeometryTokens.dpMedium),
            painter = painterResource(Res.drawable.outline_person_24),
            contentDescription = "Profile Image", // TODO: replace with name
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
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
