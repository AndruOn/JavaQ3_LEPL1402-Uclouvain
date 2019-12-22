import java.util.LinkedList;

public class MyStack<E> {

    /*
    I can only use these functions from LinkedList:
    add
    remove
    peek
    isEmpty
    size

    and cannot instantiate another LinkedList than queue
     */
    private LinkedList<E> queue;

    /*
     * Constructor
     */
    public MyStack() {
        this.queue = new LinkedList<>();
    }

    /*
    * push an element at top (remember, a stack is "Last In First Out")
    */
    public void push(E elem) {
        queue.add(0, elem);
    }

    /*
    * Return the top of the stack AND remove the retrieved element
    */
    public E pop() {
        E head = queue.remove(0);
        return head;
    }

    /*
    * Return the top element of the stack, without removing it
    */
    public E peek() {
        E head = queue.remove(0);
        queue.add(0,head);
        return head;
    }

    /*
    * Tells if the stack is empty or not
    */
    public boolean empty() {
        return queue.isEmpty();
    }

    public void Print(){
        System.out.println("Printing MysStack");
        while (!empty()){
            System.out.println(queue.remove());
        }
    }

    public static void main(String[] args){
        MyStack s = new MyStack();
        s.push("hey");
        s.push("2");
        s.push("3");
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
        s.Print();


    }

}
