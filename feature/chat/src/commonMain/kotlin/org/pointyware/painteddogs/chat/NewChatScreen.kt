package org.pointyware.painteddogs.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.pointyware.painteddogs.chat.viewmodels.NewChatViewModel

@Composable
fun NewChatScreen(
    viewModel: NewChatViewModel,
    navController: NavController
) {
    val state by viewModel.editorState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.navEvent.collect { destination ->
            navController.navigate(destination)
        }
    }
    Box {
        Column(
            modifier = Modifier
        ) {
            var title by remember { mutableStateOf(state.title) }
            TextField(
                value = title,
                onValueChange = { title = it }
            )
            Text(
                text = "Participants"
            )
            LazyColumn {
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

        FloatingActionButton(
            onClick = { TODO("Open menu to select from contact") }
        ) {
            Text(
                text = "Add Contact"
            )
        }
    }
}
