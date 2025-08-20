package org.pointyware.painteddogs.chat.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.chat.entities.Message

sealed interface ChatUiState {
    data class Loaded(
        val chatId: String,
        val messages: List<Message>
    ): ChatUiState

    data object Loading: ChatUiState
    data object UnknownChat: ChatUiState
}

class ChatViewModel(

): ViewModel() {

    private val _messages = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val messages: StateFlow<ChatUiState> get() = _messages.asStateFlow()

    fun loadMessages(id: String) {
        viewModelScope.launch {
            delay(500)
            val messages = ChatMessagesDummyData.chatMessageMap[id]
            _messages.value =
                if (messages == null) {
                    ChatUiState.UnknownChat
                } else {
                    ChatUiState.Loaded(
                        chatId = id,
                        messages = messages
                    )
                }
        }
    }
}
