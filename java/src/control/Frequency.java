package control;

import representation.User;
import representation.RandomItems;
import representation.Item;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Frequency extends BaseRanker {


	//public ArrayList<Double> frequencies;

	public double threshold;
	
	public Frequency(){
		super();
		//frequencies = new ArrayList<Double>();
		threshold = 0;

	}
	
	public Frequency(User user, User randomUser) {	
        this();
        
        this.user = user;
        this.randomUser = randomUser;
		
		
		this.findPreferences();
		this.setPreferenceScore();
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
	
	public void findPreferences() {
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
		
		findFrequencies(user, user.items);
		Collections.sort(user.items, frequencyComparator);
		findThreshold(user);
		
		findFrequencies(user, randomUser.items);
		Collections.sort(randomUser.items, frequencyComparator);
		preferences = randomUser.items;
	}
	
}