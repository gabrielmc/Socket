import java.util.ArrayList;

public class Programa {

    int itemCount;
    int compartilhada;
    ArrayList buffer;

    Programa () {
        this.itemCount = 0;
        this.buffer = new ArrayList();
    }

    public static void main(String[] args) {
        Programa programa = new Programa();
        programa.run();
    }

    public void run() {
        Consumer consumer = new Consumer(this);
        consumer.start();
        Producer producer = new Producer(this);
        producer.start();

        Consumer consumer1 = new Consumer(this);
        consumer1.start();
        Producer producer1 = new Producer(this);
        producer1.start();
    }
}
