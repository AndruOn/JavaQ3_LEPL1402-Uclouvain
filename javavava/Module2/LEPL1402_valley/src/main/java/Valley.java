import java.lang.reflect.Array;
import java.util.Arrays;

public class Valley{
    /*
     * Example:
     * [1, 1, 1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, -1]
     * Should return
     * [5, 3]
     */

     public static int[] valley ( int[] array){
         int[] hauteurMax = new int[2];
         boolean enMonte;
         if (array[0] == 1){ enMonte = true; }
         else{ enMonte = false; }

         int hauteur = 0;
         int countMonte = 0;

         int profondeur = 0;
         int countDescente = 0;

         for (int i : array){
             //Les montagnes
             if (i == 1 && enMonte){
                 countMonte++;
             }
             else if (i == 1 && enMonte == false){
                 enMonte = true;
                 if (hauteur - countMonte > hauteurMax[0]){
                     hauteurMax[0] = hauteur - countMonte;
                     countMonte = 0;
                 }
                 countMonte++;
             }
             else{
                 if (enMonte) {
                     enMonte = false;
                     hauteur = countMonte;
                     if (countMonte > 0){
                         countMonte--;
                     }
                 }
                 else if (countMonte > 0){
                     countMonte--;
                     if (countMonte == 0){
                         if (hauteur > hauteurMax[0]){
                             hauteurMax[0] = hauteur;
                         }
                     }
                 }
             }
         }
         if (hauteur - countMonte > hauteurMax[0]) {
             hauteurMax[0] = hauteur - countMonte;
         }
         for (int i : array){
             //Les vallées
             if (i == -1 && !enMonte){
                 countDescente++;
             }
             else if (i == -1 && enMonte){
                 enMonte = false;

                 if (profondeur - countDescente > hauteurMax[1]){
                     hauteurMax[1] = profondeur - countDescente;
                     countDescente = 0;
                 }
                 countDescente++;
             }
             else{
                 if (!enMonte) {
                     enMonte = true;
                     profondeur = countDescente;
                     if (countDescente > 0){
                         countDescente--;
                     }
                 }
                 else if (countDescente > 0){
                     countDescente--;
                     if (countDescente == 0){
                         if (profondeur > hauteurMax[1])
                         hauteurMax[1] = profondeur;
                     }
                 }
             }
         }
         if (profondeur - countDescente > hauteurMax[1]) {
             hauteurMax[1] = profondeur - countDescente;
         }
         return new int[]{hauteurMax[1],hauteurMax[0]};
     }

    public static void main(String[] args){
        System.out.println( Arrays.toString( valley(new int[]{1, 1, 1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, -1 }) ));
        System.out.println( Arrays.toString( valley(new int[]{1, 1, 1, -1, -1, -1, 1, 1}) ));
        System.out.println( Arrays.toString( valley(new int[]{1, 1, 1, -1, -1, -1, -1, 1}) ));
        System.out.println( Arrays.toString( valley(new int[]{1, 1, 1, -1, -1, 1}) ));
        System.out.println( Arrays.toString( valley(new int[]{-1, 1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, -1}) ));
    }
}
