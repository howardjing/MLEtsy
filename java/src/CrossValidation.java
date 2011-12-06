import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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

	    int setSize = sampleSize / (numPartitions);//3000 divided by 10 = 300 in each partition
	    
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
    Partition[] partitions = CrossValidation.partitionData("data/training", 3000);
    try {      
      for (int i=0; i<numPartitions; i++) {
        String trainPath = "data/partitions/training" + i;
        //String testPath = "data/partitions/testing" + i;
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
    
  }
  
}