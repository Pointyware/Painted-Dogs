package org.pointyware.painteddogs.chat.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.pointyware.painteddogs.chat.entities.ChatRecord

class ChatHistoryViewModel(

): ViewModel() {


    val _chatList = MutableStateFlow(ChatHistoryDummyData.chatHistory)
    val chatList: StateFlow<List<ChatRecord>> get() = _chatList.asStateFlow()

}
