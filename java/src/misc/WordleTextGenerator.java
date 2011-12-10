package misc;

import representation.User;
import java.util.HashMap;

public class WordleTextGenerator {
    
    // the user to print
    public User user;
    
    public WordleTextGenerator(User user) {
        this.user = user;
    }
    
    // prints out a user's tagDict's keys value times 
    public void printWords() {
        HashMap<String, Integer> dictionary = user.getTagDict();
        for (String tag : dictionary.keySet()) {
            String filtered = tag.toLowerCase();
            //String filtered = tag;
            for (int i=0; i<dictionary.get(tag); i++) {
                System.out.print(filtered + " ");
            }
            System.out.println("");
        }
    }
    
}