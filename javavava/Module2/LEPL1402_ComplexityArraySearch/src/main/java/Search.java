
public class Search {

    /**
     * @param tab is an ordered array of int.
     * @return index of elem or -1
     */

    public static int search(int[] tab, int elem) {
        if (tab.length == 0) {
            return -1;
        }

        int a = 0;
        int b = tab.length -1;
        int milieu = b / 2;
        while (a <= b) {
            if (tab[milieu] == elem) {
                return milieu;
            }
            if (tab[milieu] > elem) {
                b = milieu - 1;
            } else {
                a = milieu + 1 ;
            }
            milieu = (a + b) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{ 0,1,2,3,4,5,6,7,8 };
        System.out.print("Actual: ");
        System.out.print(search(array, 2));
        System.out.println("  Excepted: 2");
        System.out.print("Actual: ");
        System.out.print(search(array, 8));
        System.out.println("  Excepted: 8");
    }
}
