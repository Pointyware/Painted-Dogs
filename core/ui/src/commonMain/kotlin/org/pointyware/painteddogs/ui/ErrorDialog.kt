package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.ui.Res
import org.pointyware.painteddogs.ui.acc_unknown_error

@Composable
fun ErrorDialog(
    throwable: Throwable?,
    onDismissRequest: ()->Unit,
    modifier: Modifier = Modifier
) {
    throwable?.let {
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            Column(
                modifier = modifier
            ) {
                Text(
                    text = throwable.message ?: stringResource(Res.string.acc_unknown_error)
                )
            }
        }
    }
}
