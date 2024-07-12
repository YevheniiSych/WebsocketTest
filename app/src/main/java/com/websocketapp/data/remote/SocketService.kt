package com.websocketapp.data.remote

import com.websocketapp.BuildConfig
import com.websocketapp.data.domain.model.Message
import com.websocketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface SocketService {
    suspend fun initSession(userName: String): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        private const val BASE_URL = "wss://free.blr2.piesocket.com"
    }

    sealed class Endpoints(val url: String) {
        object ChatSocket: Endpoints("$BASE_URL/v3/1?api_key=${BuildConfig.PIESOCKET_API_KEY}&notify_self=1")
    }
}