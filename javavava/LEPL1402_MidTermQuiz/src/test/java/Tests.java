
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class Tests {
    @Test
    public void testExample(){
        LinkedList l = new LinkedList(new int[]{7, 1, 102, 6, 9, 5});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
}
