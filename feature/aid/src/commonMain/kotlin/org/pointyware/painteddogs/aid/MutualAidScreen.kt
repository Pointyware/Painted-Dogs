package org.pointyware.painteddogs.aid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
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
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.viewmodels.MutualAidViewModel
import selectedColors
import unSelectedColors

@Composable
fun MutualAidScreen(
    viewModel: MutualAidViewModel,
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navEvent.collect { destination ->
            navController.navigate(destination)
        }
    }
    Box(

    ) {
        LazyColumn {
            items(state.posts) {

            }
        }
        var resource by remember { mutableStateOf(Resource.Food) }
        Row {
            ResourceSelector(
                value = resource,
                onResourceSelected = { resource = it },
                modifier = Modifier
            )
        }
    }
}

@Composable
fun stringForResource(value: Resource): String {
    return stringResource(when (value) {
        Resource.Food -> Res.string.label_indefinite
        Resource.Housing -> Res.string.label_limited
        Resource.Funds -> Res.string.label_event
        Resource.Skills -> Res.string.label_immediate
        Resource.Protection -> Res.string.label_scheduled
    })
}

@Composable
fun ResourceSelector(
    value: Resource,
    onResourceSelected: (Resource) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        listOf(
            Resource.Food,
            Resource.Housing,
            Resource.Funds,
            Resource.Skills,
            Resource.Protection
        ).forEach { resource ->
            Button(
                onClick = { onResourceSelected(resource) },
                colors = if (value == resource) selectedColors() else unSelectedColors(),
            ) {
                Text(
                    text = stringForResource(resource)
                )
            }
        }
    }
}
