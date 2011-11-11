
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class atlasPagination {

    public static int r;
    public static int c;
    public static int[][] pages;

    public static void main(String[] args) throws IOException {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null || line.equals("")) break;
                StringTokenizer st = new StringTokenizer(line);
                r = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                pages = new int[r][c];

                int page = 1;
                char next;
                for (int row = 0; row < r; row++) {
                    for (int col = 0; col < c; col++) {
                        next = (char) br.read();
                        if (next == 'X')
                            pages[row][col] = 0;
                        else 
                            pages[row][col] = page++;
                    }
                    br.read(); // read newline away
                }
                printAtlas();
                System.out.println();
            }
        }
        catch (IOException e) {
            System.out.println("Bummer.");
        }
    }

    public static void printAtlas() {
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (pages[x][y] != 0) {
                    System.out.println(pages[x][y] + " " + 
                            get(x-1,y) + " " + get(x, y+1) + " " +
                            get(x+1, y) + " " + get(x, y-1));
                    
                }
            }
        }
    }
    
    public static int get(int x, int y) {
        if (x < 0 || x >= r) return 0;
        if (y < 0 || y >= c) return 0;
        else return pages[x][y];
    }
}
