package token.file.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import adjacent.list.AdjacentListMaker;
import utility.ContentLoader;

public class KeywordSrcFileMapper {

	String changesetFile;
	String bugTitleFile;
	HashMap<Integer, ArrayList<String>> changeset;
	public HashMap<String, ArrayList<String>> token2SrcMap;
	public AdjacentListMaker maker;

	public KeywordSrcFileMapper(String changesetFile, String bugTitleFile) {
		// default constructor
		this.changesetFile = changesetFile;
		this.bugTitleFile = bugTitleFile;
		this.changeset = new HashMap<>();
		this.token2SrcMap = new HashMap<>();
		this.loadChangeSet();
	}

	protected void loadChangeSet() {
		// loading the change set
		ArrayList<String> lines = ContentLoader
				.getAllLinesList(this.changesetFile);
		for (int i = 0; i < lines.size();) {
			String currentLine = lines.get(i);
			String[] items = currentLine.split("\\s+");
			if (items.length == 2) {
				int bugID = Integer.parseInt(items[0].trim());
				int filecount = Integer.parseInt(items[1].trim());
				ArrayList<String> tempList = new ArrayList<>();
				for (int currIndex = i + 1; currIndex <= i + filecount; currIndex++) {
					tempList.add(lines.get(currIndex));
				}
				// now store the change set to bug
				this.changeset.put(bugID, tempList);
				// now update the counter
				i = i + filecount;
				i++;
			}
		}
		System.out.println("Changeset reloaded successfully for :"
				+ this.changeset.size());
	}

	public HashMap<String, ArrayList<String>> getKeywordAdjacencyList() {
		return this.maker.adjacentMap;
	}

	public HashMap<String, ArrayList<String>> mapKeyword2SrcFile() {
		// mapping the bug keywords to source file
		// collecting adjacency token and token-bugID mapping
		this.maker = new AdjacentListMaker(bugTitleFile, true);
		maker.makeAdjacentList();
		for (String token : maker.token2BugMap.keySet()) {
			ArrayList<Integer> bugIDs = maker.token2BugMap.get(token);
			ArrayList<String> tempList = new ArrayList<>();
			for (int bugID : bugIDs) {
				if (this.changeset.containsKey(bugID)) {
					tempList.addAll(this.changeset.get(bugID));
				}
			}
			this.token2SrcMap.put(token, tempList);
		}
		System.out.println("Created map:" + this.token2SrcMap.keySet().size());
		return this.token2SrcMap;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String bugTitleFile = "./data/BugInfoFile.txt";
		String gitInfoFile = "./data/GitInfoFile2.txt";
		KeywordSrcFileMapper mapper = new KeywordSrcFileMapper(gitInfoFile,
				bugTitleFile);
		mapper.mapKeyword2SrcFile();
	}
}
