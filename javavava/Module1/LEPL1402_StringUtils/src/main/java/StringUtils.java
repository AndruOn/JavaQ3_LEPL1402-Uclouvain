import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {


    /*
     * Split the input string 'str' w.r.t the character 'marker' in an array of String
     * for example split("test-test", '-') => {"test", "test"}
     * Must return null if there is no occurrence of 'marker' in 'str'
     */
    public static String [] split(String str, char marker){
        if (!str.contains(Character.toString(marker))) {
            return null;
        }
        int count = 1;
        for (int x = 0; x < str.length(); x++) {
            if (str.charAt(x) == marker) {
                count++;
            }
        }
        String[] splitted = new String[count];
        int iter = 0;
        int begin = 0;
        for (int x = 0; x < str.length(); x++) {
            if (str.charAt(x) == marker) {
                splitted[iter] = str.substring(begin, x);
                begin = x + 1;
                iter++;
            }
            if (x == str.length() - 1) {
                splitted[iter] = str.substring(begin, x + 1);
            }
        }
        return splitted;
    }


    /*
     * Returns the index of the first occurrence of sub in str
     * or -1 if there is no occurrence of sub in str at all.
     * Be careful, we ask you to make CASE SENSITIVE comparison between str and sub.
     */
    public static int indexOf(String str, String sub){
        int len = str.length();
        int lenSub = sub.length();
        for (int i = 0; i <= len - lenSub; i++) {
            if (str.substring(i, i + lenSub).equals(sub)) { // substring doesn't take the char at i + lenSub (stops before)
                return i;
            }
        }
        return -1;
    }


    public static String toLowerCase(String str){
        char[] s1 = str.toCharArray();
        int diff = 'a' - 'A';

        for (int i=0; i < s1.length; i++) {
            if (s1[i] >= 'A' && s1[i] <= 'Z') {
                s1[i] += diff;
            }
        }
        return String.valueOf(s1);
    }


    /*
     * Returns true if the string 'str' is a palindrome (a string that reads the same from
     * left to right AND from right to left).
     */
    public static boolean palindrome(String str){
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "Hello.Ill.Go.";
        System.out.println(Arrays.toString(StringUtils.split(s, '.')));
        System.out.println(StringUtils.indexOf(s, "ello"));
    }
}
