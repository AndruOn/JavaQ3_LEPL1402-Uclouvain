import java.util.List;
import java.awt.Color;

import java.util.Comparator;
import java.util.List;

public class Sorter {


    /*
     * Should sort the list and order the Flower by color, in this specific order : red, blue, green
     */
    public static void sortFlowerByColor(List<Flower> list){
        boolean sorted = false;
        Flower temp;
        while (!sorted){
            sorted = true;
            for (int i =  0; i < list.size()-1; i++){
                if (list.get(i).getPetalColor().getRGB() > list.get(i + 1).getPetalColor().getRGB()){
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1,temp);
                    sorted = false;
                }

            }
        }
    }
    /*
     * Should sort the Plant by name only
     */
    public static void sortPlantByName(List<Plant> list){
        list.sort(Plant::compareTo);
    }

    /*
     * Should sort the list and order the Tree by height
     */
    public static void sortTreeByHeight(List<Tree> list){
        //TODO by student
        boolean sorted = false;
        Tree temp;
        while (!sorted){
            sorted = true;
            for (int i =  0; i < list.size()-1; i++){
                if ( list.get(i).getHeight() > list.get(i + 1).getHeight() ){
                    temp = list.get(i);
                    list.set(i,list.get(i + 1));
                    list.set(i + 1,temp);
                    sorted = false;
                }
            }
        }
    }
}
