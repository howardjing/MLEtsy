package representation;

import java.util.ArrayList;

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
    
    public User(int id) {
        this();
        this.id = id;
    }
 
    public User(ArrayList<Item> items, ArrayList<String> tags) {
        this();
        this.items = items;
        this.process(tags);
    }
    
    public void findPreferenceScore() {
        for (Item item : items) {
            preferenceScore = preferenceScore + item.getPreferenceScore();
        }
    }
    
    // ==== GETTERS AND SETTERS ====
    public double getPreferenceScore() {
        return preferenceScore;
    }
}