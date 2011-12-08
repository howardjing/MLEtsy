import java.util.ArrayList;

public class RandomItems extends Items{

    public RandomItems() {
        super();
    }
    
    public RandomItems(String filePath) {
        this();
        this.process(filePath);
    }
    
    public RandomItems(ArrayList<Item> items, ArrayList<String> tags) {
        this();
        this.items = items;
        this.process(tags);
    }
    
    // parse through data, record 
    public static void main(String args[]) {
        RandomItems ourRandomItems = new RandomItems("randomItems.txt");

        System.out.println("Num Items: " + ourRandomItems.items.size());
    }
}