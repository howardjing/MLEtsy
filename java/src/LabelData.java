import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class LabelData {
	public String file = "";
	
	public LabelData(String filePath) {
		file = filePath;
    }

 	public void labelAsUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));    
            String thisLine;
			String path = "data/labeled/userFavoriteItemsLabeled";
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));		

			
            while( (thisLine = reader.readLine()) != null) {
		        writer.write("1:" + thisLine + "\n");
            }
			writer.close();
            reader.close();
        }
        catch (IOException e){
            
        }
    }

	public void labelAsRandom() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));    
            String thisLine;
			String path = "data/labeled/randomLabeled";
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));		

			
            while( (thisLine = reader.readLine()) != null) {
		        writer.write("0:" + thisLine + "\n");
            }
			writer.close();
            reader.close();
        }
        catch (IOException e){
            
        }
    }

	public static void main(String args[]) {
		LabelData userData = new LabelData("data/training"); 
		//data to be labeled as user (1)
		userData.labelAsUser();
	
		LabelData randomData = new LabelData(""); 
		//random data to be labeled (0) *(DO THIS BEFORE YOU SEED THE RANDOMS)
			randomData.labelAsRandom();
	
	}
	
}