import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class splitData {

public static void main(String args[]) {
	 int testingSplit = 3000;
	 int counter = 0;
   	 String trainPath = "data/training";
     String testPath = "data/testing";
	 String trainingOutput = "";
	 String testingOutput = "";
 	 	
	 try {
     	BufferedReader reader = new BufferedReader(new FileReader("take3.txt"));    
     	 String thisLine;
     		while((thisLine = reader.readLine()) != null && counter < testingSplit) {
				counter++;
             	//	System.out.println(thisLine);
				trainingOutput = trainingOutput + thisLine + "\n";	
        	}
			BufferedWriter training = new BufferedWriter(new FileWriter(trainPath));
			training.write(trainingOutput);
			training.flush();
        	training.close();
			trainingOn = 0;
	
			while((thisLine = reader.readLine()) != null){
				testingOutput = testingOutput + thisLine + "\n";
			}
		
		 
		BufferedWriter testing = new BufferedWriter(new FileWriter(testPath));
		testing.write(testingOutput);
		testing.flush();
    	testing.close();
	}
	
	catch (IOException e){
	}
}
}