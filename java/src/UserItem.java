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
            double similarity = Helper.similarity(self.getTags(), random.getTags())
        }
        // insert the item in the list
        
        
    }
}