public class Semaphore{

    protected int count;

    public Semaphore(){
        this.count = 0;
    }
    public Semaphore(int i){
        this.count = i;
    }

    public synchronized void down(){
        while(this.count == 0){
            try{
                wait();
            }catch(InterruptedException _ex){}
        }
        this.count--;
    }

    public synchronized void up(){
        this.count++;
        notify();
    }

}
