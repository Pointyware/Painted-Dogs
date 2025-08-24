package org.pointyware.painteddogs.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.chat.ui.ContactRow
import org.pointyware.painteddogs.chat.viewmodels.ContactsUiState
import org.pointyware.painteddogs.chat.viewmodels.NewChatViewModel
import org.pointyware.painteddogs.core.ui.design.LocalGeometry

@Composable
fun NewChatScreen(
    viewModel: NewChatViewModel,
    navController: NavController
) {
    val state by viewModel.editorState.collectAsState()
    val geometry = LocalGeometry.current

    LaunchedEffect(viewModel) {
        viewModel.navEvent.collect { destination ->
            navController.navigate(destination)
        }
    }
    Box {
        Column(
            modifier = Modifier
                .padding(geometry.paddingSmall),
            verticalArrangement = Arrangement.spacedBy(geometry.marginMedium)
        ) {
            var title by remember { mutableStateOf(state.title) }
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(Res.string.label_chat_title)) }
            )
            HorizontalDivider()
            Text(
                text = "Participants"
            )
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(state.participants) {
                    Text(
                        text = it.username
                    )
                }
            }

            Button(
                onClick = viewModel::onCreateChat
            ) {
                Text(
                    text = "Create Chat"
                )
            }
        }

        val contactState by viewModel.contactState.collectAsState()
        when (val capture = contactState) {
            ContactsUiState.Closed -> {}
            ContactsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.1f))
                )
            }
            is ContactsUiState.Loaded -> {
                Dialog(
                    onDismissRequest = { viewModel.onCancelContact() }
                ) {
                    LazyColumn {
                        items(capture.contacts) {
                            ContactRow(value = it)
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(geometry.paddingSmall)
                .safeContentPadding(),
            onClick = { viewModel.onOpenContacts() }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(Res.string.acc_add_contact)
            )
        }
    }
}
