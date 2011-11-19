import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class classs {
    public static class Class {
        public int location;
        public int energy;
        
        public Class(int loc, int energy) {
            this.location = loc; this.energy = energy;
        }
        
    }
    
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        for (int trial = 0; trial < n; trial++) {
            int cats = s.nextInt();
            int choices = s.nextInt();
            int length = s.nextInt();
            
            List<Class> lastClasses = new LinkedList<Class>();
            lastClasses.add(new Class(0, 0));
            
            for (int cat = 0; cat < cats; cat++) {
                List<Class> thisClasses = new LinkedList<Class>();
                
                // Figure out the least energy for taking this class.
                for (int choice = 0; choice < choices; choice++) {
                    Class thisClass = new Class(s.nextInt(), s.nextInt());
                    int minEnergy = 100000;
                    for (Class lastClass : lastClasses)
                        minEnergy = Math.min(minEnergy, lastClass.energy + Math.abs(lastClass.location - thisClass.location));
                    thisClasses.add(new Class(thisClass.location, 
                            thisClass.energy + minEnergy));
//                    System.out.println("For slot " + cat + ", " + "taking class " + choice + " has min energy of " + (thisClass.energy + minEnergy));
                }
                lastClasses = thisClasses;
            }
            
            // Select the best class to take
            int minEnergy = 100000;
            for (Class last : lastClasses)
                minEnergy = Math.min(minEnergy, last.energy + (length - last.location));
            System.out.println(minEnergy);
        }
    }
}
