import threading
import time
import random
# Buffer size
BUFFER_SIZE = 10
# Buffer
buffer = [0] * BUFFER_SIZE
# Mutex for synchronization
mutex = threading.Lock()
# Number of iterations
ITERATIONS = 7
# Counter for how many items the consumer has consumed
consumed = 0
def producer():
 count = 0
 while count < ITERATIONS:
 item = random.randint(1, 100)
 mutex.acquire()
 if 0 in buffer:
 index = buffer.index(0)
 buffer[index] = item
 print(f"Producer produced {item} at index {index}")
 mutex.release()
 time.sleep(1)
 count += 1
def consumer():
 global consumed
 count = 0
 while count < ITERATIONS:
 mutex.acquire()
 if 0 not in buffer:
 index = buffer.index(item)
 item = buffer[index]
 buffer[index] = 0
 print(f"Consumer consumed {item} from index {index}")
 consumed += 1
 mutex.release()
 time.sleep(1)
 count += 1
# Example usage
if __name__== "__main__":
 t1 = threading.Thread(target=producer)
 t2 = threading.Thread(target=consumer)
 t1.start()
 t2.start()
 t1.join()
 t2.join()
 print(f"Consumer consumed a total of {consumed} items")
