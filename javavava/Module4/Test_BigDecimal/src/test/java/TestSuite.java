import org.junit.Test;
import static org.junit.Assert.*;

public class TestSuite{

    @Test
    public void testPos(){
        char[] in = "100".toCharArray();
        int offset = 0;
        int len = in.length - offset;
        assertEquals(100,BigDecimal.parseExp(in, offset, len));
    }

    @Test
    public void testNeg(){
        char[] in = "-100".toCharArray();
        int offset = 0;
        int len = in.length - offset;
        assertEquals(-100,BigDecimal.parseExp(in, offset, len));
    }

    @Test
    public void testWithOffset(){
        char[] in = "2-100".toCharArray();
        int offset = 1;
        int len = in.length - offset;
        assertEquals(-100,BigDecimal.parseExp(in, offset, len));
    }

    @Test
    public void testError(){
        char[] in = "+100e".toCharArray();
        int offset = 0;
        int len = in.length - offset;

        boolean correct = false;
        try{
            BigDecimal.parseExp(in, offset, len);
        } catch (NumberFormatException e){
            correct = true;
        }
        assertTrue(correct);
    }

    @Test
    public void testEmpty(){
        char[] in = "".toCharArray();
        int offset = 0;
        int len = in.length - offset;

        boolean correct = false;
        try{
            BigDecimal.parseExp(in, offset, len);
        } catch (NumberFormatException e){
            correct = true;
        }
        assertTrue(correct);
    }

}