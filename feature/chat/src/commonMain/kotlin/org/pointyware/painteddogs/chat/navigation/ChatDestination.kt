package org.pointyware.painteddogs.chat.navigation

import kotlinx.serialization.Serializable
import org.pointyware.painteddogs.core.navigation.Destination

@Serializable
sealed interface ChatDestination: Destination

/**
 *
 */
@Serializable
data object ChatRootDestination: ChatDestination
