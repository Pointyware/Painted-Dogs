package org.pointyware.painteddogs.chat.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.chat.interactors.ChatPreview
import org.pointyware.painteddogs.chat.interactors.GetChatListUseCase

class ChatHistoryViewModel(
    private val getChatListUseCase: GetChatListUseCase
): ViewModel() {


    private val _chatList = MutableStateFlow<List<ChatPreview>>(emptyList())
    val chatList: StateFlow<List<ChatPreview>> get() = _chatList.asStateFlow()
    private val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = _error.asStateFlow()

    fun onInit() {
        viewModelScope.launch {
            getChatListUseCase.invoke()
                .onSuccess { previewList ->
                    _chatList.update {
                        previewList
                    }
                }
                .onFailure {
                    _error.value = it
                }
        }
    }
}
