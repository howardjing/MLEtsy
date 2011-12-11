package control;

import representation.User;
import representation.RandomItems;
import representation.Item;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Frequency {
	
	public User user;
    public RandomItems randomItems;

    // This assigns each tag value a unique integer
    public HashMap<String, Integer> allTagsDict;

    public int numUserItems;
    public int numTotal;

	//public ArrayList<Double> frequencies;

	public double threshold;
	
	public Frequency(){
		user = new User();
        randomItems = new RandomItems();
		//frequencies = new ArrayList<Double>();

        allTagsDict = new HashMap<String, Integer>();
	}
	
	public Frequency(User user, RandomItems randomItems) {	
        this();
        
        this.user = user;
        this.randomItems = randomItems;
        
        this.allTagsDict = this.prepareAllTagsDict();

		this.process();
    }

	public HashMap<String,Integer> prepareAllTagsDict() {
        HashMap<String, Integer> temp = new HashMap<String, Integer>();
        
        for (String key : this.user.tagDict.keySet()) {
            temp.put(key, (temp.size() + 1));
        }
        
      	for (String key : this.randomItems.tagDict.keySet()) {
            if (!temp.containsKey(key)) {
                temp.put(key, (temp.size() + 1));
            }
        }
        
        return temp;
    }

	public void findFrequencies(User user, ArrayList<Item> items) {
		double denominator = user.getSum();
		int userTagFreq = 0;
        for (Item item : items) {
			double numerator = 0;
			for (String tag : item.getTags()){
				if(user.tagDict.containsKey(tag)){
				userTagFreq = user.tagDict.get(tag);
				numerator = numerator + userTagFreq;
				}
			}
			item.similarityScore = numerator / denominator;	
			//frequencies.add(item.frequency);
        }
	}
	
	public void findThreshold(User userItem) {
		for(Item item : userItem.items){
			if(item.similarityScore < threshold){
				threshold = item.similarityScore;
			}
		}
	}
	
	public void rankItems() {
	Comparator<Item> frequencyComparator = new Comparator<Item>() {
        public int compare(Item a, Item b) {
            if (a.similarityScore < b.similarityScore) {
                return 1;
            } 
            else if (a.similarityScore > b.similarityScore) {
                return -1;
            } 
            return 0;   
        }
    };

	Collections.sort(user.items, frequencyComparator);
	Collections.sort(randomItems.items, frequencyComparator);
	}

	
	
	public void process() {
            findFrequencies(user, user.items);
			rankItems();
			findThreshold(user);
			//System.out.println("User: " + user.items);
			/*
			for(Item item : user.items){
			System.out.println(item.frequency);
			}
			*/
			//System.out.println("Threshold minimum is: " + threshold);
			//System.out.println(randomItems.items);
			findFrequencies(user, randomItems.items);
			rankItems();
			
			//System.out.println(randomItems.items);
			System.out.println("label,id");
			for (Item sortedItem : randomItems.items) {
		            System.out.println(sortedItem);
		        }
			/*
			for(Item item : randomItems.items){
			System.out.println(item.frequency);
			}
			*/
			//findFrequencies(user, );
	}
	
}