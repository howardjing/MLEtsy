package representation;

import java.util.ArrayList;
import java.util.HashMap;

// Users are a list of items
public class User extends Items{    

    public int id;
    
    // how close this user is to other users
    public double preferenceScore;
 
    public User() {
        super();
        id = 0;
        preferenceScore = 0;
    }

    public User(String filePath) {
        this();
        this.process(filePath);
    }
    
    public User(String filePath, HashMap<String,String> blackList) {
        this();
        this.process(filePath, blackList);
    }
    
    public User(int id) {
        this();
        this.id = id;
    }
 
    public User(ArrayList<Item> items, ArrayList<String> tags) {
        this();
        this.items = items;
        this.process(tags);
    }
    
    public void appendMoreItems(ArrayList<Item> userItems) {
        items.addAll(userItems);
    }
    
    // ==== GETTERS AND SETTERS ====
    public double getPreferenceScore() {
        return preferenceScore;
    }
}