package query.reformulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


import config.StaticData;
import config.StaticInfo;

import utility.ContentLoader;
import utility.ContentWriter;
import utility.CosineSimilarity2;
import utility.MiscUtility;

import adjacent.list.AdjacentListMaker;

public class BugTitleQueryReformulation2 {

	/**
	 * @param args
	 */
	String bugTitleFile;
	public AdjacentListMaker maker;
	HashMap<String, ArrayList<String>> adjacentMap;
	HashMap<String, ArrayList<String>> reformedQueries;
	
	public BugTitleQueryReformulation2(String bugTitleFile)
	{
		this.bugTitleFile=bugTitleFile;
		this.reformedQueries=new HashMap<>();
		LoadAdjacentList();
	}
	
	public void LoadAdjacentList()
	{
		this.maker = new AdjacentListMaker(bugTitleFile,true);
		maker.makeAdjacentList();
		this.adjacentMap=this.maker.adjacentMap;
		this.reformedQueries=new HashMap<>();
	}
	
	public void QueryReformulation()
	{
		ArrayList<String> titleList=ContentLoader.readContent(bugTitleFile);
		//int count=0;
		for(String line:titleList)
		{
			//count++;
			String[] spilter=line.split(" ");
			String bugID=spilter[0];
			ArrayList<String> content = new ArrayList<String>();
			for(int i=1;i<spilter.length;i++)
			{
				//System.out.print(spilter[i]+" ");
				content.add(spilter[i]);
			}
			//System.out.println();
			ArrayList<String> list=ApplyFormula(content);
			String str = MiscUtility.list2Str(list);
			System.out.println("Reformed Query for: "+bugID+" "+str);
			reformedQueries.put(bugID, list);
			//if(count > 3)break;
		}
	}
	
	public HashMap<String, ArrayList<String>> getReformedQueries() {
		return this.reformedQueries;
	}
	
	public ArrayList<String> ApplyFormula(ArrayList<String> content)
	{
		ArrayList<String> list = new ArrayList<>();
	
		for(String keyword:content)
		{
			String preKeyword="";
			double preCosineValue=0.0;
			if(adjacentMap.containsKey(keyword))
			{
				ArrayList <String> listforQueryKeyword=adjacentMap.get(keyword);
				HashMap<String, Double> relevantKeywords=new HashMap<String, Double>();
				
				for(String adjKeyword:adjacentMap.keySet())
				{
					if(adjKeyword.equalsIgnoreCase(keyword)==false)
					{
						ArrayList <String> listfromAdjList=adjacentMap.get(adjKeyword);
						double cosineVal=CosileSimilarityCalculator(listforQueryKeyword, listfromAdjList);
						if(cosineVal>preCosineValue)
						{
							preKeyword=adjKeyword;
							preCosineValue=cosineVal;
						}
					}	
				}
			}
			list.add(preKeyword);
		}
		
		
		return list;
	}
	
	public double CosileSimilarityCalculator(ArrayList<String> S1, ArrayList<String> S2)
	{
		String s1Text=MiscUtility.list2Str(S1);
		String s2Text=MiscUtility.list2Str(S2);
		double cosineVal=0.0;
		CosineSimilarity2 cs1 = new CosineSimilarity2();
		cosineVal=cs1.Cosine_Similarity_Score(s1Text, s2Text);
		return cosineVal;
	}
	
    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BugTitleQueryReformulation2 obj=new BugTitleQueryReformulation2("./data//BugInfoFile.txt");
		obj.QueryReformulation();
	}

}
