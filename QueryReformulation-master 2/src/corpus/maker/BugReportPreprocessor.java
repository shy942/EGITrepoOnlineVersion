package corpus.maker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import stemmer.Stemmer;
import config.StaticData;
import utility.ContentLoader;
import utility.MiscUtility;

public class BugReportPreprocessor {

	String content;
	ArrayList<String> stopwords;
	Stemmer stemmer;

	public BugReportPreprocessor(String content) {
		this.content = content;
		this.stopwords = new ArrayList<String>();
		this.loadStopWords();
		this.stemmer = new Stemmer();
	}

	protected void loadStopWords() {
		this.stopwords = ContentLoader.readContent(StaticData.STOPWORDFILE);
	}

	protected ArrayList<String> removeStopWords(ArrayList<String> words) {
		ArrayList<String> refined = new ArrayList<String>(words);
		for (String word : words) {
			if (this.stopwords.contains(word.toLowerCase())) {
				refined.remove(word);
			}
		}
		return refined;
	}

	protected ArrayList<String> splitContent(String content) {
		String[] words = content.split("\\s+|\\p{Punct}+|\\d+");
		//String[] words = content.split("\\s+|\\p{Punct}+|\\d+");
		return new ArrayList<String>(Arrays.asList(words));
	}

	protected String performStemming(String word) {
		return stemmer.stripAffixes(word);
		//return word;
	}
	
	protected ArrayList<String> splitLines(String content)
	{
		String[] contentByLine=content.split("\\r?\\n");
		return new ArrayList<String>(Arrays.asList(contentByLine));
	}

	public String performNLP() {
		// performing NLP operations
		ArrayList<String> lineOfContent=splitLines(content);
		ArrayList<String> stemmed = new ArrayList<String>();
		for (String indLine : lineOfContent) 
		{
			
		ArrayList<String> words = splitContent(indLine);
		//ArrayList<String> words = splitContent(this.content);
		ArrayList<String> refined = removeStopWords(words);
		//ArrayList<String> stemmed = new ArrayList<String>();
		int found=0;
		for (String word : refined) {
			if (!word.trim().isEmpty()) {
				String stemmedWord = performStemming(word.trim());
				if (stemmedWord.length() >= 3) {
					stemmedWord=stemmedWord.toLowerCase(Locale.ENGLISH);
					stemmedWord=stemmedWord.trim();
					stemmedWord=stemmedWord.replaceAll("“", "");
					stemmedWord=stemmedWord.replaceAll("”", "");
					if(!stemmedWord.isEmpty())
						{
							stemmed.add(stemmedWord.trim());
							found=1;
						}
					}
				}
			}
		if(found>0)stemmed.add("\n");
		}
		return MiscUtility.list2Str(stemmed);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
