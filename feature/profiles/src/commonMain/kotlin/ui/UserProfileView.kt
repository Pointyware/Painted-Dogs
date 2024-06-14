package org.pointyware.painteddogs.feature.profiles.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class UserProfileViewState(
    val userId: String,
    val username: String,
    val email: String,
    val bio: String,
    val profileImageUrl: String,
)

/**
 *
 */
@Composable
fun UserProfileView(
    state: UserProfileViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
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
    }
}
