import java.io.*;

public class TextFormatting 
{
    public static void main(String[] args) 
    {
        try {
            FileInputStream fStream = new FileInputStream("take1.txt");
            DataInputStream inStream = new DataInputStream(fStream);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(inStream));
            
            String line;
            String output = "";
            
            while ((line = buffReader.readLine()) != null)
            {
                if (line.substring(line.length() - 1).equals(":")) {
                    output = output + "\n" + line;
                } else {
                    output = output + line + ",";
                }
                
//                output = line.substring(10) + "\n";
//                System.out.print(output);
            }
            
            System.out.print(output);

            inStream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
