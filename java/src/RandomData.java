import java.lang.StrictMath;
import java.lang.Long;
import java.lang.Integer;

import java.util.Random;
import java.util.ArrayList;

// Generates an Item with 13 tags drawn from a gaussian distribution centered around a user inputted mean
// The standard deviation is set upon creation and defaults to 50
public class RandomData {
    
    Random random;
    int standardDeviation;
    
    public RandomData() {
        random = new Random();
        standardDeviation = 50;    
    }
    
    public RandomData(int standardDeviation) {
        this();
        this.standardDeviation = standardDeviation;
    }
    
    // given a mean (the id), returns an arraylist of 13 random integers drawn from a gaussian
    public RandomItem makeRandomItem(int mean) {
        RandomItem item = new RandomItem();
        item.setID(mean);
        item.setTags(makeTags(mean));
        return item;
    }
    
    // same thing, but for a UserItem
    public UserItem makeUserItem(int mean) {
        UserItem item = new UserItem();
        item.setID(mean);
        item.setTags(makeTags(mean));
        return item;
    }
    
    // helper method that generates an item's tags
    private ArrayList<String> makeTags(int mean) {
        
        ArrayList<String> tags = new ArrayList<String>();
        for (int i=0; i<13; i++) {
            // set up the random number
            double randomNumber = random.nextGaussian();
            randomNumber = randomNumber*standardDeviation;
            randomNumber = randomNumber + mean;
            // add it to the list
            int value = new Long(StrictMath.round(randomNumber)).intValue();
            tags.add(Integer.toString(value));
        }
        return tags;
    }
    
    public static void main(String[] args) {
        
        // set standard deviation to 10
        RandomData randomizer = new RandomData(10);
        
        // create randomItems
        ArrayList<String> globalRandomTags = new ArrayList<String>();
        ArrayList<RandomItem> randomItemsList = new ArrayList<RandomItem>(); 
        for (int i=0; i<100; i++) {
            RandomItem tempItem = randomizer.makeRandomItem(i);
            randomItemsList.add(tempItem);
            
            // record in the global tags list
            globalRandomTags.addAll(tempItem.getTags());
        }
        RandomItems randomItems = new RandomItems(randomItemsList, globalRandomTags);
        
        
        for (RandomItem item : randomItems.items) {
            System.out.println(item);
        }
        
        // create UserItems
        ArrayList<String> globalUserTags = new ArrayList<String>();
        ArrayList<UserItem> userItemsList = new ArrayList<UserItem>();
        for (int i=0; i<20; i++) {
            UserItem tempItem = randomizer.makeUserItem(i);
            userItemsList.add(tempItem);
            
            globalUserTags.addAll(tempItem.getTags());
        }
        User user = new User(userItemsList, globalUserTags);
        
        // Test on generated data
        Tester test = new Tester(user, randomItems);
        //System.out.println("User: " + user.toString());
        //System.out.println("RandomItems: " + randomItems.toString());
        //System.out.println("Dict: " + test.allTagsDict.toString());
        System.out.println("Testing...");
        for (RandomItem item : test.preferences) {
            System.out.println(item);
        }
        
    }
}