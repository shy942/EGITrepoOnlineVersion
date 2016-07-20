package file.token.mapping;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import utility.ContentLoader;

public class FileTokenMapper {

	String codeDir;
	HashMap<String, ArrayList<String>> fileTokenMap;
	HashMap<String, ArrayList<String>> tokenFileMap;

	public FileTokenMapper(String codeDir) {
		this.codeDir = codeDir;
		this.fileTokenMap = new HashMap<>();
		this.tokenFileMap = new HashMap<>();
	}

	public HashMap<String, ArrayList<String>> mappFile2Tokens() {
		// map tokens to code files
		File dir = new File(this.codeDir);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				String[] srcTokens = ContentLoader.getAllTokens(file
						.getAbsolutePath());
				ArrayList<String> tempList = new ArrayList<>(
						Arrays.asList(srcTokens));
				String key = file.getName();
				this.fileTokenMap.put(key, tempList);

				// now create token 2 File mapping
				for (String token : tempList) {
					if (this.tokenFileMap.containsKey(token)) {
						ArrayList<String> fileNames = this.tokenFileMap
								.get(token);
						fileNames.add(key);
						this.tokenFileMap.put(token, fileNames);
					} else {
						ArrayList<String> fileNames = new ArrayList<>();
						fileNames.add(key);
						this.tokenFileMap.put(token, fileNames);
					}
				}
			}
			System.out.println("File mapped to Tokens: "
					+ this.fileTokenMap.keySet().size());
			System.out.println("Token mapped to Files: "
					+ this.tokenFileMap.keySet().size());

		}
		return this.fileTokenMap;
	}

	public HashMap<String, ArrayList<String>> getTokenFileMap() {
		return this.tokenFileMap;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String srcCodeDir = "./data/ExampleSourceCodeFiles";
		new FileTokenMapper(srcCodeDir).mappFile2Tokens();
	}
}
