package com.websocketapp.data.remote.dto

import com.websocketapp.data.domain.model.Message

data class MessageDto(
    val text: String
) {
    fun toMessage(): Message {
        return Message(
            user = "",
            text = text
        )
    }
}