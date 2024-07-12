package com.websocketapp.data.remote

import com.websocketapp.data.domain.model.Message
import com.websocketapp.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.WebSocketSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.isActive

class SocketServiceImpl(
    val client: HttpClient
) : SocketService {

    private var socket: WebSocketSession? = null

    override suspend fun initSession(userName: String): Resource<Unit> {
        return try {
            socket = client.webSocketSession {
                url(SocketService.Endpoints.ChatSocket.url)
            }

            if (socket?.isActive == true) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Couldn't establish connection.")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun sendMessage(message: String) {

    }

    override fun observeMessages(): Flow<Message> {
        TODO("Not yet implemented")
    }

    override suspend fun closeSession() {
        TODO("Not yet implemented")
    }
}


//class ChatSocketServiceImpl(
//    val client: OkHttpClient
//) : ChatSocketService {
//
//    private var socket: WebSocket? = null
//
//    override suspend fun connect(userName: String): Resource<Unit> {
//        val request = Request.Builder()
//            .url(ChatSocketService.Endpoints.ChatSocket.url)
//            .build()
//        return try {
//            suspendCancellableCoroutine { continuation ->
//                socket = client.newWebSocket(
//                    request = request,
//                    listener = object : WebSocketListener() {
//                        override fun onFailure(
//                            webSocket: WebSocket,
//                            t: Throwable,
//                            response: Response?
//                        ) {
//                            super.onFailure(webSocket, t, response)
//                            continuation.resumeWithException(t)
//                        }
//
//
//                        override fun onMessage(webSocket: WebSocket, text: String) {
//                            super.onMessage(webSocket, text)
//                        }
//
//                        override fun onOpen(webSocket: WebSocket, response: Response) {
//                            super.onOpen(webSocket, response)
//                            continuation.resume(Resource.Success(Unit))
//                        }
//                    }
//                )
//            }
//        } catch (e: Exception) {
//            Resource.Error(e.localizedMessage ?: "Unknown error")
//        }
//    }
//
//    override suspend fun sendMessage(message: String) {
//        TODO("Not yet implemented")
//    }
//
//    override fun observeMessages(): Flow<Message> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun closeSession() {
//        TODO("Not yet implemented")
//    }
//}