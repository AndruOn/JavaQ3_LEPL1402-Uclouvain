import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {

    /**
     * Merge the array from lo to hi
     */
    /**
     * Pre-conditions: a[lo..mid] and a[mid+1..hi] are sorted
     * Post-conditions: a[lo..hi] is sorted
     */
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        int i = lo; //index de low à mid
        int j = mid + 1; // index de mid+1 à high
        int aux_index = lo;
        while (aux_index <= hi){
            if (i <= mid && j <= hi) {
                if (a[i] < a[j]) {
                    aux[aux_index++] = a[i++];
                } else {
                    aux[aux_index++] = a[j++];
                }
            } else if (i <= mid) {
                aux[aux_index++] = a[i++];
            } else {
                aux[aux_index++] = a[j++];
            }
        }
        System.arraycopy(aux, lo, a, lo, hi - lo + 1);
    }

    /**
     *  Split the array and call merge
     */
    /**
     * Rearranges the array in ascending order, using the natural order
     */
    public static void sort(int[] a) {
        mergeSort(a, new int[a.length], 0, a.length -1);
    }

    private static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if(hi - lo > 0) {
            int mid = (lo+hi)/2;
            mergeSort(a, aux, lo, mid);
            mergeSort(a, aux, mid+1, hi);
            merge(a, aux, lo, mid, hi);
        }
    }

    static void print(String actual,String expected){
        System.out.print("Actual:   ");
        System.out.println(actual);
        System.out.print("Expected: ");
        System.out.println(expected);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 5, 3, 9, 7, 10, 8, 6};
        int[] array2 = new int[]{2,5,4,0,7};
        sort(array1);
        print(Arrays.toString(array1), "[1, 2, 3, 5, 6, 7, 8, 9, 10]");
        sort(array2);
        print(Arrays.toString(array2), "[0, 2, 4, 5, 7]");
    }
}
