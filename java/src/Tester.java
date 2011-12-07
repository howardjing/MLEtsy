import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tester {
    
    // Instance variables
    public User user;
    public RandomItems randomItems;

    // This assigns each tag value a unique integer
    public HashMap<String, Integer> allTagsDict;

    public HashMap<Item, Double> itemsSimilarityScore;
    public int n; // number of nearest neighbors
    
    // The found preferences
    public ArrayList<RandomItem> preferences;
    
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
        
        // prepare dictionary, update all items appropriately
        this.allTagsDict = this.prepareAllTagsDict();
        for (UserItem item : user.items) {
            item.setTagsID(allTagsDict);
        }
        for (RandomItem item : randomItems.items) {
            item.setTagsID(allTagsDict);
        }
        
        this.findPreferences();
    }
    
    public HashMap<String,Integer> prepareAllTagsDict() {
        HashMap<String, Integer> temp = new HashMap<String, Integer>();
        
        for (String key : this.user.tagDict.keySet()) {
            temp.put(key, (temp.size() + 1));
        }
        
        for (String key : this.randomItems.tagDict.keySet()) {
            if (!temp.containsKey(key)) {
                temp.put(key, (temp.size() + 1));
            }
        }
        
        return temp;
    }
    
    // algorithm:
    // for each item belonging to random user,
    // find the top 20 most correlated vectors;
    // for each item in randomitem, calculate the score
    // returns a sorted array of items
    public void findPreferences() {
        // for each user's item, find closest random items
        for (UserItem userItem : user.getItems()) {
            userItem.findClosestItems(randomItems.getItems());
        }
        
        // for each random item, calculate preference score
        for (RandomItem item : randomItems.getItems()) {
            item.setClosestItems(user.getItems());
            item.findPreferenceScore(n);
        }
        
        // sort randomItems by preference from greatest to least
        // I think this gives an xlint warning for some reason
        // TDL: MAKE THIS ITS OWN CLASS???
        Comparator<RandomItem> preferenceComparator = new Comparator<RandomItem>() {
            // compares by similarity score
            public int compare(RandomItem a, RandomItem b) {
                if (a.preferenceScore < b.preferenceScore) {
                    return 1;
                } 
                else if (a.preferenceScore > b.preferenceScore) {
                    return -1;
                } 
                return 0;   
            }
        };
        
        // sort the randomItems by randomitems's preference score
        Collections.sort(randomItems.items, preferenceComparator);
        preferences = (ArrayList<RandomItem>)randomItems.items.clone();
        
    }
    
    public static void main(String[] args) {
        User testUser = new User("data/MadeUpTrainingData.txt");
        RandomItems testRandomItems = new RandomItems("data/MadeUpTestingData.txt");
        
        Tester test = new Tester(testUser, testRandomItems);
        System.out.println("Dict: " + test.allTagsDict.toString());
        for (RandomItem item : test.preferences) {
            System.out.println(item);
        }
        
    }

}