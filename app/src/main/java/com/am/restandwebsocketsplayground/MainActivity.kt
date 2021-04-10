package com.am.restandwebsocketsplayground

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var webSocketsPlayground: WebSocketsPlayground
    private lateinit var btcPriceTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btcPriceTv = findViewById(R.id.btc_price_tv);
        webSocketsPlayground = WebSocketsPlayground(
            WebSocketsConstants.COINRBASE_WEB_SOCKET_URL,
            WebSocketsConstants.COINRBASE_WEB_SOCKET_SUBSCRIBE,
            WebSocketsConstants.COINRBASE_WEB_SOCKET_UNSUBSCRIBE)
    }

    override fun onResume() {
        super.onResume()
        List(20) { // launch a lot of coroutines and list their jobs
            CoroutineScope(Dispatchers.IO).launch {
                var ws = WebSocketsPlayground(
                    WebSocketsConstants.COINRBASE_WEB_SOCKET_URL,
                    WebSocketsConstants.COINRBASE_WEB_SOCKET_SUBSCRIBE,
                    WebSocketsConstants.COINRBASE_WEB_SOCKET_UNSUBSCRIBE)
                ws.openConnection()
                delay(3000)
                ws.sendMessage(ws.subscribeMessage)
                delay(5000)
                ws.sendMessage(ws.unsubscribeMessage)
                //ws.closeConnection()
            }
        }

    }


    companion object {
        const val TAG = "MainActivity"
    }

}