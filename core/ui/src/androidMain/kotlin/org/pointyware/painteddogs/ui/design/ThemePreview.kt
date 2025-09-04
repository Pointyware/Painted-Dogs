package org.pointyware.painteddogs.core.ui.org.pointyware.painteddogs.ui.design

import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.painteddogs.core.ui.design.PaintedDogsTheme


@Preview
@Composable
fun PaintedDogsThemePreview() {
    PaintedDogsTheme {
        Surface {
            Text("ooh, a button")
            Button(onClick = { println("Click") }) {
                Text("Click me!")
            }
        }
    }
}
