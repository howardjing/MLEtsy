
import java.io.*;

public class ConcatenateFiles {

//NOTE DS STORE IS GOING TO BE COUNTED SO GO MANUALLY DELETE THE FIRST LINE
	static public void main(String arg[]) throws java.io.IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(""));//output.txt, concatenated file
		File file = new File("");//Path to directory of files that you want to merge to 1
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println("Processing " + files[i].getPath() + "... ");
			BufferedReader br = new BufferedReader(new FileReader(files[i]
					.getPath()));
			String line = br.readLine();
			while (line != null) {
				pw.println(line);
				line = br.readLine();
			}
			br.close();
		}
		pw.close();
		System.out.println("All files have been concatenated.");
	}
}