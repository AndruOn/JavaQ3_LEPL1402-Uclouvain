public class Sorter {
    /**
    * Sorts a list in increasing order
    *
    * We expect this function to behave in O(n^2), where n is the size of the list. 
    * Precisely, we expect that you make at MOST n^2 calls to list.pop() and at 
    * MOST n^2 calls to list.swap(). 
    * 
    * You will obtain the full score for a test case if
    *
    * - The list is sorted (2/4 points per test case)
    * - The list is sorted AND you make less than n^2 calls to swap (1/4 points)
    * - The list is sorted AND you make less than n^2 calls to pop (1/4 points)
    *
    * @param list: a list of integers to be sorted.
    */
    public static void sortwithprint(LinkedList list) {
        // Here is a small loop with an invariant that you should try to respect
        // although it's not mandatory. Try to respect it, it will help you ;-)
        System.out.println(list);
        System.out.print("size: ");
        System.out.println(list.getSize());
        System.out.println();
        if (list.getSize() == 0 || list.getSize() == 1){
            return;
        }
        if (list.getSize() == 2){
            if (list.getFirst() > list.getSecond()){
                list.swap();
                return;
            }
            return;
        }
        boolean isSorted = false;
        while(!isSorted) {
            isSorted = true;
            for (int iter = 0; iter < list.getSize() - 1; iter++) {
                //invariant: the 'iter' biggest elements are at the end of the list and are sorted.
                //example, at iteration iter=3, the three lasts elements are the three biggest elements in the list, and
                //they are in the increasing order.
                if (list.getFirst() > list.getSecond()){
                    list.swap();
                    isSorted = false;
                    System.out.println("ya eu swap");
                }
                list.pop();
                System.out.println("i: "+ iter);
                System.out.println(list);
            }
            System.out.println("1 for fini list a la fin du for:");
            System.out.println(list);
            System.out.println();
            list.pop();

        }
        // here, if you followed the invariant proposed above, the list should be sorted!

    }
    public static void sort(LinkedList list) {
        // Here is a small loop with an invariant that you should try to respect
        // although it's not mandatory. Try to respect it, it will help you ;-)
        if (list.getSize() == 0 || list.getSize() == 1){
            return;
        }
        if (list.getSize() == 2){
            if (list.getFirst() > list.getSecond()){
                list.swap();
                return;
            }
            return;
        }
        boolean isSorted = false;
        while(!isSorted) {
            isSorted = true;
            for (int iter = 0; iter < list.getSize() - 1; iter++) {
                //invariant: the 'iter' biggest elements are at the end of the list and are sorted.
                //example, at iteration iter=3, the three lasts elements are the three biggest elements in the list, and
                //they are in the increasing order.
                if (list.getFirst() > list.getSecond()){
                    list.swap();
                    isSorted = false;
                }
                list.pop();
            }
            list.pop();
        }
        System.out.println(list.getnPops());
        System.out.println(list);
    }
}
