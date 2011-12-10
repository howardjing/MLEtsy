package control;

import representation.User;
import representation.RandomItems;
import representation.Item;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranker {
    
    // Instance variables
    public User user;
    public RandomItems randomItems;

    // This assigns each tag value a unique integer
    public HashMap<String, Integer> allTagsDict;

    public int n; // number of nearest neighbors
    
    // The found preferences
    public ArrayList<Item> preferences;
    public int numWrong;
    public int numUserItems;
    public int numTotal;
    
    public Ranker() {
        user = new User();
        randomItems = new RandomItems();

        allTagsDict = new HashMap<String, Integer>();
        
        n = 20;
        
        preferences = new ArrayList<Item>();
        numWrong = 0;
        numUserItems = 0;
        numTotal = 0;
    }
    
    public Ranker(User user, RandomItems randomItems) {
        this();
        
        this.user = user;
        this.randomItems = randomItems;
        
        this.numTotal = randomItems.getItems().size();
        
        // prepare dictionary, update all items appropriately
        this.allTagsDict = this.prepareAllTagsDict();
        for (Item item : user.items) {
            item.setTagsID(allTagsDict);
        }
        for (Item item : randomItems.items) {
            item.setTagsID(allTagsDict);
        }
        
        this.findPreferences();
        this.findNumWrong();
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
            rankRandomItemsToComparedToUser(userItem, randomItems.getItems());
        }
        
        // for each random item, calculate preference score
        for (Item randomItem : randomItems.getItems()) {
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
        Collections.sort(randomItems.items, preferenceComparator);
        preferences = (ArrayList<Item>)randomItems.items.clone();
        
    }
    
    // finds the number of user favorites ranked in the top
    public void findNumWrong() {
        numWrong = 0;
        numUserItems = 0;
        // find the total number of user items in random items
        for (int i=0; i<numTotal; i++) {
            if (preferences.get(i).getLabel() == 1) {
                numUserItems = numUserItems + 1;
            }
        }
        
        // find the number of user items that are not ranked in the top
        for (int i=numUserItems; i<numTotal; i++) {
            if (preferences.get(i).getLabel() != 1) {
                numWrong = numWrong + 1;
            }
        }
    }
    
    // buggy
    public void printError() {
        System.out.println("Total items: " + numTotal);
        System.out.println("Total user items: " + numUserItems);
        System.out.println("User items wrong: " + numWrong);
        System.out.println("User items right: " + (numUserItems - numWrong));
        System.out.println("Percent accuracy: " + (numUserItems - numWrong)/numUserItems);
    }
    
    public void printPreferences() {
        for (Item sortedItem : preferences) {
            System.out.println(sortedItem);
        }
    }
    
}