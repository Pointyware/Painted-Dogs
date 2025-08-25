package org.pointyware.painteddogs.aid

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.viewmodels.OfferScreenViewModel
import selectedColors
import unSelectedColors

@Composable
fun OfferScreen(
    viewModel: OfferScreenViewModel,
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navEvent.collect { destination ->
            navController.navigate(destination)
        }
    }
    Column(
        modifier = Modifier
    ) {
        var scope by remember { mutableStateOf(TemporalScope.Indefinite) }
        TemporalSelector(
            value = scope,
            modifier = Modifier,
            onScopeSelected = { scope = it }
        )

        var title by remember { mutableStateOf(state.title) }
        TextField(
            modifier = Modifier,
            value = title,
            onValueChange = { title = it }
        )

        Button(
            onClick = { viewModel.onSubmit() }
        ) {
            Text(
                text = stringResource(Res.string.label_submit)
            )
        }
    }
}

@Composable
fun stringForScope(value: TemporalScope): String {
    return stringResource(when (value) {
        TemporalScope.Indefinite -> Res.string.label_indefinite
        TemporalScope.Limited -> Res.string.label_limited
        TemporalScope.Schedule -> Res.string.label_scheduled
        TemporalScope.Event -> Res.string.label_event
        TemporalScope.Immediate -> Res.string.label_immediate
    })
}

@Composable
fun TemporalSelector(
    value: TemporalScope,
    onScopeSelected: (TemporalScope)->Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        listOf(
            TemporalScope.Indefinite,
            TemporalScope.Limited,
            TemporalScope.Schedule,
            TemporalScope.Event,
            TemporalScope.Immediate
        ).forEach { scope ->
            Button(
                onClick = { onScopeSelected(scope) },
                colors = if (value == scope) selectedColors() else unSelectedColors(),
            ) {
                Text(
                    text = stringForScope(scope)
                )
            }
        }
    }
}
