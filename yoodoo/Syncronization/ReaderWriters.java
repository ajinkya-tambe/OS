import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class ReaderWriters {
    
    static int readCount = 0;
    
    static Semaphore mutex = new Semaphore(1);
    static Semaphore write = new Semaphore(1);

    static class Reader extends Thread {
        @Override
        public void run() {
            try {
                mutex.acquire();
                readCount++;
                if (readCount == 1) {
                    write.acquire();
                }
                mutex.release();
                System.out.println("Reader is reading...");
                Thread.sleep((int) (Math.random() * 1000));
                mutex.acquire();
                readCount--;
                if (readCount == 0) {
                    write.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Writer extends Thread {
        @Override
        public void run() {
            try {
                write.acquire();
                System.out.println("Writer is writing...");
                Thread.sleep((int) (Math.random() * 1000));
                write.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of readers: ");
        int numReaders = sc.nextInt();
        System.out.print("Enter number of writers: ");
        int numWriters = sc.nextInt();

        Reader[] reader = new Reader[numReaders];
        Writer[] writer = new Writer[numWriters];

        for (int i = 0; i < numReaders; i++) {
            reader[i] = new Reader();
            reader[i].start();
        }

        for (int i = 0; i < numWriters; i++) {
            writer[i] = new Writer();
            writer[i].start();
        }
    }
}
