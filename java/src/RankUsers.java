import control.Ranker;
import representation.Users;
import representation.User;
import representation.RandomItems;

public class RankUsers {
    public static void main(String args[]) {
        Users randomUsers = new Users("data/raw/randomUsersFavoriteItems.txt");
        for (User user : randomUsers.users) {
            System.out.println(user);
        }
    }
}