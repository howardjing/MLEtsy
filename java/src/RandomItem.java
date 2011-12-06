import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

public class RandomItem extends Item {
    
    // a list of user's items, and how this random item ranked wrt similarity
    public HashMap<UserItem, Integer> closestItems;
    
    public RandomItem(String data) {
        super(data);
        closestItems = new HashMap<UserItem, Integer>();
    }
    
    // returns a summation of the absolute value of the correlation
    public void findPreferenceScore(int n) {
        double temp = 0;
        for (UserItem item : closestItems.keySet()) {
            if (closestItems.get(item) <= n) {
                temp = temp + Math.abs(item.getSimilarityScore());
            }
        }
        preferenceScore = temp;
    }
    
    // this method is really slow probably
    public void setClosestItems(ArrayList<UserItem> userItems) {
        for (UserItem item : userItems) {
            if (item.closestItems.indexOf(this) < 0) {
                System.out.println("UH OH COULD NOT FIND CLOSEST ITEMS");
            }
            closestItems.put(item, (item.closestItems.indexOf(this)+1));
        }
    }
    
}