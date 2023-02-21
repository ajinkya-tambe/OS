import threading
import time
import random
from threading import Semaphore
# Buffer size
BUFFER_SIZE = 10
# Buffer
buffer = [0] * BUFFER_SIZE
# Semaphores for synchronization
empty =
Semaphore(BUFFER_SIZE) full =
Semaphore(0)
mutex = Semaphore(1)
# Number of iterations
ITERATIONS = 20
def producer():
count = 0
while count < ITERATIONS:
item = random.randint(1, 100)
empty.acquire()
mutex.acquire()
buffer.append(item)
print(f"Producer produced {item}")
mutex.release()
full.release()
time.sleep(1)
count += 1
def consumer():
count = 0
while count < ITERATIONS:
full.acquire()
 mutex.acquire()
item = buffer.pop()
print(f"Consumer consumed
{item}")mutex.release()
empty.release()
time.sleep(1)
count += 1
# Example usage
if name == " main ":
t1 = threading.Thread(target=producer)
t2 = threading.Thread(target=consumer)
t1.start()
t2.start()
t1.join()
t2.join()
