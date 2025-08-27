package org.pointyware.painteddogs.aid.ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import org.pointyware.painteddogs.aid.Res
import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.TemporalScope
import org.pointyware.painteddogs.aid.label_event
import org.pointyware.painteddogs.aid.label_food
import org.pointyware.painteddogs.aid.label_funds
import org.pointyware.painteddogs.aid.label_housing
import org.pointyware.painteddogs.aid.label_immediate
import org.pointyware.painteddogs.aid.label_indefinite
import org.pointyware.painteddogs.aid.label_limited
import org.pointyware.painteddogs.aid.label_protection
import org.pointyware.painteddogs.aid.label_scheduled
import org.pointyware.painteddogs.aid.label_skills


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
fun stringForResource(value: Resource): String {
    return stringResource(when (value) {
        Resource.Food -> Res.string.label_food
        Resource.Housing -> Res.string.label_housing
        Resource.Funds -> Res.string.label_funds
        Resource.Skills -> Res.string.label_skills
        Resource.Protection -> Res.string.label_protection
    })
}
