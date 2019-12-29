
public class FListMerge {
    /*
     * This method receives an FList and returns the FList containing the same values but sorted with the smallest value to the highest one.
     *
     */

    public static FList<Integer> rev(FList<Integer> fList){
        FList<Integer> reversed = new FList.Nil<>();
        while (fList.isNotEmpty()) {
            reversed = reversed.cons(fList.head());
            fList = fList.tail();
        }
        return reversed;
    }

    public static FList<Integer> mergeSort(FList<Integer> fList) {
        boolean toPrint = false;
        if (fList.length() <= 1) { return fList; }

        FList<Integer> firstHalf = new FList.Nil<Integer>();
        FList<Integer> secondHalf = new FList.Nil<Integer>();
        FList<Integer> current = fList;
        FList<Integer> sorted = FList.nil();
        for (int i = 0; i < fList.length() / 2; i++) {
            firstHalf = firstHalf.cons(current.head());
            current = current.tail();
        }
        for (int i = fList.length() / 2; i < fList.length(); i++) {
            secondHalf = secondHalf.cons(current.head());
            current = current.tail();
        }
        if (toPrint) {
            System.out.print("firstHalf: ");
            firstHalf.print();
            System.out.print("secondHalf: ");
            secondHalf.print();
        }
        firstHalf = mergeSort(firstHalf);
        secondHalf = mergeSort(secondHalf);
        if (toPrint) {
            System.out.println();
            System.out.print("firstHalfsorted: ");
            firstHalf.print();
            System.out.print("secondHalfsorted: ");
            secondHalf.print();
        }
        while(firstHalf.isNotEmpty() && secondHalf.isNotEmpty()){
            if(firstHalf.head() > secondHalf.head()){
                sorted = sorted.cons(secondHalf.head());
                secondHalf = secondHalf.tail();
            }
            else{
                sorted = sorted.cons(firstHalf.head());
                firstHalf = firstHalf.tail();
            }
        }
        while (firstHalf.isNotEmpty()){
            sorted = sorted.cons(firstHalf.head());
            firstHalf = firstHalf.tail();
        }
        while(secondHalf.isNotEmpty()){
            sorted = sorted.cons(secondHalf.head());
            secondHalf = secondHalf.tail();
        }
        if (toPrint){
            System.out.println();
            System.out.print("Return Value: ");
            rev(sorted).print();
            System.out.println();
        }
        return rev(sorted);
    }
}

