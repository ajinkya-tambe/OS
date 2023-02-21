import java.util.concurrent.*;

class ReaderWriter {
    private int readCount = 0;
    private Semaphore readLock = new Semaphore(1);
    private Semaphore writeLock = new Semaphore(1);
    private Semaphore mutex = new Semaphore(1);

    public void startRead() throws InterruptedException {
        readLock.acquire();
        mutex.acquire();
        readCount++;
        if (readCount == 1) {
            writeLock.acquire();
        }
        mutex.release();
        readLock.release();
    }

    public void endRead() throws InterruptedException {
        mutex.acquire();
        readCount--;
        if (readCount == 0) {
            writeLock.release();
        }
        mutex.release();
    }

    public void startWrite() throws InterruptedException {
        writeLock.acquire();
    }

    public void endWrite() throws InterruptedException {
        writeLock.release();
    }
}

class Reader implements Runnable {
    private ReaderWriter readerWriter;

    public Reader(ReaderWriter readerWriter) {
        this.readerWriter = readerWriter;
    }

    public void run() {
        try {
            readerWriter.startRead();
            System.out.println("Reader is reading");
            Thread.sleep(1000);
            readerWriter.endRead();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Writer implements Runnable {
    private ReaderWriter readerWriter;

    public Writer(ReaderWriter readerWriter) {
        this.readerWriter = readerWriter;
    }

    public void run() {
        try {
            readerWriter.startWrite();
            System.out.println("Writer is writing");
            Thread.sleep(1000);
            readerWriter.endWrite();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ReaderWriterProblem {
    public static void main(String[] args) {
        ReaderWriter readerWriter = new ReaderWriter();
        Reader reader1 = new Reader(readerWriter);
        Reader reader2 = new Reader(readerWriter);
        Writer writer1 = new Writer(readerWriter);
        Writer writer2 = new Writer(readerWriter);

        new Thread(reader1).start();
	   new Thread(writer1).start();
        new Thread(reader2).start();
        
        new Thread(writer2).start();
    }
}
