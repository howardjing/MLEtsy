package representation;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.lang.Integer;

// a collection of users
public class Users {
    
    public ArrayList<User> users;
    
    public Users(String filePath) {
        users = new ArrayList<User>();
        this.process(filePath);
    }
    
    // method takes the name of a text file formated as 
    // USER:134234:
    // Label:ItemID:tag,tag,tag...
    // Label:ItemID:tag,tag,tag...
    // USER:34234:
    // Lbael:ItemID:tag,tag,tag,...
    public void process(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));    

            // read in every line
            String thisLine;
            User tempUser = new User();
            int counter = 1;
            while( (thisLine = reader.readLine()) != null) {
                // split by colons
                String[] tempArray = thisLine.split(":");
                
                // if first word is USER, then make a new user with id
                if (tempArray[0].equals("USER")) {
                    if (counter > 1) {
                        users.add(tempUser);
                    }
                    tempUser = new User(Integer.valueOf(tempArray[1]));
                }
                // otherwise it's a new Item
                else {
                    Item tempItem = new Item(tempArray, "unlabelled");
                    // put this item in the arrayList if it has not been parsed yet
                    if (!tempUser.idDict.containsKey(tempItem.id)) {

                        tempUser.idDict.put(tempItem.id, tempItem);
                        tempUser.items.add(tempItem);  

                        // get tag counts from the item
                        for (String tag : tempItem.getTags()) {
    						tempUser.tagSum++;
                            int count = 1;
                            if (tempUser.tagDict.containsKey(tag)) {
                                count = count + tempUser.tagDict.get(tag);
                            }
                            tempUser.tagDict.put(tag, count);
                        }
                    }
                    else {
                        //System.out.println("Repeat item: " + tempItem.id);
                    }
                }

                counter++;
            }
            reader.close();
        }

        catch (IOException e) {
            
        }

    }
    
}