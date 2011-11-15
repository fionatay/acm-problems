import java.util.Scanner;


public class vampire {
    
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String vampire;
        while ((vampire = s.nextLine()).equals("0")) {
            String answer;
            try {
                permutable(vampire);
                answer = "no";
            }
            catch (Exception e) {
                answer = "yes";                
            } 
            System.out.println(vampire + ": " + answer);
        }
    }
    
    public static void permutable(String num) throws Exception {                
        int N = num.length()/2;
        if (2*N != num.length())
            return; // Stop trying
        
        long product = Long.parseLong(num);
        int[] permutation = new int[2*N];
        int X, Y;
        int[] indices;
        
        PermutationGenerator x = new PermutationGenerator(N*2);
        while (x.hasMore()) {
            indices = x.getNext();

            for (int i = 0; i < 2*N; i++) 
                permutation[indices[i]] = num.charAt(i) - '0'; // Fill in the permutation
//            System.out.println("Permutation is " + Arrays.toString(permutation));
            multiplies(permutation, N, product);
        }
    }
    
    public static void multiplies(int[] perm, int N, long product) throws Exception {
        if (perm[0] == 0 || perm[N] == 0) return; // invalid perm
        else {
            long X = perm[0]; 
            long Y = perm[N];
            for (int i = 1; i < N; i++) {
                X *= 10; Y *= 10;
                X += perm[i]; Y += perm[i+N];
                if (perm[i] == 0 && perm[i-1] == 0) return; // invalid perm
                if (perm[i+N] == 0 && perm[i+N-1] == 0) return; // invalid perm
            }
            if (X*Y == product) {
//                System.out.println("Product is correct " + X + "*" + Y + " = " + product);
                throw new Exception();
            }
        }
        
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
