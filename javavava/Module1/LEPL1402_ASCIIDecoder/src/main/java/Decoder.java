import java.util.ArrayList;

public class Decoder {

    /*
     * Forbidden characters are passed as an array of int.
     * Each element of this array correspond to the decimal ASCII code
     * of a forbidden character OR null if there's no forbidden character
     * If you encounter one of these forbidden character
     * you must ignore it when you translate your sentence.
     *
     * the 2D array "sentences" contain a set of decimal ASCII code we want you
     * to translate. Each sub-element of this array is a different sentence.
     * Ex : if we pass this array : [ ["42", "72", "88"], ["98", "99", "111", "47", "55"]]
     * to your decode method, you should return : [ "*HX", "bco/7" ]
     *
     * You should NEVER return null or an array containing null
     */
    public static String [] decode(int[] forbidden, String[][] sentences){
        String[] decoded = new String[sentences.length];
        ArrayList<Integer> forbidden_List = new ArrayList<>();
        if (forbidden != null) {
            for (int i : forbidden) {
                forbidden_List.add(i);
            }
        }

        for (int sentence_index = 0; sentence_index < sentences.length; sentence_index++) {
            StringBuilder sentence = new StringBuilder();
            for (int char_index = 0; char_index < sentences[sentence_index].length; char_index++) {
                int character = Integer.parseInt(sentences[sentence_index][char_index]);
                if (!forbidden_List.contains(character)) {
                    sentence.append((char) character);
                }
            }
            decoded[sentence_index] = sentence.toString();
        }
        return decoded;
    }

}