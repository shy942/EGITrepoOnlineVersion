package corpus.maker;

import java.io.File;
import java.util.ArrayList;

import source.visitor.CommentFilterer;
import utility.ContentLoader;
import utility.ContentWriter;
import config.StaticData;;

public class SourceCodeCorpusBuilder {

    File sourceCodeFolder;
	String sourceCodePPFolder;
	ArrayList<String> javaFilePaths;
	ArrayList<String> javaFilePathsLastName;
	int noOfFile=0;
	
	public SourceCodeCorpusBuilder()
	{
		this.sourceCodeFolder=StaticData.SOURCECODEDIR;
		this.sourceCodePPFolder=StaticData.PREPROCESSEDSOURCECODEDIR+"/"+"ProcessedFiles";
		this.javaFilePaths=new ArrayList<String>();
		this.javaFilePathsLastName=new ArrayList<String>();
		this.noOfFile=0;
		this.loadJavaFilesOnly(sourceCodeFolder);
	}
	
	protected void createPreprocessedRepo()
	{
		int file_track=0;
		for (String s : javaFilePaths)
	    {
	        String fileName=javaFilePathsLastName.get(file_track++);
	    	//Remove initial copyright comment
			CommentFilterer cf=new CommentFilterer(s,fileName);
			cf.discardClassHeaderComment();
			
			
			String content=ContentLoader.readContentSimple("./data/processed/"+fileName);
			SourceCodePreprocessor scbpp=new SourceCodePreprocessor(content);
			String preprocessed=scbpp.performNLP();
			//String outFile=this.sourceCodePPFolder+"/"+fileName;
			String outFile="./data/ExampleSourceCodeFiles/"+fileName;
			ContentWriter.writeContent(outFile, s.replace("\\", "/")+"\r\n"+preprocessed);
			System.out.println("Preprocessed:"+fileName);
		}
	}
	
	public void loadJavaFilesOnly(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				loadJavaFilesOnly(fileEntry);
			} else {
				// System.out.println(fileEntry.getAbsolutePath());
				if (fileEntry.getName().endsWith(".java")) {
					this.javaFilePaths.add(fileEntry.getAbsolutePath());
					this.javaFilePathsLastName.add(noOfFile++,
							fileEntry.getName());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SourceCodeCorpusBuilder().createPreprocessedRepo();
		//This is a simple change.
	}

}
