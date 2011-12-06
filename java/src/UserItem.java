import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserItem extends Item{
    
    public ArrayList<RandomItem> closestItems;
    
    public UserItem(String data) {
        super(data);
        closestItems = new ArrayList<RandomItem>();
    }
    
    public void findClosestItems(ArrayList<RandomItem> randomItems) {
        // compare randomItems to this item
        for (RandomItem random : randomItems) {
            random.similarityScore = Helper.similarity(this.getTagsID(), random.getTagsID());
        }
        // TDL: maybe make this its own class??
        // sort randomItems by similarityScore from greatest to least
        // I think this gives an xlint warning for some reason
        Comparator<RandomItem> similarityComparator = new Comparator<RandomItem>() {
            // compares by similarity score
            public int compare(RandomItem a, RandomItem b) {
                if (a.similarityScore < b.similarityScore) {
                    return 1;
                } 
                else if (a.similarityScore > b.similarityScore) {
                    return -1;
                } 
                return 0;   
            }
        };
        Collections.sort(randomItems, similarityComparator);
        // NOTE: SHALLOW CLONE
        closestItems = (ArrayList<RandomItem>)randomItems.clone();
    }
}