import control.UserRanker;
import representation.Users;
import representation.User;
import representation.RandomItems;

import java.lang.Double;

public class RankUsers {
    public static void main(String args[]) {
        
        
        /* uncomment to test if its parsing text file correctly
        Users randomUsers = new Users("data/raw/randomUsersFavoriteItems.txt");
        for (User user : randomUsers.users) {
            System.out.println(user);
        }
        */
        
        //double maxPreference = Double.MIN_VALUE;
        //double minPreference = Double.MAX_VALUE;
        
        // an array list of users and their overal preferencescore
        
        if (args.length == 2) {
            String userItemsPath = args[0];
            String randomUsersPath = args[1];
            
            User testUser = new User(userItemsPath);
            Users testRandomUsers = new Users(randomUsersPath);
            UserRanker test = new UserRanker(testUser, testRandomUsers.users.get(0));
            // 
            // System.out.println("label,id");
            // test.printPreferences();
        }
        else {
            System.out.println("usage: java -Xmx2048m -classpath [path to classes] RankRandomItems [path to user's items] [path to random items]");
        }
    }
}