package representation;

import java.util.ArrayList;

// Users are a list of items
public class User extends Items{    

    public int id;
 
    public User() {
        super();
        id = 0;
    }

    public User(String filePath) {
        this();
        this.process(filePath);
    }
 
    public User(ArrayList<Item> items, ArrayList<String> tags) {
        this();
        this.items = items;
        this.process(tags);
    }  
}