import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphore {
    
    static int bufferSize;
    static int[] buffer;
    static int count = 0;
    
    static Semaphore full = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);
    static Semaphore empty;

    static class Producer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    empty.acquire();
                    mutex.acquire();
                    buffer[count++] = i;
                    System.out.println("Produced: " + i);
                    mutex.release();
                    full.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    full.acquire();
                    mutex.acquire();
                    int item = buffer[--count];
                    System.out.println("Consumed: " + item);
                    mutex.release();
                    empty.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter buffer size: ");
        
        bufferSize = sc.nextInt();
        
        buffer = new int[bufferSize];
        empty = new Semaphore(bufferSize);

        System.out.print("Enter number of producers: ");
        int numProducers = sc.nextInt();
        
        System.out.print("Enter number of consumers: ");
        int numConsumers = sc.nextInt();

        Producer[] producer = new Producer[numProducers];
        Consumer[] consumer = new Consumer[numConsumers];

        for (int i = 0; i < numProducers; i++) {
            producer[i] = new Producer();
            producer[i].start();
        }

        for (int i = 0; i < numConsumers; i++) {
            consumer[i] = new Consumer();
            consumer[i].start();
        }
    }
}
