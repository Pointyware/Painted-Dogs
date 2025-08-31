package org.pointyware.painteddogs.aid.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.vectorResource
import org.pointyware.painteddogs.aid.entities.Resource

@Composable
fun ResourceImage(
    resource: Resource,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        imageVector = vectorResource(drawableForResource(resource)),
        contentDescription = stringForResource(resource)
    )
}
