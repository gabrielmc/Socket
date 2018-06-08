
public class MultiThread{

    int compartilhada;

    MultiThread () {
        this.compartilhada = 1;
    }

    public static void main(String[] args) {
        MultiThread thread = new MultiThread();
        thread.run();
    }

    public void run() {
        UmaThread t1,t2,t3;

        t1 = new UmaThread("Primeira", this, (int)(Math.random()*8000));
        t2 = new UmaThread("Segunda", this, (int)(Math.random()*8000));
        t3 = new UmaThread("Terceira", this, (int)(Math.random()*8000));

        t1.start();
        t2.start();
        t3.start();
    }
}

