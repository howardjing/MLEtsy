import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

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

    // ===== MAIN METHOD SHOULD REMOVE LATER =======

    // parse through data, record 
    public static void main(String args[]) {
        User me = new User("data/userFavoriteItems.txt");
        //me.sort();
        System.out.println("User: " + me);
        System.out.println("Num Items: " + me.items.size());
        // UNCOMMENT TO PRINT OUT STUFF FOR WORDLE:
        //WordleTextGenerator wordle = new WordleTextGenerator(me);
        //wordle.printWords();
    }   
}