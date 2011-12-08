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

public class User {    

    public ArrayList<UserItem> items;
    public HashMap<String, Integer> tagDict;
    public HashMap<Integer, Integer> idDict;
 
    public User() {
        items = new ArrayList<UserItem>();
        tagDict = new HashMap<String, Integer>();
        idDict = new HashMap<Integer, Integer>();
    }

    public User(String filePath) {
        this();
        this.process(filePath);
    }
 
    public User(ArrayList<UserItem> items, ArrayList<String> tags) {
        this();
        this.items = items;
        this.process(tags);
    }
 
    // method takes the name of a text file formated as ItemID:tag,tag,tag...
    public void process(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));    

            // read in every line
            String thisLine;

            while( (thisLine = reader.readLine()) != null) {

                // make a new item
                UserItem tempItem = new UserItem(thisLine);
                // put this item in the arrayList if it has not been parsed yet
                if (!idDict.containsKey(tempItem.id)) {

                    idDict.put(tempItem.id, tempItem.id);
                    items.add(tempItem);  

                    // get tag counts from the item

                    for (String tag : tempItem.getTags()) {
                        int count = 1;
                        if (tagDict.containsKey(tag)) {
                            count = count + tagDict.get(tag);
                        }
                        tagDict.put(tag, count);
                    }
                }
                else {
                    System.out.println("Repeat item: " + tempItem.id);
                }
            }
            reader.close();
        }

        catch (IOException e) {
            
        }

    }
 
    public void process(ArrayList<String> tags) {
        // get a count of tags
        for (String tag : tags) {
            int count = 1;
            if (tagDict.containsKey(tag)) {
                count = count + tagDict.get(tag);
            }
            tagDict.put(tag, count);
        }
    }
 
    public void sort(){
        ArrayList myArrayList=new ArrayList(tagDict.entrySet());
        Collections.sort(myArrayList, new MyComparator());

        Iterator itr=myArrayList.iterator();
        String key="";
        int value=0;
        
        while(itr.hasNext()){
            Map.Entry e=(Map.Entry)itr.next();
            key = (String)e.getKey();
            value = ((Integer)e.getValue()).intValue();
            System.out.println(key + " = " + value); // THIS WILL PRINT EVERYTHING BEAUTIFULLY      
        }
    }
    
    public String toString() {
        return tagDict.toString();
    }
 
    // ===== GETTERS AND SETTERS ======
    public ArrayList<UserItem> getItems() {
        return items;
    }
 
    public HashMap<String, Integer> getTagDict() {
        return tagDict;
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