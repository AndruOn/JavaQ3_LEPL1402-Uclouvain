import org.junit.Test;
import static org.junit.Assert.*;

public class TestSuite{

    @Test
    public void test(){
        int[] arr = new int[]{1,3,4,5,7,10};
        assertEquals(Exercise.binarySearch(arr,0,arr.length,7),4);
    }

    @Test
    public void testNotPresent(){
        int[] arr = new int[]{1,3,4,5,7,10};
        assertEquals(Exercise.binarySearch(arr,0,4,8),-1);
    }

    @Test
    public void testPreNotRespected(){
        int[] arr = new int[]{1,3,4,5,7,10};
        assertEquals(Exercise.binarySearch(arr,5,4,8),-2);
    }

    @Test
    public void testFirst(){
        int[] arr = new int[]{1,3,4,5,7,10};
        assertEquals(Exercise.binarySearch(arr,0,4,1),0);
    }


}