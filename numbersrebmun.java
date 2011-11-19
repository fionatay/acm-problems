
import java.util.Scanner;

public class numbersrebmun {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int cases = Integer.parseInt(s.nextLine());
        for (int iter = 0; iter < cases; iter++) {
            boolean b = isPalindrome(s.nextLine());
            String result = b ? "YES" : "NO";
            System.out.println(result);
        }
    }
    public static boolean isPalindrome(String num) {
        num = num.toLowerCase();
        int lo = 0;
        int hi = num.length()-1;
        while (lo < hi) {
            if (numVal(num.charAt(lo)) != numVal(num.charAt(hi))) return false;
            lo++; hi--;
        }
        return true;
    }
    
    public static int numVal(char a) {
        int offset = a - 'a';
        if (offset < 3) return 0;
        if (offset < 6) return 1;
        if (offset < 9) return 2;
        if (offset < 12) return 3;
        if (offset < 15) return 4;
        if (offset < 19) return 5;
        if (offset < 22) return 6;
        if (offset < 26) return 7;
        else return -1;
    }
    
}
