import threading
import time
# Number of readers
readers = 0
# Semaphores for synchronization
read_mutex = threading.Semaphore(1)
write_mutex = threading.Semaphore(1)
db = threading.Semaphore(1)
def reader():
global readers
read_mutex.acquire()
readers += 1
if readers ==1:
    db.acquire()
read_mutex.release()
print("Reader is reading")
time.sleep(1)
read_mutex.acquire()
readers -= 1
if readers ==
    0:
db.release()
read_mutex.release()
def writer():
write_mutex.acquire()
db.acquire()
print("Writer iswriting")
    time.sleep(1)
      db.release()
      write_mutex.release()
      # Example usage
      if name == " main ":
      for i in range(10):
      t1 = threading.Thread(target=reader)
      t2 = threading.Thread(target=writer)
      t1.start()
      t2.start()
      t1.join()
      t2.join()
