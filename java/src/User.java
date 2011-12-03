import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.HashMap;
import java.util.ArrayList;

// Users are a list of listings
public class User {
    
    public ArrayList<Listing> listings;
    
    public User() {
        listings = new ArrayList<Listing>();
    }
    
    public User(String filePath) {
        this();
        this.process(filePath);
    }
    
    // method takes the name of a text file formated as ListingID:tag,tag,tag...
    public void process(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));    
            // read in every line
            String thisLine;
            while( (thisLine = reader.readLine()) != null) {
                // make a new listing
                Listing tempListing = new Listing(thisLine);
                listings.add(tempListing);  
            }
            reader.close();
        }
        catch (IOException e){
            
        }
    }
    
    public String toString() {
        return listings.toString();
    }
    
    // parse through data, record 
    public static void main(String args[]) {
        User me = new User("test.txt");
        System.out.println("User: " + me);
    }
}