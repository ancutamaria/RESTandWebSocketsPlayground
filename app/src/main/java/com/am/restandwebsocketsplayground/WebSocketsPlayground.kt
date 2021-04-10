package com.am.restandwebsocketsplayground

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI
import javax.net.ssl.SSLSocketFactory

class WebSocketsPlayground(
    baseUrl: String,
    val subscribeMessage: String,
    val unsubscribeMessage: String
    ) {

    private lateinit var webSocketClient: WebSocketClient
    private val coinBaseUri: URI? = URI(baseUrl)

    fun openConnection() {

        createWebSocketClient(coinBaseUri)

        val socketFactory: SSLSocketFactory = SSLSocketFactory.getDefault() as SSLSocketFactory

        webSocketClient.setSocketFactory(socketFactory)
        webSocketClient.connect()
    }

    fun closeConnection() {
        webSocketClient.close()
    }

    private fun createWebSocketClient(coinbaseUri: URI?) {
        webSocketClient = object : WebSocketClient(coinbaseUri) {

            override fun onOpen(handshakedata: ServerHandshake?) {
                Log.d(MainActivity.TAG, "onOpen")
                sendMessage(subscribeMessage)
            }

            override fun onMessage(message: String?) {
                Log.d(MainActivity.TAG, "onMessage: $message")
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                Log.d(MainActivity.TAG, "onClose")
                //sendMessage(unsubscribeMessage)
            }

            override fun onError(ex: Exception?) {
                Log.e(MainActivity.TAG, "onError: ${ex?.message}")
            }

        }
    }

    fun sendMessage(message: String) {
        webSocketClient.send( message)
    }

}