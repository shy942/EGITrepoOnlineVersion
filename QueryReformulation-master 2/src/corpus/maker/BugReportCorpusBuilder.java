package corpus.maker;

import java.io.File;
import java.util.ArrayList;

import utility.ContentLoader;
import utility.ContentWriter;
import utility.MiscUtility;
import config.StaticData;

public class BugReportCorpusBuilder {

	int year;
	String bugFolder;
	String bugPPFolder;
	int noOfBugReports;
	public BugReportCorpusBuilder(int year)
	{
		this.year=year;
		this.bugFolder=StaticData.BUGDIR+"/new/"+year;
		this.bugPPFolder=StaticData.PROCESSEDBUGREPORTS+"/new/"+year;
		this.noOfBugReports=noOfBugReports;
	}
	protected void createPreprocessedRepo()
	{
		File[] files=new File(bugFolder).listFiles();
		//String allInOne="";
		ArrayList <String> list=new ArrayList<String>();
		noOfBugReports=files.length;
		for(File f:files){
			String fileName=f.getName();
			String content=ContentLoader.readContentSimple(f.getAbsolutePath());
			BugReportPreprocessor bpp=new BugReportPreprocessor(content);
			String preprocessed=bpp.performNLP();
			//preprocessed=preprocessed.replace(",", " ");
			preprocessed=preprocessed.trim();
			String outFile=this.bugPPFolder+"/"+fileName;
			ContentWriter.writeContent(outFile, preprocessed);
			//allInOne+=allInOne+preprocessed+"\n";
			System.out.println("Preprocessed:"+fileName);
			list.add(preprocessed);
		}
		String outFile=StaticData.PROCESSEDBUGREPORTS+"/new/AllinOne/"+"input"+year+".txt";
		ContentWriter.writeContent(outFile, list);
	}
	
	protected int getNoOFSourceCodes()
	{
		return noOfBugReports;
	}
	
	protected static void PutAll2gether()
	{
		String inFile=StaticData.PROCESSEDBUGREPORTS+"/new/AllinOne/";
		String outFile=StaticData.PROCESSEDBUGREPORTS+"/new/"+"inputAll.txt";
		File[] files=new File(inFile).listFiles();
		String content="";
		ArrayList<String> list=new ArrayList<String>();
		for(File f:files)
		{
			String contentFromEachFile=ContentLoader.readContentSimple(f.getAbsolutePath());
			//content+=contentFromEachFile+"\n";
			list.add(contentFromEachFile);
			System.out.println(f.getAbsolutePath());
		}
		//String conetntStr=MiscUtility.list2Str(list);
		ContentWriter.appendContent(outFile, list);
	}
	
	protected static void IndividualYearProcessing()
	{
		int year=2015;
		new BugReportCorpusBuilder(year).createPreprocessedRepo();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//IndividualYearProcessing();
		PutAll2gether();
	}

}
