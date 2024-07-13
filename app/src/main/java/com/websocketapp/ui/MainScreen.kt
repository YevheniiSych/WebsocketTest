package com.websocketapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    Column(
        Modifier
            .then(modifier)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true,
            verticalArrangement = Arrangement.Bottom,
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(state.messagesList) { message ->
                val isOutgoingMessage = message.user == "Outgoing"
                Text(
                    text = "${message.user}:\n   ${message.text}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            if (isOutgoingMessage) PaddingValues(start = 80.dp)
                            else PaddingValues(end = 80.dp)
                        )
                        .background(
                            color = if (isOutgoingMessage) Color.Cyan
                            else Color.Green,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        TextField(
            value = messageText,
            onValueChange = {
                messageText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
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