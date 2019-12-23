import java.util.BitSet;

public class Sieve{
    
    public static BitSet bits; //You should work on this BitSet

    //Number of prime numbers between 0 and n
    public static int numberOfPrime(int n){
        bits = new BitSet(n);
        bits.clear();
        bits.flip(2, n);
        int p = 2;
        int count;
        int pfois = 2 * p;
        while (p > 0 && p < n/2 +1) {
            count = 2 ;
            pfois = p*count;
            while (pfois <= n) {
                bits.set(pfois, false);
                count++;
                pfois = p*count;
            }
            p = bits.nextSetBit(p+1);
        }
        //System.out.println(bits.cardinality());
        return bits.cardinality();
    }

    public static void main(String[] args) {
        numberOfPrime(25);
    }
}