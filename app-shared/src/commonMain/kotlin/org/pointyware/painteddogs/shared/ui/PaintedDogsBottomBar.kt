package org.pointyware.painteddogs.shared.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.shared.Res
import org.pointyware.painteddogs.shared.label_aid
import org.pointyware.painteddogs.shared.label_chat

/**
 * A [NavigationBar] containing a buttons for the [org.pointyware.painteddogs.chat.navigation.ChatHistoryDestination]
 * and [org.pointyware.painteddogs.aid.navigation.ExchangeBoardDestination].
 */
@Composable
fun PaintedDogsBottomBar(
    onNavigateToChat: ()->Unit,
    onNavigateToAid: ()->Unit,
) {
    NavigationBar {
        val chatString = stringResource(Res.string.label_chat)
        NavigationBarItem(
            selected = false,
            onClick = onNavigateToChat,
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Message,
                    contentDescription = chatString
                )
            },
            label = {
                Text(
                    text = chatString
                )
            },
        )
        val aidString = stringResource(Res.string.label_aid)
        NavigationBarItem(
            selected = false,
            onClick = onNavigateToAid,
            icon = {
                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = aidString
                )
            },
            label = {
                Text(
                    text = aidString
                )
            }
        )
    }
}
