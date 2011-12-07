import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.HashMap;
import java.util.ArrayList;

public class RandomItems {
    
    public ArrayList<RandomItem> items;
    public HashMap<Integer, Integer> idDict;
    public HashMap<String, Integer> tagDict;

    public RandomItems() {
        items = new ArrayList<RandomItem>();
        idDict = new HashMap<Integer, Integer>();
        tagDict = new HashMap<String, Integer>();
    }
    
    public RandomItems(String filePath) {
        this();
        
        this.process(filePath);
    }
    
    public RandomItems(ArrayList<RandomItem> items, ArrayList<String> tags) {
        this();
        this.items = items;
        this.process(tags);
    }
    
    // method takes the name of a text file formated as ItemID:tag,tag,tag...
    public void process(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));    
            
            String thisLine;
            
            while((thisLine = reader.readLine()) != null) {
                // make a new item
                RandomItem tempItem = new RandomItem(thisLine);
                
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
                } else {
                    System.out.println("Repeat item: " + tempItem.id);
                }
            }
            
            reader.close();
        } catch (IOException e) {
            
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
	
    public String toString() {
        return tagDict.toString();
    }
    
    // ===== GETTERS AND SETTERS ======
    public ArrayList<RandomItem> getItems() {
        return items;
    }
    
    // parse through data, record 
    public static void main(String args[]) {
        RandomItems ourRandomItems = new RandomItems("randomItems.txt");

        System.out.println("Num Items: " + ourRandomItems.items.size());
    }
}