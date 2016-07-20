package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ContentLoader {

	public static ArrayList<String> readContent(String inFile) {
		// save the content
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader breader = new BufferedReader(new FileReader(inFile));
			while (breader.ready()) {
				String line = breader.readLine().trim();
				if (!line.isEmpty()) {
					lines.add(line);
				}
			}
			breader.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
		return lines;
	}
	

	public static String[] getAllTokens(String fileName) {
		String content = readContentProcessedSourceCode(fileName);
		String[] tokens = content.split("\\s+");
		return tokens;
	}

	public static String readContentSimple(String inFile) {
		// save the content
		String content = new String();
		try {
			BufferedReader breader = new BufferedReader(new FileReader(inFile));
			while (breader.ready()) {
				String line = breader.readLine().trim();
				if (!line.isEmpty()) {
					content += line + "\n";
				}
			}
			breader.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
		return content;

	}

	// loading all lines from a file using arraylist
	public static ArrayList<String> getAllLinesList(String fileName) {
		ArrayList<String> lines = new ArrayList<>();
		try {
			File f = new File(fileName);
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(f));
			while (bufferedReader.ready()) {
				String line = bufferedReader.readLine();
				lines.add(line.trim());
			}
			bufferedReader.close();
		} catch (Exception ex) {
			// handle the exception
		}
		return lines;
	}

	public static String readContentProcessedSourceCode(String inFile) {
		// save the content
		String content = new String();
		try {
			BufferedReader breader = new BufferedReader(new FileReader(inFile));
			String line = breader.readLine().trim();

			while (breader.ready()) {
				line = breader.readLine().trim();
				if (!line.isEmpty()) {
					content += line + "\n";
				}
			}
			breader.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
		return content;
	}

	public static String returnFilePath(String inFile) {
		String filePath = "";
		String base = "F:/BigDataProject/Dataset/eclipse.platform.ui/";

		try {
			BufferedReader breader = new BufferedReader(new FileReader(inFile));
			String line = breader.readLine().trim();
			filePath = line.substring(base.length());
			breader.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
		return filePath;
	}
	
	public static HashMap<String, ArrayList<String>> returnHashMapFromFileContent(String inFile){
		HashMap<String, ArrayList<String>> hm=new HashMap<String, ArrayList<String>>();
		ArrayList<String> fileContent=ContentLoader.readContent(inFile);
		for(String line:fileContent)
		{
			String [] spilter=line.split(":");
			String idPart=spilter[0];
			ArrayList <String> list=new ArrayList<String>();
			for(int i=1;i<spilter.length;i++)
			{
				list.add(spilter[i]);
			}
			hm.put(idPart, list);
		}
		ContentWriter.writeContent("/Users/user/Dropbox/WorkinginHome/SCAM/Implementation/Output/test.txt", fileContent);
		return hm;
	}
	
	public static HashMap<String, ArrayList<String>> returnHashMapFromFileContentForQuery(String inFile){
		HashMap<String, ArrayList<String>> hm=new HashMap<String, ArrayList<String>>();
		ArrayList<String> fileContent=ContentLoader.readContent(inFile);
		for(String line:fileContent)
		{
			String [] spilter=line.split(" ");
			String idPart=spilter[0];
			ArrayList <String> list=new ArrayList<String>();
			for(int i=1;i<spilter.length;i++)
			{
				list.add(spilter[i]);
			}
			hm.put(idPart, list);
		}
		return hm;
	}


}
