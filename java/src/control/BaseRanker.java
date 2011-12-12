package control;

import representation.User;
import representation.RandomItems;
import representation.Item;

import java.util.ArrayList;
import java.lang.Math;

public abstract class BaseRanker {
    
    // Instance variables
    public User user;
    public User randomUser;

    // The found preferences
    public ArrayList<Item> preferences;
    
    public BaseRanker() {
        user = new User();
        randomUser = new User();
        preferences = new ArrayList<Item>();
    }
    
    public void setPreferenceScore() {
        for (Item item : user.getItems()) {
            user.preferenceScore = user.preferenceScore + item.getPreferenceScore();
        }
    }
    
    public void printPreferences() {
        for (Item sortedItem : preferences) {
            System.out.println(sortedItem);
        }
    }
    
}