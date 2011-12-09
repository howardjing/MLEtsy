import control.Ranker;
import representation.User;
import representation.RandomItems;

public class FirstRun {
    public static void main(String[] args) {
        System.out.println("Hello World");
        User testUser = new User("data/training.txt");
        RandomItems testRandomItems = new RandomItems("data/testing.txt");
    
        Ranker test = new Ranker(testUser, testRandomItems);
        //Ranker test = new Ranker(testUser, testRandomItems);
        //System.out.println("Dict: " + test.allTagsDict.toString());
        test.printPreferences();
    
    }
}