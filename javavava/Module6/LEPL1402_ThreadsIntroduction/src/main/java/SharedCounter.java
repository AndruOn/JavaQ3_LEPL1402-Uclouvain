public class SharedCounter {
    private int counter = 0;

    public SharedCounter() { }

    public int get() {
        return counter;
    }

    public void inc(){
        synchronized (this){
            counter++;
            notify();
        }
    }

    public void dec(){
        synchronized (this){
            try{
                while(counter == 0) {
                    wait();
                }
            } catch (InterruptedException e) {}
            counter--;
        }
    }

}
