package org.pointyware.painteddogs.chat.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pointyware.painteddogs.chat.entities.Contact
import org.pointyware.painteddogs.chat.interactors.AddParticipantUseCase
import org.pointyware.painteddogs.chat.interactors.CreateChatUseCase
import org.pointyware.painteddogs.chat.interactors.LoadContactListUseCase
import org.pointyware.painteddogs.chat.navigation.ChatDestination
import org.pointyware.painteddogs.core.navigation.Destination

/**
 *
 */
class NewChatViewModel(
    private val createChatUseCase: CreateChatUseCase,
    private val addParticipantUseCase: AddParticipantUseCase,
    private val loadContactListUseCase: LoadContactListUseCase
): ViewModel() {

    private val _contactState = MutableStateFlow<ContactsUiState>(ContactsUiState.Closed)
    val contactState: StateFlow<ContactsUiState> = _contactState.asStateFlow()
    private val _navEvent = Channel<Destination>()
    val navEvent: Flow<Destination> = _navEvent.consumeAsFlow()
    private val _editorState = MutableStateFlow(ChatCreatorUiState("", emptyList()))
    val editorState: StateFlow<ChatCreatorUiState> = _editorState.asStateFlow()
    private val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = _error.asStateFlow()

    /**
     *
     */
    fun onSetTitle(title: String) {
        _editorState.update {
            it.copy(title = title)
        }
    }

    /**
     *
     */
    fun onAddParticipant(participant: Contact) {
        val state = editorState.value
        viewModelScope.launch {
            addParticipantUseCase.invoke(participant.id, state.participants)
                .onSuccess { newList ->
                    _editorState.update { it.copy(participants = newList) }
                }
                .onFailure {
                    _error.value = it
                }
        }
        _editorState.update {
            it.copy(participants = it.participants + participant)
        }
        _contactState.value = ContactsUiState.Closed
    }

    /**
     *
     */
    fun onCreateChat() {
        val state = editorState.value
        viewModelScope.launch {
            createChatUseCase.invoke(state.title, state.participants)
                .onSuccess { chatId ->
                    _navEvent.send(ChatDestination.Session(chatId))
                }
                .onFailure {
                    _error.value = it
                }
        }
    }

    fun onOpenContacts() {
        _contactState.value = ContactsUiState.Loading
        viewModelScope.launch {
            loadContactListUseCase()
                .onSuccess { contactList ->
                    _contactState.update {
                        ContactsUiState.Loaded(contactList)
                    }
                }
                .onFailure {
                    _error.value = it
                    _contactState.update {
                        ContactsUiState.Closed
                    }
                }
        }
    }

    fun onCancelContact() {
        _contactState.value = ContactsUiState.Closed
    }

    /**
     * Called when the user has acknowledged the error and attempts to dismiss it.
     */
    fun onClearError() {
        _error.value = null
    }
}

data class ChatCreatorUiState(
    val title: String,
    val participants: List<Contact>
)

sealed class ContactsUiState(
    val isOpen: Boolean = false
) {
    data object Closed: ContactsUiState()
    data object Loading: ContactsUiState(isOpen = true)
    data class Loaded(
        val contacts: List<Contact>
    ): ContactsUiState(isOpen = true)
}
