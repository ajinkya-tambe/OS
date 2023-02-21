import java.util.concurrent.Semaphore;

public class DiningTable {
  static final int NUM_PHILOSOPHERS = 5;
  static Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
  static Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];

  public static void main(String[] args) {
    for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
      forks[i] = new Semaphore(1);
    }

    for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
      philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS]);
      philosophers[i].start();
    }
  }
}

class Philosopher extends Thread {
  int id;
  Semaphore leftFork;
  Semaphore rightFork;

  Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
    this.id = id;
    this.leftFork = leftFork;
    this.rightFork = rightFork;
  }

  public void run() {
    int iterationCounter = 0;
    while (iterationCounter < 3) {
      System.out.println("Philosopher " + id + " is thinking");
      try {
        leftFork.acquire();
        rightFork.acquire();
        System.out.println("Philosopher " + id + " is eating");
        iterationCounter++;
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        leftFork.release();
        rightFork.release();
      }
    }
  }
}
