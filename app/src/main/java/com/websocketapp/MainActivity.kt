package com.websocketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.websocketapp.ui.MainScreen
import com.websocketapp.ui.WebsocketViewModel
import com.websocketapp.ui.theme.WebSocketAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebSocketAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = hiltViewModel<WebsocketViewModel>()
                    MainScreen(
                        state = viewModel.state.value,
                        onSendMessage = viewModel::sendMessage,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}