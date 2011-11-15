
import java.util.Scanner;


public class vampire {
    
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int vampire;
        while ((vampire = s.nextInt()) != 0) {
            String vamp = Integer.toString(vampire);
            String yesno = permutable(vamp) ? "yes" : "no";
            System.out.println(vamp + ": " + yesno);
        }
    }
    
    public static boolean permutable(String num) {
        int N = num.length()/2;
        int product = Integer.parseInt(num);
        int X;
        int Y;
        
        int[] indices;
        PermutationGenerator x = new PermutationGenerator(N*2);
        while (x.hasMore()) {
            indices = x.getNext();

            X = Y = 0;
            for (int i = 0; i < N; i++) {
                X *= 10;
                Y *= 10;
                X += num.charAt(indices[i]) - '0';
                Y += num.charAt(indices[i+N]) - '0';
            }
//            System.out.println("X is " + X + "; Y is " + Y);
            if (X*Y == product)
                return true;
        }
        return false;
    }

    public static class PermutationGenerator {

        private int[] a;
        private long numLeft;
        private long total;

        //-----------------------------------------------------------
        // Constructor. WARNING: Don't make n too large. n! has to fit into a long.
        //----------------------------------------------------------
        public PermutationGenerator(int n) {
            if (n < 1) {
                throw new IllegalArgumentException("Min 1");
            }
            a = new int[n];
            total = getFactorial(n);
            reset();
        }

        //------
        // Reset
        //------
        public void reset() {
            for (int i = 0; i < a.length; i++) {
                a[i] = i;
            }
            numLeft = total;
        }

        //------------------------------------------------
        // Return number of permutations not yet generated
        //------------------------------------------------
        public long getNumLeft() {
            return numLeft;
        }

        //------------------------------------
        // Return total number of permutations
        //------------------------------------
        public long getTotal() {
            return total;
        }

        //-----------------------------
        // Are there more permutations?
        //-----------------------------
        public boolean hasMore() {
            return numLeft > 0;
        }

        //------------------
        // Compute factorial
        //------------------
        private static long getFactorial(int n) {
            long fact = 1;
            for (int i = n; i > 1; i--) {
                fact = fact * i;
            }
            return fact;
        }

        //--------------------------------------------------------
        // Generate next permutation (algorithm from Rosen p. 284)
        //--------------------------------------------------------
        public int[] getNext() {

            if (numLeft==total) {
                numLeft = numLeft-1;
                return a;
            }

            int temp;

            // Find largest index j with a[j] < a[j+1]

            int j = a.length - 2;
            while (a[j] > a[j + 1]) {
                j--;
            }

            // Find index k such that a[k] is smallest integer
            // greater than a[j] to the right of a[j]

            int k = a.length - 1;
            while (a[j] > a[k]) {
                k--;
            }

            // Interchange a[j] and a[k]

            temp = a[k];
            a[k] = a[j];
            a[j] = temp;

            // Put tail end of permutation after jth position in increasing order

            int r = a.length - 1;
            int s = j + 1;

            while (r > s) {
                temp = a[s];
                a[s] = a[r];
                a[r] = temp;
                r--;
                s++;
            }

            numLeft = numLeft - 1;
            return a;

        }
    }
}
