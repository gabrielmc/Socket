
public class Consumer extends Thread {

    Programa programa;

    public Consumer(Programa outro) {
        this.programa = outro;
    }

    public void run() {
        try {
            while (true) {
                while (this.programa.itemCount == 0){
                    sleep(100);
                }
        int item = (Integer) this.programa.buffer.get(0);
        this.programa.buffer.remove(0);
        this.programa.itemCount--;

        System.out.println("consumer: consuming item "+ item);
        for (int i =0 ; i<10000 ; i++);
        }

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
