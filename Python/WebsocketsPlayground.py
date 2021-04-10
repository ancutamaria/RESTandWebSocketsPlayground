import websocket
import threading
import time
from websocket import create_connection
import asyncio
from concurrent.futures import ProcessPoolExecutor


def on_message(ws, message):
    print(message)


def on_error(ws, error):
    print(error)


def on_close(ws):
    print("### closed ###")


def on_open(ws):
    def run(*args):
        for i in range(3):
            time.sleep(1)
            data = ws.recv()
            print(data)
        time.sleep(1)
        ws.close()
        print("thread terminating...")

    thread.start_new_thread(run, ())


def websocketsKitchenFirst():
    ws = create_connection("ws://echo.websocket.org/")
    print("Sending 'Hello, World'...")
    ws.send("Hello, World")
    print("Sent")
    print("Receiving...")
    result = ws.recv()
    print("Received '%s'" % result)
    ws.close()


def websocketsKitchen():
    websocket.enableTrace(True)
    ws = websocket.WebSocketApp("wss://stream.binance.com:9443/ws/bnbbtc@ticker",
                                on_message=on_message,
                                on_error=on_error,
                                on_close=on_close)
    ws.on_open = on_open

    ws.run_forever()


if __name__ == "__main__":
    number_of_loops = 60
    executor = ProcessPoolExecutor(number_of_loops)
    loop = asyncio.get_event_loop()
    for i in range(number_of_loops):
        asyncio.ensure_future(loop.run_in_executor(executor, websocketsKitchen))
    loop.run_forever()