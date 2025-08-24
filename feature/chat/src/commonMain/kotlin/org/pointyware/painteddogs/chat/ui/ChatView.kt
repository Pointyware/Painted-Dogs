package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.chat.Res
import org.pointyware.painteddogs.chat.acc_send
import org.pointyware.painteddogs.chat.interactors.ChatLogItem
import org.pointyware.painteddogs.chat.viewmodels.ChatUiState
import org.pointyware.painteddogs.core.ui.design.GeometryTokens
import org.pointyware.painteddogs.core.ui.design.LocalDateFormat
import org.pointyware.painteddogs.core.ui.design.LocalGeometry
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun ChatView(
    state: ChatUiState,
    error: Throwable?,
    modifier: Modifier = Modifier,
    onSendMessage: (String)->Unit,
    onClearError: ()->Unit,
) {

    if (error != null) {
        Dialog(
            onDismissRequest = onClearError
        ) {
            Text(
                text = error.message ?: error.toString()
            )
        }
    }
    val dateTime = LocalDateFormat.current
    when (state) {
        is ChatUiState.Loading -> {
            Text("Loading")
        }
        is ChatUiState.UnknownChat -> {
            Text("Unknown")
        }
        is ChatUiState.Loaded -> {
            Column(
                modifier = modifier
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    reverseLayout = true,
                    contentPadding = LocalGeometry.current.paddingSmall,
                    verticalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(state.elements) { element ->
                        ChatLogElement(
                            state = when (element) {
                                is ChatLogItem.TimeDivider -> {
                                    ChatLogElementState.TimeDivider(
                                        dateTime.format(element.day)
                                    )
                                }

                                is ChatLogItem.Author -> {
                                    ChatLogElementState.Author(
                                        element.contact.username
                                    )
                                }

                                is ChatLogItem.Message -> {
                                    ChatLogElementState.Message(
                                        element.content,
                                        dateTime.format(element.timeStamp),
                                        element.isSender
                                    )
                                }
                            }
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(GeometryTokens.dpMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(LocalGeometry.current.paddingSmall)
                ) {
                    var message by remember { mutableStateOf("") }
                    TextField(
                        modifier = Modifier
                            .weight(1f),
                        value = message,
                        onValueChange = { message = it }
                    )

                    IconButton(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = MaterialTheme.shapes.small
                            ),
                        onClick = {
                            onSendMessage(message)
                            message = ""
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.Send,
                            contentDescription = stringResource(Res.string.acc_send)
                        )
                    }
                }
            }
        }
    }
}
