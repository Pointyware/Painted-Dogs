package org.pointyware.painteddogs.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.pointyware.painteddogs.ui.Res
import org.pointyware.painteddogs.ui.acc_toggle_selected
import org.pointyware.painteddogs.ui.acc_toggle_unselected
import org.pointyware.painteddogs.ui.twotone_checked_24
import org.pointyware.painteddogs.ui.twotone_unchecked_24

@Composable
fun LazyItemScope.BooleanSelector(
    value: Boolean,
    onValueChange: (Boolean)->Unit,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    Row(
        modifier = modifier
    ) {
        IconToggleButton(
            modifier = modifier,
            checked = value,
            onCheckedChange = onValueChange,
        ) {
            Image(
                imageVector = if (value) {
                    vectorResource(Res.drawable.twotone_checked_24)
                } else {
                    vectorResource(Res.drawable.twotone_unchecked_24)
                },
                contentDescription = if (value) {
                    stringResource(Res.string.acc_toggle_selected)
                } else {
                    stringResource(Res.string.acc_toggle_unselected)
                }
            )
        }
        label?.let {
            Text(text = label)
        }
    }
}


@Composable
fun LazyItemScope.IntegerQuantitySelector(
    value: Int,
    onValueChange: (String)->Unit,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    TextField(
        value = value.toString(),
        onValueChange = onValueChange,
        modifier = modifier,
        label = label?.let {
            @Composable { Text(text = label) }
        }
    )
}

@Composable
fun LazyItemScope.DecimalQuantitySelector(
    value: Float,
    onValueChange: (String)->Unit,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    TextField(
        value = value.toString(),
        onValueChange = onValueChange,
        modifier = modifier,
        label = label?.let {
            @Composable { Text(text = label) }
        }
    )
}
