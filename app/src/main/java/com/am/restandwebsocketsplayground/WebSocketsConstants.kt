package com.am.restandwebsocketsplayground

object WebSocketsConstants {

    const val COINRBASE_WEB_SOCKET_URL = "wss://ws-feed.pro.coinbase.com"
    const val COINRBASE_WEB_SOCKET_SUBSCRIBE = "{\n" +
            "    \"type\": \"subscribe\",\n" +
            "    \"channels\": [{ \"name\": \"ticker\", \"product_ids\": [\"BTC-EUR\"] }]\n" +
            "}"
    const val COINRBASE_WEB_SOCKET_UNSUBSCRIBE = "{\n" +
            "    \"type\": \"unsubscribe\",\n" +
            "    \"channels\": [\"ticker\"]\n" +
            "}"

}
