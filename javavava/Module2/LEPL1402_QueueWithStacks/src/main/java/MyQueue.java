import java.util.Stack;

public class MyQueue<E> {

    Stack<E> s1;
    Stack<E> s2;

    private E front;

    /*
     * Constructor
     */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
        this.front = null;
    }

    /*
     * Push element x to the end of the queue (remember, a queue is FIFO)
     */
    public void enqueue(E elem) {
        if (s1.isEmpty()) {
              front = elem;
        }
        s1.push(elem);
    }

    /*
     * Removes the front element of this queue
     */
    public E dequeue() {
        int l = s1.size();
        for (int i = 0; i < l; i++){
          s2.push(s1.pop());
        }
        E front = s2.pop();
        for (int i = 0; i < l-1; i++){
          s1.push(s2.pop());
        }
        return front;
    }

    /*
     * Get the first element of this list but does not remove it
     */
    public E peek() {
      return front;
    }

    /*
     * Tells if the queue is empty or not.
     */
    public boolean empty() {
      return s1.isEmpty();
    }

    public void print(){
        System.out.println("The Stack:");
        for (E elem : s1) {
            System.out.print(elem+ " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] str){
        MyQueue q = new MyQueue();
        q.enqueue("caca");
        q.enqueue("pipi");
        q.enqueue("prout");
        q.print();
        q.dequeue();
        q.print();
        q.dequeue();
        q.print();
    }
}
