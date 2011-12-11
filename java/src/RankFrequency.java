import control.Frequency;
import representation.User;
import representation.RandomItems;

// This is going to be the driver class for ranking random items
// It will take as input the path to the user's items and the path to the random items.
// It will print the random items in order from most relevant to least relevant
// (DOES IT IN A CSV FORMAT)
// Should redirect this output to a desired file
public class RankFrequency {
    public static void main(String[] args) {
        
        if (args.length == 2) {
            String userItemsPath = args[0];
            String randomItemsPath = args[1];
            User testUser = new User(userItemsPath);
            RandomItems testRandomItems = new RandomItems(randomItemsPath);
            Frequency test = new Frequency(testUser, testRandomItems);
            // this is buggy
            //test.printError();
            //System.out.println("label,id");
            test.printPreferences();
        }
        else {
            System.out.println("usage: java -Xmx2048m -classpath [path to classes] RankRandomItems [path to user's items] [path to random items]");
        }
    }
}