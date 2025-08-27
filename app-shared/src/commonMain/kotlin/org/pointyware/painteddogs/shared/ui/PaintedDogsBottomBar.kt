package org.pointyware.painteddogs.shared.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.navigation.AidDestination
import org.pointyware.painteddogs.chat.navigation.ChatDestination
import org.pointyware.painteddogs.shared.Res
import org.pointyware.painteddogs.shared.label_aid
import org.pointyware.painteddogs.shared.label_chat

@Composable
fun PaintedDogsBottomBar(
    navController: NavController
) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(ChatDestination.History)
            },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Message,
                    contentDescription = stringResource(Res.string.label_chat)
                )
            },
            label = {
                Text(
                    text = stringResource(Res.string.label_chat)
                )
            },
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(AidDestination.Board)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = stringResource(Res.string.label_aid)
                )
            },
            label = {
                Text(
                    text = stringResource(Res.string.label_aid)
                )
            }
        )
    }
}
