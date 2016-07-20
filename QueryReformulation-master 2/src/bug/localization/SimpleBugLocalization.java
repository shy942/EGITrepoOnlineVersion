package bug.localization;

import graph.maker.KeywordTokenGraphMaker;

import java.util.ArrayList;
import java.util.HashMap;

import config.StaticData;

import query.reformulation.BugTitleQueryReformulation2;
import utility.ContentLoader;
import utility.ContentWriter;
import utility.MiscUtility;

public class SimpleBugLocalization {

	/**
	 * @param args
	 */
	String bugTitleFile;
	String gitInfoFile;
	String srcCodeDir;
	static HashMap<String, ArrayList<String>> reformedQueries;
	
	static HashMap<String, ArrayList<String>> keywordFileMap;

	public SimpleBugLocalization(String bugTitleFile, String gitInfoFile, String srcCodeDir)
	{
		this.reformedQueries=new HashMap<>();
		
		this.bugTitleFile=bugTitleFile;
		this.gitInfoFile=gitInfoFile;
		this.srcCodeDir=srcCodeDir;
		this.keywordFileMap=new HashMap<>();
	}
	
	public static void PerformQueryProcessing()
	{
		BugTitleQueryReformulation2 obj=new BugTitleQueryReformulation2("./data//BugInfoFile.txt");
		obj.QueryReformulation();
		reformedQueries=obj.getReformedQueries();
		WriteProcessQuery(reformedQueries);
	}
	
	public static void WriteProcessQuery(HashMap<String, ArrayList<String>> reformedQueries)
	{
		//int i=0;
		ArrayList <String> list=new ArrayList<String>();
		for(String bugID:reformedQueries.keySet())
		{
			//i++;
			String content=bugID+" ";
			ArrayList <String> queryWords=reformedQueries.get(bugID);
			for(String word:queryWords)
			{
				content+= word + " ";
			}
			list.add(content);
			//if(i>3) break;
		}
		ContentWriter.writeFormulatedQueriesContent(StaticData.OUTPUTFOLDER+"/processedQuery.txt", list);
	}
	
	public static void CreateMappingDatabases(String bugTitleFile, String gitInfoFile, String srcCodeDir)
	{
		KeywordTokenGraphMaker gmaker = new KeywordTokenGraphMaker(
				bugTitleFile, gitInfoFile, srcCodeDir);
		gmaker.developBipartiteGraph();
		//gmaker.showBipartiteGraph();
		keywordFileMap=gmaker.getKeywordFileMap();
		WriiteMappingDatabase(keywordFileMap);
	}
	public static void WriiteMappingDatabase(HashMap <String, ArrayList<String>> keywordFileMap)
	{
		//int i=0;
		ArrayList <String> list=new ArrayList<String>();
		for(String keyword:keywordFileMap.keySet())
		{
			//i++;
			String content=keyword;
			ArrayList <String> fileList=keywordFileMap.get(keyword);
			for(String file:fileList)
			{
				content+= ":"+file;
			}
			list.add(content);
			//if(i>3) break;
		}
		ContentWriter.writeFormulatedQueriesContent(StaticData.OUTPUTFOLDER+"/processedMapDatabase.txt", list);
	}
	public void LocalizedBugInSimpleWay(HashMap<String, ArrayList<String>> hmReformedQueries, HashMap<String, ArrayList<String>> hmKeywordFileMap)
	{
		ArrayList<String> finResult=new ArrayList<String>();
		for(String bugIDwithText:hmReformedQueries.keySet())
		{
			ArrayList <String> listOfReformedQueryWords=hmReformedQueries.get(bugIDwithText);
			String bugID=bugIDwithText.substring(0, bugIDwithText.length()-5);
			System.out.println(bugIDwithText+" : "+bugID);
			String content=bugID;
			for(String QueryWord: listOfReformedQueryWords)
			{
				if(hmKeywordFileMap.containsKey(QueryWord))
				{
					ArrayList<String> listOfFiles=hmKeywordFileMap.get(QueryWord);
					if(listOfFiles.size()>1)
					{
					content+=":"+listOfFiles.get(0)+":"+listOfFiles.get(1);
					}
					else 
					{
						content+=":"+listOfFiles.get(0);
					}
					System.out.print(listOfFiles.get(0));
				}
			}
			if(content.contains(":"))finResult.add(content);
			System.out.println();
		}
		ContentWriter.writeFormulatedQueriesContent(StaticData.OUTPUTFOLDER+"/finalResult.txt", finResult);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String bugTitleFile = "./data/BugInfoFile.txt";
		String gitInfoFile = "./data/GitInfoFile2.txt";
		String srcCodeDir = "./data/ExampleSourceCodeFiles";
		
		SimpleBugLocalization obj=new SimpleBugLocalization(bugTitleFile, gitInfoFile, srcCodeDir);
		
		//Step 1
		//obj.PerformQueryProcessing();
		
		//Step 2
		//obj.CreateMappingDatabases(bugTitleFile, gitInfoFile, srcCodeDir);
	
		//Step 3
		HashMap<String, ArrayList<String>> hmReformedQueries= ContentLoader.returnHashMapFromFileContentForQuery(StaticData.OUTPUTFOLDER+"/processedQuery.txt");
		MiscUtility.showResult(10, hmReformedQueries);
		HashMap<String, ArrayList<String>> hmKeywordFileMap= ContentLoader.returnHashMapFromFileContent(StaticData.OUTPUTFOLDER+"/processedMapDatabase.txt");
		
		obj.LocalizedBugInSimpleWay(hmReformedQueries, hmKeywordFileMap);
	}

}
