import org.jacoco.agent.rt.internal_035b120.IExceptionLogger;

import static java.lang.Double.isNaN;

public class IntroductionExercises {

    public static int variable = 0;

    public static int[] squares;

    /*
     * Function that bound variable to value
     */
    //TODO attribute(int value) method
    public static void attribute(int value){

        variable = value;
    }

    /*
     * Function that return the addition of the two parameters
     */
    //TODO add(int a, int b) method
    public static int add(int a, int b){

        return (a + b);
    }

    /*
     * return true is a and b are equal
     */
    //TODO equalsIntegers(int a, int b) method
    public static boolean equalsIntegers(int a, int b){

        return (a == b);
    }

    /*
     * Function that return the max between a and b
     * You must use a ternary operation
     */
    public static int max(int a, int b){
        //TODO the body of this function in one line
        return (a > b) ? a : b ;
    }

    /*
     * Function that return the middle value.
     * If a > b > c, the function must return b.
     * If two value are equals, return -1.
     */
    //TODO middleValue(int a, int b, int c) method
    public static int middleValue(int a, int b, int c){
        if (a == b || b == c || a == c){
            return -1;
        }
        if (a > b && b > c || c > b && b > a){
            return b;
        }
        else if (b > a && a > c || c > a && a > b){
            return a;
        }
        else {
            return c;
        }
    }

    /*
     * This function must return :
     * "Good morning, sir!" if str is "Morning"
     * "Good evening, sir!" if str is "Evening"
     * "Hello, sir!" otherwise
     * Use a switch case statement
     * Your implementation must be case sensitive
     */
    //TODO greetings(String str) method
    public static String greetings(String str){
        switch (str){
            case "Morning":
                return "Good morning, sir!";
            case "Evening":
                return "Good evening, sir!";
            default:
                return "Hello, sir!";
        }
    }


    /*
     * This function must return a new array of length 3
     * The first element of this new array is the last element of a
     * The second element is the first element of a
     * The last element is the middle element of a
     */
    //TODO lastFirstMiddle(int[] a)
    public static int[] lastFirstMiddle(int[] a){
        int[] pute = new int[3];
        pute[0] = a[-1];
        pute[1] = a[0];
        pute[2] = a[(int)(a.length)/2];
        return pute;
    }

    /*
     * This function must return the sum of the elements of array using a for loop
     */
    //TODO sum(int[] array) method
    public static int sum(int[] array){
        int total = 0;
        for (int x : array) {
            total += x;
        }
        return total;
    }

    /*
     * return the maximum element of array using a while loop
     */
    //TODO maxArray(int[] array) method
    public static int maxArray(int[] array){
        int max = 0;
        int count = 0;
        int len = array.length;
        while (count < len){
            if (max < array[count]){
                max = array[count];
            }
            count += 1;
        }
        return max;
    }

    /*
     * Using the argument of the program
     * Bound the variable squares with the square of
     * the elements passed in argument.
     * Look at the java API : https://docs.oracle.com/javase/8/docs/api/index.html
     * If an exception occurs, assign the value 0 at the index where its occurs
     */
    public static void main(String[] args){
        System.out.println("hello");
        int l = args.length;
        squares = new int[l];
        for (int i = 0 ; i < l ; i ++){
            try {
                squares[i] = i * i;
            }
            catch (Exception e){
                System.out.println("Please enter numbers");
            }
        }
        System.out.println(squares);
    }

}
