@file:OptIn(ExperimentalUuidApi::class)

package org.pointyware.painteddogs.feature.profiles.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class UserProfileViewState(
    val userId: Uuid,
    val username: String,
    val email: String? = null,
    val bio: String,
    val profileImageUrl: String? = null,
)

/**
 *
 */
@Composable
fun UserProfileView(
    state: UserProfileViewState,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {},
    onEditProfile: () -> Unit = {},
    onViewCollections: (userId: Uuid) -> Unit = {},
    onViewContributions: (userId:Uuid) -> Unit = {},
) {
    Column(
        modifier = modifier.verticalScroll(state = rememberScrollState()),
    ) {
        Text(
            text = "User Profile View",
            style = MaterialTheme.typography.displaySmall,
        )
        Text(
            text = "Username: ${state.username}",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "Email: ${state.email}",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "Bio: ${state.bio}",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "Profile image URL: ${state.profileImageUrl}",
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onLogout) {
            Text("Logout")
        }
        Button(onClick = onEditProfile) {
            Text("Edit Profile")
        }
        Button(onClick = { onViewCollections(state.userId) }) {
            Text("View Collections")
        }
        Button(onClick = { onViewContributions(state.userId) }) {
            Text("View Contributions")
        }
    }
}
