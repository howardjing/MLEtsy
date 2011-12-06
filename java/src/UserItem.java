import java.util.ArrayList

public class UserItem extends Item{
    public ArrayList<Item> closestItems;
    
    public UserItem(String data) {
        super(data);
        closestItems = new ArrayList<Item>;
    }
    
    public void findClosestItems(ArrayList<Item> randomItems) {
        // compare randomItems to this item
        for (Item random : randomItems) {
            random.similarityScore = Helper.similarity(self.getTags(), random.getTags())  
        }
        // sort randomItems by similarityScore
        // NOTE: MIGHT WANT TO CLONE RANDOMITEMS
        
        
        
    }
}