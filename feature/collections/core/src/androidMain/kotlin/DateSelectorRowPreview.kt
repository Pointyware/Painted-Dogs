import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.Clock
import org.pointyware.painteddogs.core.ui.components.DateSelectorRow
import org.pointyware.painteddogs.core.ui.components.DateSelectorRowState
import org.pointyware.painteddogs.core.ui.PopularSizePreviews

@PopularSizePreviews
@Preview
@Composable
fun DateSelectorRowPreview() {
    DateSelectorRow(
        state = DateSelectorRowState(date = Clock.System.now()),
        onDateChanged = {},
    )
}
