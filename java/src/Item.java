import java.util.StringTokenizer;
import java.util.ArrayList;

// items have ids and tags
public class Item implements Comparable<Item> {
    
    public int id;
    public ArrayList<String> tags;
    public double similarityScore;
    public double preferenceScore;
    
    public Item() {
        id = 0;
        tags = new ArrayList<String>();
        similarityScore = 0;
        preferenceScore = 0;
    }
    
    public Item(String data) {
        this();
        this.process(data);
    }
    
    public void process(String data) {
        // break up categories
        StringTokenizer colonTokenizer = new StringTokenizer(data, ":");
        
        // first token is id
        id = Integer.valueOf(colonTokenizer.nextToken());
        
        // tokenize the tags
        if (colonTokenizer.hasMoreTokens()) {
            String tagsString = colonTokenizer.nextToken();
            System.out.println(tagsString);
            StringTokenizer commaTokenizer = new StringTokenizer(tagsString, ",");
            while (commaTokenizer.hasMoreTokens()) {
            
                String tag = commaTokenizer.nextToken();
                this.tags.add(tag);
        
            }
        }
    }
    
    // compares by similarity score
    public int compareTo(Item item) {
        if (this.similarityScore < item.similarityScore) {
            return -1;
        } 
        else if (this.similarityScore > item.similarityScore) {
            return 1;
        } 
        else {
            return 0;
        }
        
    }
    
    public String toString() {
        String s = id + ": " + tags.toString();
        return s;
    }
    
    // will have to change this to return a list of integers
    public ArrayList<String> getTags() {
        return tags;
    }
}