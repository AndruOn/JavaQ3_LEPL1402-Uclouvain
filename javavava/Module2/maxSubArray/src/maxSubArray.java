import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class maxSubArray {
    /*
     *    Given the array [-2,1,-3,4,-1,2,1,-5,4]
     *    The contiguous subarray that produces the best result is [4,-1,2,1]
     *    For this array your method should return [6, 3, 6]
     */

    //Complexity O(n^2)
    public static int[] maxSubArray(int[] a){
        int[] returnArray = new int[]{a[0],0,0};
        int[] cumsum = new int[a.length+1];

        cumsum[0] = 0;
        for (int i = 0; i < a.length; i++) {
            cumsum[i+1] = cumsum[i] + a[i];
        }

        for (int start = 0; start < a.length; start++) {
            for (int end = start; end < a.length; end++) {
                int sum = cumsum[end+1] - cumsum[start];

                if ( sum > returnArray[0] ){
                    returnArray[0] = sum;
                    returnArray[1] = start;
                    returnArray[2] = end;
                }
            }
        }
        return returnArray;
    }

    // complexity O(n)
    public static int[] maxSubArraySum(int a[]) {
        int max = a[0], max_ending_here = 0,
                from = 0, to = 0, s = 0;

        for (int i = 0; i < a.length; i++) {
            max_ending_here += a[i];

            if (max < max_ending_here) {
                max = max_ending_here;
                from = s;
                to = i;
            }

            if (max_ending_here < 0) {
                max_ending_here = 0;
                s = i + 1;
            }
        }
        return new int[]{max, from, to};
    }



    public static void main(String[] args) {
        int[] a = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(Arrays.toString(maxSubArray(a))); //[6, 3, 6]
        int[] b = new int[]{3,-2,4,3,-2,3};
        System.out.println(Arrays.toString(maxSubArray(b)));//[9, 0, 5]

        System.out.println(Arrays.toString(maxSubArraySum(new int[]{-1})));
    }

}
