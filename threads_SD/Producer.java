public class Producer extends Thread {
    Programa programa;
    int contador;

    public Producer(Programa x) {
        this.programa = x;
        this.contador = 0;
    }

    public void run() {
        try {
            while (true) {
                while (this.programa.itemCount == 10)
                    sleep(100);

        this.contador ++;
        this.programa.buffer.add(this.contador);
        this.programa.itemCount++;

        System.out.println("produtor: producing item "+this.contador);
        for (int i =0;i<10000;i++);
        }

        }catch(InterruptedException e) {
            e.printStackTrace();
        }

    }


}
