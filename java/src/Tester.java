import java.util.HashMap;
import java.util.ArrayList;

public class Tester {
    
    // Instance variables
    public User user;
    public RandomItems randomItems;

    // This assigns each tag value a unique integer
    public HashMap<String, Integer> allTagsDict;

    public HashMap<Item, Double> itemsSimilarityScore;
    public int n; // number of nearest neighbors
    
    public Tester() {
        user = new User();
        randomItems = new RandomItems();
        
        allTagsDict = new HashMap<String, Integer>();
        
        itemsSimilarityScore = new HashMap<Item, Double>();
        n = 20;
    }
    
    public Tester(User user, RandomItems randomItems) {
        this();
        
        this.user = user;
        this.randomItems = randomItems;
        
        this.allTagsDict = this.prepareAllTagsDict();
    }
    
    public HashMap<String,Integer> prepareAllTagsDict() {
        HashMap<String, Integer> temp = new HashMap<String, Integer>();
        
        for (String key : this.user.tagDict) {
            temp.put(key, (temp.size() - 1));
        }
        
        for (String key : this.randomItems.tagDict) {
            if (!temp.containsKey(key)) {
                temp.put(key, (temp.size() - 1));
            }
        }
        
        return temp;
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