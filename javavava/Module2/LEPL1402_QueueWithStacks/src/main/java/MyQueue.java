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

    public void enqueue(E elem) {
      if (s1.isEmpty()) {
          front = elem;
      }
      s1.push(elem);
    }

    public E dequeue() {
      int l = s1.size();
      for (int i = 0; i < l; i++){
          s2.push(s1.pop());
      }
      E front = s2.pop();
      for (int i = 0; i < l; i++){
          s1.push(s2.pop());
      }
      return front;
    }

    public E peek() {
      return front;
    }

    public boolean empty() {
      return s1.isEmpty();
    }

    public void print(){
        for (E elem : s1) {
            System.out.print(elem+ " ");
        }
        System.out.println();
    }

    public static void main(String[] str){
        MyQueue q = new MyQueue();
        q.enqueue("caca");
        q.enqueue("pipi");
        q.enqueue("prout");
        q.print();
    }
}
