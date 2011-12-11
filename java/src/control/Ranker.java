package control;

import representation.User;
import representation.RandomItems;
import representation.Item;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranker extends BaseRanker{
    
    public int n; // number of nearest neighbors
    
    // This assigns each tag value a unique integer
    public HashMap<String, Integer> allTagsDict;
    
    public Ranker() {
        super();
        n = 20;
        allTagsDict = new HashMap<String, Integer>();
    }
    
    public Ranker(User user, User randomUser) {
        this();

        this.user = user;
        this.randomUser = randomUser;
        
        // prepare dictionary, update all items appropriately
        this.allTagsDict = this.prepareAllTagsDict();
        for (Item item : user.items) {
            item.setTagsID(allTagsDict);
        }
        for (Item item : randomUser.items) {
            item.setTagsID(allTagsDict);
        }
        
        this.findPreferences();
    }
    
    public HashMap<String,Integer> prepareAllTagsDict() {
        HashMap<String, Integer> temp = new HashMap<String, Integer>();
        
        for (String key : this.user.tagDict.keySet()) {
            temp.put(key, (temp.size() + 1));
        }
        
        for (String key : this.randomUser.tagDict.keySet()) {
            if (!temp.containsKey(key)) {
                temp.put(key, (temp.size() + 1));
            }
        }
        
        return temp;
    }
    
    // takes a user's item, and compares it to random item's, then updates the user's item appropriately
    public void rankRandomItemsToComparedToUser(Item userItem, ArrayList<Item> randomItems) {
        // compare randomItems to this item
        for (Item random : randomItems) {
            random.similarityScore = Helper.similarity(userItem.getTagsID(), random.getTagsID());
            userItem.similarityDict.put(random.id, random.similarityScore);
        }
        
        // sort randomItems by similarityScore from greatest to least
        // I think this gives an xlint warning for some reason
        Comparator<Item> similarityComparator = new Comparator<Item>() {
            // compares by similarity score
            public int compare(Item a, Item b) {
                if (a.similarityScore < b.similarityScore) {
                    return 1;
                } 
                else if (a.similarityScore > b.similarityScore) {
                    return -1;
                } 
                return 0;   
            }
        };
        
        // now randomItems are in sorted order
        Collections.sort(randomItems, similarityComparator);
        
        // updated userItem's closestItems hashmap
        for (int i=0; i<randomItems.size(); i++) {
            userItem.closestItems.put(randomItems.get(i).id, (i+1));
        }
    }
    
    public void rankUserItemsComparedToRandom(Item randomItem, ArrayList<Item> userItems) {
        for (Item userItem : userItems) {
            if (userItem.closestItems.containsKey(randomItem.id)) {
                randomItem.closestItems.put(userItem.id, userItem.closestItems.get(randomItem.id));
            }
            else {
                System.out.println("UH OH COULD NOT FIND CLOSEST ITEMS");
            }
        }
    }
    
    // returns a summation of the absolute value of the correlation for a random item
    public void computePreferenceScore(Item randomItem) {
        double temp = 0;
        for (int userItemID : randomItem.closestItems.keySet()) {
            // only sum if the two items are nth nearest neighbors
            if (randomItem.closestItems.get(userItemID) <= n) {
                Item userItem = user.idDict.get(userItemID);
                //System.out.println("User: " + userItem);
                temp = temp + Math.abs(userItem.similarityDict.get(randomItem.id));
            }
        }
        randomItem.preferenceScore = temp;
    }
    
    // algorithm:
    // for each item belonging to random user,
    // find the top 20 most correlated vectors;
    // for each item in randomitem, calculate the score
    // returns a sorted array of items
    public void findPreferences() {
        // for each user's item, find closest random items
        for (Item userItem : user.getItems()) {
            rankRandomItemsToComparedToUser(userItem, randomUser.getItems());
        }
        
        // for each random item, calculate preference score
        for (Item randomItem : randomUser.getItems()) {
            rankUserItemsComparedToRandom(randomItem, user.getItems());
            computePreferenceScore(randomItem);
        }
        
        // sort randomItems by preference from greatest to least
        // I think this gives an xlint warning for some reason
        Comparator<Item> preferenceComparator = new Comparator<Item>() {
            // compares by similarity score
            public int compare(Item a, Item b) {
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
        Collections.sort(randomUser.items, preferenceComparator);
        preferences = (ArrayList<Item>)randomUser.items.clone();
        
    }
    
}