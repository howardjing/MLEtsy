import control.Ranker;
import representation.User;
import representation.RandomItems;

import java.util.HashMap;

// This is going to be the driver class for ranking random items without categories
// It will take as input the path to the user's items and the path to the random items.
// It will print the random items in order from most relevant to least relevant
// (DOES IT IN A CSV FORMAT)
// Should redirect this output to a desired file
public class RankRandomFilteredItems {
    public static void main(String[] args) {
        
        if (args.length == 2) {
            String userItemsPath = args[0];
            String randomItemsPath = args[1];
            
            HashMap<String,String> blackList = new HashMap<String,String>();
            blackList.put("accessories", "");
            blackList.put("art", "");
            blackList.put("bags and purses", "");
            blackList.put("bath and beauty", "");
            blackList.put("books and zines", "");
            blackList.put("candles", "");
            blackList.put("ceramics and pottery", "");
            blackList.put("children", "");
            blackList.put("clothing", "");
            blackList.put("crochet", "");
            blackList.put("dolls and miniatures", "");
            blackList.put("everything else", "");
            blackList.put("furniture", "");
            blackList.put("geekery", "");
            blackList.put("glass", "");
            blackList.put("holidays", "");
            blackList.put("housewares", "");
            blackList.put("jewelry", "");
            blackList.put("knitting", "");
            blackList.put("music", "");
            blackList.put("needlecraft", "");
            blackList.put("paper goods", "");
            blackList.put("patterns", "");
            blackList.put("pets", "");
            blackList.put("plants and edibles", "");
            blackList.put("quilts", "");
            blackList.put("supplies", "");
            blackList.put("toys", "");
            blackList.put("vintage", "");
            blackList.put("weddings", "");
            blackList.put("woodworking", "");
            blackList.put("handmade", "");
            
            User testUser = new User(userItemsPath, blackList);
            User testRandomItems = new User(randomItemsPath, blackList);
            Ranker test = new Ranker(testUser, testRandomItems);
              	
            System.out.println("label,id");
            test.printPreferences();
        }
        else {
            System.out.println("usage: java -Xmx2048m -classpath [path to classes] RankRandomItems [path to user's items] [path to random items]");
        }
    }
}