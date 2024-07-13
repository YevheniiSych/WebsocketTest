package com.websocketapp.ui

import com.websocketapp.data.domain.model.Message

data class MessageState(
    val messagesList: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
