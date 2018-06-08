class UmaThread extends Thread {

    private int delay;
    MultiThread multThread;

    public UmaThread(String identificacao, MultiThread x, int delay) {
        super(identificacao);
        this.multThread = x;
        this.delay = delay;
    }

    public void run() {
        String identificacao = this.getName();
        try {
            sleep(this.delay);
            this.multThread.compartilhada = this.multThread.compartilhada + 2;

        }catch(InterruptedException e) {
            System.out.println("Thread:  "+ identificacao + " foi interrompida");
        }
        System.out.println(">>" + identificacao + " " + delay);
        System.out.println("Variavel = " + this.multThread.compartilhada + " ");
    }

}

