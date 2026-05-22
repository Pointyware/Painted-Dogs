package org.pointyware.painteddogs.aid.entities

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.painteddogs.aid.ui.stringForExchange
import org.pointyware.painteddogs.aid.ui.vectorForExchange


@Composable
fun RequestImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        imageVector = vectorForExchange(isRequest = true),
        contentDescription = stringForExchange(isRequest = true)
    )
}

@Composable
fun OfferImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        imageVector = vectorForExchange(isRequest = false),
        contentDescription = stringForExchange(isRequest = false)
    )
}
