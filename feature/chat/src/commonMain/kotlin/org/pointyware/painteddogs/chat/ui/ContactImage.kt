package org.pointyware.painteddogs.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.pointyware.painteddogs.chat.Res
import org.pointyware.painteddogs.chat.acc_no_profile_picture
import org.pointyware.painteddogs.chat.entities.Contact
import org.pointyware.painteddogs.chat.outline_person_24
import org.pointyware.painteddogs.ui.outline_question_mark_24
import org.pointyware.painteddogs.ui.Res as UiRes

@Composable
fun ContactImage(
    value: Contact?,
    modifier: Modifier = Modifier
) {
    val image = value?.let {
        vectorResource(UiRes.drawable.outline_question_mark_24)
    } ?: vectorResource(Res.drawable.outline_person_24)
    Image(
        modifier = modifier,
        imageVector = image,
        contentDescription = value?.username ?: stringResource(Res.string.acc_no_profile_picture)
    )
}
