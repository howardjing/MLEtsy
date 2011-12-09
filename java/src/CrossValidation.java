import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.StringTokenizer;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class CrossValidation {
  public static Partition[] partitionData(String filePath, int sampleSize) { 
    int numPartitions = 10;//can change
    Partition[] partitioned = new Partition[numPartitions];
    for (int i=0; i<partitioned.length; i++) {
      partitioned[i] = new Partition();
    }
    
    try {
      
      FileReader fileReader = new FileReader(filePath);
	    BufferedReader bufferReader = new BufferedReader(fileReader);

	    int setSize = sampleSize / (numPartitions);
		System.out.println(setSize);
	    
	    int i = 0;
	    int counter = 0;
	    String thisLine;
	    while( (thisLine = bufferReader.readLine()) != null) {
	      
	      if (partitioned[i].data.equals("")) {
	        partitioned[i].data = thisLine + "\n";
        }
        else {
          partitioned[i].data = partitioned[i].data + thisLine + "\n";
        }
	      partitioned[i].length = partitioned[i].length + 1;
	      counter = counter + 1;
	      if ( (counter % setSize == 0) && (i < (numPartitions - 1)) ) {
	        i = i+1;
	      }
	      
      }
      fileReader.close();
    }
    catch (IOException e) {
      
    }
    return partitioned;
  }
  
  public static void main (String[] args) {
    int numPartitions = 10;
    Partition[] partitions = CrossValidation.partitionData("data/labeled/Randomized", 4317);
    try {      
      for (int i=0; i<numPartitions; i++) {
        String trainPath = "data/partitions/training" + i;
        BufferedWriter training = new BufferedWriter(new FileWriter(trainPath));
        //BufferedWriter testing = new BufferedWriter(new FileWriter(testPath));
        for (int j=0; j<numPartitions; j++) {
            if(j != i){
			}
			else{
			training.write(partitions[j].data);}
          }
        training.close();
        //testing.close();
      }
    }
    catch (IOException e) {
      
    }
    /*
	//NOTE: This is CV Pseudocode for just ONE runthrough (no permutations with the other 9)
	
	//From my understanding (?)
	//Using partitions 0 through 8 construct the hashmap of the user's tags and their frequencies
	//Using partition 9 as testing data (which needs to be seeded with randomItem data),
		//we run the similarity algorithm on each item in testing in comparison to the users preferences
	//Collect the resulting ranking of items for testing9 based on preference 
		//and finally determine by identifiers, which of the items are of type 1: user item or type 0: random item.
	
	for (int i=0; i< 9; i++) {
		 String trainPath = "data/partitions/training" + i;
		try {
	      FileReader fileReader = new FileReader(trainPath);
		  BufferedReader bufferReader = new BufferedReader(fileReader);
		  String thisLine;	
			while( (thisLine = bufferReader.readLine()) != null) {
			//LEARNING ALGO: if applicable apply algorithm to training data
			//aka make inventory of all the tags and frequencies with these 9 partitions
			}
		catch (IOException e) {
	    }
		}
	}
	
	//NOTE: training9 will be our testing partition for this one CV runthrough
	String testPath	= "data/partitions/training" + 9;
		FileReader fileReader = new FileReader(testPath);
		BufferedReader bufferReader = new BufferedReader(fileReader);
		String thisLine;	
			while( (thisLine = bufferReader.readLine()) != null) {
				//SIMILARITY ALGO: apply similarity and preference algorithm by comparing all items 
				//in training9 with hashmap created before.
				
				//write or store the arraylist of preferred items by rank
			}
	
	//Use colon tokenizer at the end on the arraylist/new file to determine how the itemTypes rank up
	StringTokenizer colonTokenizer = new StringTokenizer(data, ":");
        int itemType = Integer.valueOf(colonTokenizer.nextToken());
	
	*/

  }
  
}