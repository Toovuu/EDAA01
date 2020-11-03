package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {
	
	private Map<String, Integer> words;
	
	public MultiWordCounter(String[] landskap)  //D5
	{
		//this.words = new HashMap<String, Integer>();
		this.words = new TreeMap<String, Integer>(); //D14
		for(String w : landskap)
			this.words.put(w, 0);
	}

	public void process(String w) 
	{
		if(words.containsKey(w))
			words.put(w, words.get(w) + 1);
	}

	public void report() 
	{
		for (String key : words.keySet())
			System.out.println(key + ": " + words.get(key));
		 
	}

}
