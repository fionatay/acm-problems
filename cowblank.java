import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class cowblank {
    private static int numCows;
    private static int numBlankets;

    private static Integer[] cows;
    private static Integer[] blankets;


    public static void main(String [] args){
        Scanner s = new Scanner(System.in);
        numCows = s.nextInt();
        numBlankets = s.nextInt();

        cows = new Integer[numCows];
        for (int cow = 0; cow < numCows; cow++)
            cows[cow] = s.nextInt();
        Arrays.sort(cows);

        blankets = new Integer[numBlankets];
        for (int blank = 0; blank < numBlankets; blank++)
            blankets[blank] = s.nextInt();
        Arrays.sort(blankets);

        System.out.println(search());
    }

    public static int search(){
        int lo = 0;
        int hi = cows[numCows-1];
        int lowest = hi;
        
        while (lo <= hi) {
            int mid = (lo + hi)/2;
            // Mid is viable, so start searching below it
            if (viable(mid)) {
                lowest = mid;
                hi = mid-1;
            }

            // Mid is not viable, so start searching slightly above it
            else {
                lo = mid+1;
            }
        }
        return lowest;
    }

    public static boolean viable(int minColdness){
        ArrayList<Integer> remainingCows = new ArrayList<Integer>(
                Arrays.asList(cows));
        ArrayList<Integer> remainingBlankets = new ArrayList<Integer>(
                Arrays.asList(blankets));

        while (!remainingCows.isEmpty()) {
            int first = remainingCows.get(0);

            // Try to satisfy the cow without a blanket
            if (first <= minColdness)
                remainingCows.remove(0);

            // Try and find a blanket
            else if (remainingBlankets.isEmpty())
                return false;

            // Try the first blanket
            else {
                int firstBlanket = remainingBlankets.get(0);
                remainingBlankets.remove(0);
                if (Math.abs(first-firstBlanket) <= minColdness)
                    remainingCows.remove(0);
            }
        }
        return true;
    }

}
