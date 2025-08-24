package org.pointyware.painteddogs.chat.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.chat.data.ChatMessage
import org.pointyware.painteddogs.chat.interactors.LoadMessagesUseCase

sealed interface ChatUiState {
    data class Loaded(
        val chatId: String,
        val messages: List<ChatMessage>
    ): ChatUiState

    data object Loading: ChatUiState
    data object UnknownChat: ChatUiState
}

class ChatViewModel(
    private val loadMessagesUseCase: LoadMessagesUseCase
): ViewModel() {

    private val _messages = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val messages: StateFlow<ChatUiState> get() = _messages.asStateFlow()
    private val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = _error.asStateFlow()

    fun loadMessages(id: String) {
        viewModelScope.launch {
            delay(500)
            loadMessagesUseCase.invoke(id)
                .onSuccess { chatLog ->
                    _messages.value = ChatUiState.Loaded(
                        chatId = chatLog.id,
                        messages = chatLog.messages,
                    )
                }
                .onFailure { throwable ->
                    if (throwable is IllegalArgumentException) {
                        _messages.value = ChatUiState.UnknownChat
                    } else {
                        _error.value = throwable
                    }
                }
        }
    }
}
