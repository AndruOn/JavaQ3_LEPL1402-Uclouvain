
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort<E> extends RecursiveAction {

    private volatile E[] array, aux;
    private int lo,hi;
    private Comparator<? super E> comp;
    
    private static final int threshold = 128;


    public ParallelMergeSort(E[] a, int lo, int hi, E[] aux, Comparator<? super E> comp) {
        this.array = a;
        this.lo = lo;
        this.hi = hi;
        this.aux = aux;
        this.comp = comp;
    }

    public E[] getArray(){ return array; }



    /*
     * Run a normal sort when the difference between hi and lo is under the threshold
     * Otherwise : Split the sub array in two and start the sort on each part of the array simultaneously
     */
    @Override
    protected void compute() {
        if (hi - lo < threshold) {
            sort(lo, hi);
        } else {
            int mid = (hi + lo) / 2;
            invokeAll(new ParallelMergeSort<E>(array, lo, mid, aux, comp),
                    new  ParallelMergeSort<E>(array, mid+1, hi, aux, comp)
            );
            merge(lo,mid,hi);
        }
    }

	//Sort array between lo and hi using merge sort
    private void sort(int lo, int hi){
        if (hi - lo > 0) {
            int mid = (lo + hi) / 2;
            sort(lo, mid);
            sort(mid + 1, hi);
            merge(lo, mid, hi);
        }
    }

    //merge two subarray and keep them sorted
    private void merge(int lo, int mid, int hi){
		int a_idx = lo, b_idx = mid+1, aux_idx = lo;
		while (a_idx <= mid && b_idx <= hi){
            if (comp.compare(array[a_idx], array[b_idx]) > 0) {
                aux[aux_idx++] = array[b_idx++];
            } else {
                aux[aux_idx++] = array[a_idx++];
            }
        }
        while (a_idx <= mid) {
            aux[aux_idx++] = array[a_idx++];
        }
        while ( b_idx <= hi) {
            aux[aux_idx++] = array[b_idx++];
        }

        System.arraycopy(this.aux,lo,array,lo,hi-lo+1);
    }

    public void printResultOfTask(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (E object : array) {
            stringBuilder.append(String.format(" %s,", Integer.toString((int) object)));
        }
        stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
        stringBuilder.append("]\n");
        System.out.print(stringBuilder.toString());
    }

    public static void main(String[] args) {
        int size = 1000;
        Integer[] array = new Integer[size];
        for(int i = 0 ; i < size ; i++){
            Random rng = new Random();
            array[i] = rng.nextInt(10000);
        }
        ParallelMergeSort task = new ParallelMergeSort(array, 0, size-1, new Integer[size],
                Comparator.comparing(Integer::intValue));
        new ForkJoinPool(3).invoke(task);

        task.printResultOfTask();
    }



}