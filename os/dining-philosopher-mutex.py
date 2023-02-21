Code:
import threading
import time
import random
# Number of philosophers
NUM_PHILOSOPHERS
= 5
# Number of iterations
ITERATIONS = 20
# Mutexes for forks
forks = [threading.Lock() for i in range(NUM_PHILOSOPHERS)]
def philosopher(id):
count = 0
while count <
ITERATIONS:#
Thinking
print(f"Philosopher {id} is
thinking")time.sleep(1)
# Pick up left fork
left_fork = id
forks[left_fork].acquire()
print(f"Philosopher {id} picked up left fork
{left_fork}")# Pick up right fork
right_fork = (id + 1) %
NUM_PHILOSOPHERS
forks[right_fork].acquire()
print(f"Philosopher {id} picked up right fork
{right_fork}")# Eating
print(f"Philosopher {id} is
eating")time.sleep(1)
# Release forks
forks[left_fork].release()
print(f"Philosopher {id} released left fork
{left_fork}")forks[right_fork].release()
print(f"Philosopher {id} released right fork {right_fork}")
      count += 1
if name == " main ":
threads = [threading.Thread(target=philosopher, args=(i,)) for i
inrange(NUM_PHILOSOPHERS)]
for t in
threads:
t.start()
for t in
threads:
t.join()
print("All philosophers have finished eating.")
