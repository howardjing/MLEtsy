import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;

// reads in the csv file, puts it in an arraylist
public class DrawError {
    
    ArrayList<Integer> result;
    
    public DrawError(String filePath) {
        result = new ArrayList<Integer>();
        process(filePath);
    }
    
    public void process(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));    

            // skip first line
            String thisLine = reader.readLine();

            while( (thisLine = reader.readLine()) != null) {
                String[] results = thisLine.split(",");
                result.add(Integer.valueOf(results[0]));
            }
            reader.close();
        }
        catch (IOException e) {
            
        }
    }
    
    public static void main(String args[]) {
        DrawError error = new DrawError("output/4fold/results12");
        System.out.println("Hello");
        for (Integer result : error.result) {
            System.out.println(result);
        }
    }
}