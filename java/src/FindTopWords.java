import representation.User;
import misc.WordleTextGenerator;
// Throwaway class for finding a User's top words
public class FindTopWords {

    // parse through data, record 
    public static void main(String args[]) {
        User me = new User("src/data/labeled/Randomized");
        //System.out.println("User: " + me);
        //System.out.println("Num Items: " + me.items.size());
        me.sort();
        // UNCOMMENT TO PRINT OUT STUFF FOR WORDLE:
        // WordleTextGenerator wordle = new WordleTextGenerator(me);
        // wordle.printWords();
    }
}