package com.websocketapp.ui

import com.websocketapp.data.domain.model.Message

data class MessageState(
    val message: Message = Message(""),
    val isLoading: Boolean = false
)
