import control.Ranker;
import representation.Users;
import representation.User;
import representation.RandomItems;

import java.lang.Double;
import java.util.Collections;
import java.util.Comparator;

public class RankUsers {
    public static void main(String args[]) {
        
        
        // //uncomment to test if its parsing text file correctly
        // Users randomUsers = new Users("data/splits/usersLabeled", true);
        // for (User user : randomUsers.users) {
        //     System.out.println(user);
        // }
      
        
        //double maxPreference = Double.MIN_VALUE;
        //double minPreference = Double.MAX_VALUE;
        
        // an array list of users and their overal preferencescore
        
        if (args.length == 3) {
             String userItemsPath = args[0];
             String addedItemsPath = args[1];
             String randomUsersPath = args[2];
             
             User testUser = new User(userItemsPath);
             User moreItems = new User(addedItemsPath);
             Users testRandomUsers = new Users(randomUsersPath, true);
             
             Ranker test;
             for (User user : testRandomUsers.users) {
                 // add more items to all of the random users
                 user.appendMoreItems(moreItems.items);
                 test = new Ranker(testUser, user);
             }
             
             // arrange users in order from most similar to least similar
             Comparator<User> preferenceComparator = new Comparator<User>() {
                 // compares by preference score
                 public int compare(User a, User b) {
                     if (a.getPreferenceScore() < b.getPreferenceScore()) {
                         return 1;
                     } 
                     else if (a.getPreferenceScore() > b.getPreferenceScore()) {
                         return -1;
                     } 
                     return 0;   
                 }
             };
             // sort the randomItems by randomitems's preference score
             Collections.sort(testRandomUsers.users, preferenceComparator);
             System.out.println("Most similar: ");
             System.out.println(testRandomUsers.users.get(0).getPreferenceScore());
             System.out.println("Least similar: ");
             System.out.println(testRandomUsers.users.get(testRandomUsers.users.size()).getPreferenceScore());
         }
         else {
             System.out.println("usage: java -Xmx2048m -classpath [path to classes] RankUsers [path to user's items] [path to added in items] [path to random items]");
         }
    }
}