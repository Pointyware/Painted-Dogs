package org.pointyware.painteddogs.aid

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
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
import org.pointyware.painteddogs.aid.ui.TemporalSelector
import org.pointyware.painteddogs.aid.viewmodels.OfferScreenViewModel

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
