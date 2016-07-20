package performance.calculator;

import java.util.ArrayList;
import java.util.HashMap;

import config.StaticData;

import utility.ContentLoader;
import utility.ContentWriter;
import utility.MiscUtility;

public class CalculateLocalizationPerformance {

	/**
	 * @param args
	 */
	public static HashMap<String, ArrayList<String>> retTrueSetResults;
	public static HashMap<String, ArrayList<String>> retActualResultSets;
	
	
	public CalculateLocalizationPerformance(HashMap<String, ArrayList<String>> retTrueSetResults, HashMap<String, ArrayList<String>> retActualResultSets)
	{
		this.retActualResultSets=retActualResultSets;
		this.retActualResultSets=retActualResultSets;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculateLocalizationPerformance obj=new CalculateLocalizationPerformance(retTrueSetResults,retActualResultSets);		
		obj.retTrueSetResults=obj.RetrieveTrueSets(StaticData.OUTPUTFOLDER+"/trueResult2.txt");
		
		for(String key:obj.retTrueSetResults.keySet())
		{
			System.out.println(key);
		}
		obj.retActualResultSets=RetrieveFinalSets(StaticData.OUTPUTFOLDER+"/finalResult.txt");
		
		for(String key:obj.retActualResultSets.keySet())
		{
			System.out.println(key);
		}		
		int n=1;
		ComputePerformant(n);
		//retActualResultSets.clear();
		//retTrueSetResults.clear();
	}

	private static HashMap<String, ArrayList<String>> RetrieveFinalSets(
			String inFile) {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<String>> hm=new HashMap<>();
		ArrayList <String> list =new ArrayList<String>();
		list=ContentLoader.readContent(inFile);
	    for(String line: list)
	    {
	    	//System.out.println(line);
	    	String [] spilter=line.split(":");
	    	String bugID=spilter[0];
	    	ArrayList<String> fileAddress=new ArrayList<String>();
	    	if(spilter.length>1)
	    	{
	    		for(int i=1;i<spilter.length;i++)
	    		{
	    			fileAddress.add(spilter[i].toLowerCase().trim());
	    		}
	    	}
	    	hm.put(bugID, fileAddress);
	    }
		return hm;
	}

	private static void ComputePerformant(int n) {
		// TODO Auto-generated method stub
		int no_of_bug_matched=0;
		int f=0;
		double rank_i=0;
		double MRR_i=0;
		double MRR=0;
		//Iterator itRetActualResultSets = retActualResultSets.entrySet().iterator();
	    //while (itRetActualResultSets.hasNext()) {
	       // Map.Entry pair = (Map.Entry)itRetActualResultSets.next();
		for(String key:retActualResultSets.keySet()){
			System.out.println(key);
		   String bugIDfromRetActualResultSets=key;
	        //System.out.println(pair.getKey() + " = " + pair.getValue());
	        ArrayList <String> listFromActualResult= retActualResultSets.get(key);
	        if(retTrueSetResults.containsKey(bugIDfromRetActualResultSets))
	        {
	        	
	        	
	        	ArrayList <String> listFromTrueSets=retTrueSetResults.get(bugIDfromRetActualResultSets);
	        	int found_at_least1=0;
	        	//if(listFromTrueSets.size()>=10)
	        	{
	        		//System.out.println("BugID matched");
	        		no_of_bug_matched++;
	        		for(int i=0;i<1;i++)
	        		//for(int i=0;i<listFromActualResult.size();i++)
	        		{
	        			String resultedFilePath=listFromActualResult.get(i);
	        			if(found_at_least1>0)break;
	        			//System.out.println(listFromActualResult.get(i));
	        			for(int j=0;j<listFromTrueSets.size();j++)
	        			{
	        				String trueSetsFilePath=listFromTrueSets.get(j);
	        				if(resultedFilePath.equalsIgnoreCase(trueSetsFilePath)==true)
	        				{
	        					
	        					f++;
	        					found_at_least1++;
	        					System.out.println("Fuck from loop");
	        					System.out.println(bugIDfromRetActualResultSets+" "+trueSetsFilePath);
	        					rank_i=i+1;
	        					MRR_i=MRR_i+(1/rank_i);
	        					System.out.println(i+1);
	        					break;
	        				}
	        			}
	        		}
	        	}
	        }
	       // itRetActualResultSets.remove(); // avoids a ConcurrentModificationException
	    }
	    System.out.println("Fuck...."+f+" no_of_bug_matched "+no_of_bug_matched);
	    MRR=MRR_i/f;
	    System.out.println("MRR: "+MRR);
	    //IterateHashMap(retActualResultSets);
	}

	private static HashMap<String, ArrayList<String>> RetrieveTrueSets(
			String inFile) {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<String>> hm=new HashMap<>();
		ArrayList <String> list =new ArrayList<String>();
		list=ContentLoader.readContent(inFile);
	    for(String line: list)
	    {
	    	//System.out.println(line);
	    	String [] spilter=line.split(":");
	    	String bugID=spilter[0];
	    	//if(spilter.length>=2)String noOfFile=spilter[1];
	    	ArrayList<String> fileAddress=new ArrayList<String>();
	    	int t=0;
	    	if(spilter.length>2)
	    	{
	    		
	    		for(int i=2;i<spilter.length;i++)
	    		{
	    			fileAddress.add(spilter[i].toLowerCase().trim());
	    		}
	    		t++;
	    	}
	    	if(t>0)hm.put(bugID, fileAddress);
	    	
	    }
		return hm;
	}

}
