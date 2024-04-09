package org.pointyware.painteddogs.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class ProfileScreenState(
    val userId: String,
    val username: String,
)

/**
 * Displays a user's profile information.
 */
@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    onEditProfile: () -> Unit,
    onViewCollections: () -> Unit,
    onViewContributions: () -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Text("User ID: ${state.userId}")
        Text("Username: ${state.username}")

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onLogout) {
            Text("Logout")
        }
        Button(onClick = onEditProfile) {
            Text("Edit Profile")
        }
        Button(onClick = onViewCollections) {
            Text("View Collections")
        }
        Button(onClick = onViewContributions) {
            Text("View Contributions")
        }
    }
}
