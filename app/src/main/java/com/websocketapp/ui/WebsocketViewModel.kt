package com.websocketapp.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.websocketapp.data.remote.WebSocketService
import com.websocketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebsocketViewModel @Inject constructor(
    private val webSocketService: WebSocketService
) : ViewModel() {

    private val _state = mutableStateOf(MessageState())
    val state: State<MessageState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            val result = webSocketService.initSession("")
            when (result) {
                is Resource.Error -> _toastEvent.emit(result.message ?: "Unknown error")
                is Resource.Success -> {
                    webSocketService.observeMessages()
                        .onEach { message ->
                            _state.value = state.value.copy(
                                message = message
                            )
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            if (text.isNotBlank()) {
                webSocketService.sendMessage(text)
            }
        }
    }

    private fun disconnect() {
        viewModelScope.launch {
            webSocketService.closeSession()
        }
    }
}