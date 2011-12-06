import java.util.HashMap;
import java.util.ArrayList;

public class Tester {
    
    // Instance variables
    public User user;
    public ArrayList<RandomItem> randomItems;
    public int n; // number of nearest neighbors
    public HashMap<Item, Double> itemsSimilarityScore;
    
    // This assigns each tag value a unique integer
    public HashMap<String, Integer> tagDict;
    
    public Tester() {
        user = new User();
        randomItems = new ArrayList<Item>();
        N = 20;
    }
    
    public Tester(User user) {
        this();
        this.user = user;
    }
    
    // algorithm:
    // for each item belonging to random user,
    // find the top 20 most correlated vectors;
    // for each item in randomitem, calculate the score
    // returns a sorted array of items
    
    public ArrayList<Item> preferences() {
        // for each user's item, find closest random items
        for (UserItem userItem : user.getItems()) {
            userItem.findClosestItems(randomItems);
        }
        // for each random item, calculate preference score
        for (RandomItem item : randomItems) {
            findPreferenceScore();
        }
        // sort the randomItems by itemsScore
        
        
    }
    
    
    
}