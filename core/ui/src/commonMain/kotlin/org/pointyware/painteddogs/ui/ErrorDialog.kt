package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.core.ui.design.paddingMedium
import org.pointyware.painteddogs.ui.Res
import org.pointyware.painteddogs.ui.acc_unknown_error
import org.pointyware.painteddogs.ui.label_submit

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
            Surface(
                modifier  = Modifier.paddingMedium()
            ) {
                Column(
                    modifier = modifier
                ) {
                    Text(
                        text = throwable.message ?: stringResource(Res.string.acc_unknown_error)
                    )

                    Button(
                        onClick = onDismissRequest
                    ) {
                        Text(text = stringResource(Res.string.label_submit))
                    }
                }
            }
        }
    }
}
