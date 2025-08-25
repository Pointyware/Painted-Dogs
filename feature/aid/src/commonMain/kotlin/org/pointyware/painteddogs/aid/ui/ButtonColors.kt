import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable

@Composable
fun selectedColors(): ButtonColors {
    return ButtonDefaults.filledTonalButtonColors()
}

@Composable
fun unSelectedColors(): ButtonColors {
    return ButtonDefaults.buttonColors()
}
