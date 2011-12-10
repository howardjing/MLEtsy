package representation;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

// Items are a list of items
public abstract class Items {    

    // the collection of items
    public ArrayList<Item> items;
    // keeps track of the count of every tag
    public HashMap<String, Integer> tagDict;
    // makes sure there are no duplicates
    public HashMap<Integer, Item> idDict;
    
    public Items() {
        items = new ArrayList<Item>();
        tagDict = new HashMap<String, Integer>();
        idDict = new HashMap<Integer, Item>();
    }

    // method takes the name of a text file formated as Label: ItemID:tag,tag,tag...
    public void process(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));    

            // read in every line
            String thisLine;

            while( (thisLine = reader.readLine()) != null) {
                // make a new item
                Item tempItem = new Item(thisLine);
                // put this item in the arrayList if it has not been parsed yet
                if (!idDict.containsKey(tempItem.id)) {

                    idDict.put(tempItem.id, tempItem);
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
 
    // might want to unlazify this in the future
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
    public ArrayList<Item> getItems() {
        return items;
    }
 
    public HashMap<String, Integer> getTagDict() {
        return tagDict;
    }   

}