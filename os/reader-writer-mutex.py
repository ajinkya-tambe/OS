import threading
import time
# Number of readers
readers = 0
# Mutex for synchronization
read_mutex = threading.Lock()
write_mutex =
threading.Lock()db =
threading.Lock()
def reader(id):
global readers
read_mutex.acquire()
readers += 1
if readers ==
1:
db.acquire()
read_mutex.release()
print(f"Reader {id} is
reading")time.sleep(1)
read_mutex.acquire()
readers -= 1
if readers ==
0:
db.release()
read_mutex.release()
def writer(id):
write_mutex.acquire()
db.acquire()
print(f"Writer {id} is
writing")time.sleep(1)
db.release()
write_mutex.release()
# Example usage
if name == " main ":
for i in range(10):
t1 = threading.Thread(target=reader, args=(i,))
t2 = threading.Thread(target=writer, args=(i,))
t1.start()
t2.start()
t1.join()
t2.join()
