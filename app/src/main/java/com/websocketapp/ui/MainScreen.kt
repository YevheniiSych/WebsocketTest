package com.websocketapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    state: MessageState,
    onSendMessage: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {

    var messageText by rememberSaveable {
        mutableStateOf("")
    }

    Box(
        Modifier
            .then(modifier)
            .fillMaxSize()
    ) {
//        Laz

        TextField(
            value = messageText,
            onValueChange = {
                messageText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .align(Alignment.BottomCenter),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Send button",
                    modifier = modifier.clickable {
                        onSendMessage(messageText)
                        messageText = ""
                    }
                )
            }
        )
    }
}