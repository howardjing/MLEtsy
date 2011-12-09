import java.util.Random; 
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class RandomizeData {
	
	public static int numLines = 0;
	public static String file = "";
	public static String[] array;
	
	
	public RandomizeData(String filepath){
		file = filepath;	
	}
	
	public int CountLines(){
		int numberOfLines = 0;
		try {
            BufferedReader reader = new BufferedReader(new FileReader(file));    
            String thisLine;	
			
            while( (thisLine = reader.readLine()) != null) {
		        numberOfLines++;
            }
            reader.close();
        }
        catch (IOException e){     
        }
		numLines = numberOfLines;
		return numberOfLines;
	}
	
	public void Randomize(){
		ArrayList<Integer> numbers = new ArrayList<Integer>();
	    
	 	for(int i = 0; i < numLines; i++)
	     {
	       numbers.add(i);
	     }
     	Collections.shuffle(numbers);
		
		String[] array = new String[numLines];
		try {
            BufferedReader reader = new BufferedReader(new FileReader(file));    
            String thisLine;	
			int j = 0;
			while((thisLine = reader.readLine()) != null){
		        array[j] = thisLine;
				j++;
			}
            reader.close();
        }
        catch (IOException e){     
        }

		try {
		String path = "data/labeled/Randomized";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		for (int i = 0; i < numLines ; i++){
			int temp = numbers.get(i);
			String tempString = array[temp];
	        writer.write(array[temp] + "\n");
            }
			writer.close();
		}
		catch (IOException e){     
        }
	}
	
	
	public static void main(String args[]) {
		RandomizeData data = new RandomizeData("data/labeled/userFavoriteItemsLabeled");
		int numberOfLines = data.CountLines();
		data.Randomize();
		
	}
	
}