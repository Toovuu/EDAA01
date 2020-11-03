package textproc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {

	private Set<String> stopwords;
	private Map<String, Integer> words;
	
	public GeneralWordCounter(Set<String> stopwords)
	{
		this.stopwords = stopwords;
		//this.words = new HashMap<String, Integer>(); //D7
		this.words = new TreeMap<String, Integer>();  //D14
	}
	
	public Set<Entry<String, Integer>> getWords()
	{
        return words.entrySet();
	}
	
	public void process(String w) 
	{
		if(words.containsKey(w))
			words.put(w, words.get(w) + 1);
		else if(!stopwords.contains(w))
			words.put(w, 1);
	}

	public void report() 
	{
		/* D7
		for(String key : words.keySet())
		{
			if(words.get(key) > 199)
				System.out.println(key + ": " + words.get(key));
		}*/
		
		//D9
        Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        wordList.sort(new WordCountComparator()); //D10
        
        Iterator<Map.Entry<String, Integer>> it = wordList.iterator();
        for(int i = 0; (i < 40) && it.hasNext(); i++)
        {
        	System.out.println(it.next());
        }
			
	}
	
	

}
