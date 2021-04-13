import socketio
import random
import time
import threading
import asyncio
from concurrent.futures import ProcessPoolExecutor

# commands to run for depenencies
# pip install python-socketio
# pip install websocket-client
# pip install tornado
# pip install requests --user
# pip install socketio
# npm install socket.io --saves

sio = socketio.Client(engineio_logger=True, logger=True, ssl_verify=False)

@sio.event
def connect():
    print('connection established')

@sio.event
def my_message(data):
    print('Message received with ', data)
    sio.emit('msg to be sent')

@sio.event
def disconnect():
    print('disconnected from server')

def websocketsKitchen():
    hashRandom = random.getrandbits(128)
    client1 = random.randint(0, 10000)
    sio.connect('url to connect to')
    sio.emit('msg to be sent')
    while True:
        sio.emit('msg to be sent regularly')
        time.sleep(1)
    sio.wait()

if __name__ == "__main__":
    number_of_loops = 3 #number of instances
    executor = ProcessPoolExecutor(number_of_loops)
    loop = asyncio.get_event_loop()
    for i in range(number_of_loops):
        asyncio.ensure_future(loop.run_in_executor(executor, websocketsKitchen))
    loop.run_forever()








