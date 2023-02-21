import java.util.concurrent.Semaphore;

class Buffer {
    private int[] buffer;
    private int capacity;
    private int size;
    private int in;
    private int out;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;

    Buffer(int capacity) {
        this.capacity = capacity;
        buffer = new int[capacity];
        size = 0;
        in = 0;
        out = 0;
        mutex = new Semaphore(1);
        empty = new Semaphore(capacity);
        full = new Semaphore(0);
    }

    void put(int item) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        buffer[in] = item;
        in = (in + 1) % capacity;
        size++;
        mutex.release();
        full.release();
    }

    int get() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        int item = buffer[out];
        out = (out + 1) % capacity;
        size--;
        mutex.release();
        empty.release();
        return item;
    }
    
    boolean isEmpty()
    {
        if(size == 0)
            return true;
        else
            return false;
    }
    
    boolean isFull()
    {
        if(size == capacity)
            return true;
        else    
            return false;
    }
}

class Producer implements Runnable {
    private Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            try {
                if(buffer.isFull())
                {
                    System.out.println("Buffer is full!");
				System.exit(1);
                    //break;
                }
                buffer.put(i);
                System.out.println("Producer produced item " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        
        for (int i = 0; i < 15; i++) {
            try {
                if(buffer.isEmpty())
                {
                    System.out.println("Buffer is empty!");
				System.exit(1);
                    //break;
				
                }
                int item = buffer.get();
                System.out.println("Consumer consumed item " + item);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        try 
        {
            new Thread(producer).start();
            Thread.sleep(50);
            new Thread(consumer).start();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
