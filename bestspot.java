
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bestspot {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int nodes = s.nextInt();
        int favorites = s.nextInt();
        List<Integer> favs = new ArrayList<Integer>();
        
        int paths = s.nextInt();
        
        for (int f = 0; f < favorites; f++) {
            favs.add(s.nextInt()-1);
        }
        
        // zero out the adjacency matrix 
        int distances[][] = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                distances[i][j] = 7000000;
            }
            distances[i][i] = 0;
        }

        // add in the roads
        for (int i = 0; i < paths; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            int distance = s.nextInt();
            distances[a-1][b-1] = Math.min(distances[a-1][b-1], distance);
            distances[b-1][a-1] = distances[a-1][b-1];
        }

        // FW
        for (int k = 0; k < nodes; k++) {
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    distances[i][j] = Math.min(distances[i][j], 
                            distances[i][k]+distances[k][j]);
                }
            }
        }
        int bestDistance = Integer.MAX_VALUE;
        int bestSpot = -1;
        for (int spot = 0; spot < nodes; spot++) {
            int dist = 0;
            try { 
                for (int fav : favs) {
                    dist += distances[spot][fav];
//                    System.out.println("Distance to fav pasture " + fav + " was " + distances[spot][fav]);
                    if (dist >= bestDistance) {
                        throw new Exception();
                    }
                }
//                System.out.println("This is a better pasture with distance " + dist);
                bestDistance = dist; 
                bestSpot = spot;
            }
            catch (Exception e) {
            }
        }
        
        System.out.println(bestSpot+1);
        
    }
}
