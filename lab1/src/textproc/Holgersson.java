package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime(); //D13

		Set<String> stopwords = new HashSet<String>();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		scan.findWithinHorizon("\uFEFF", 1);
		
		while(scan.hasNext())
			stopwords.add(scan.next().toLowerCase());
		
		scan.close();
		
		TextProcessor r = new GeneralWordCounter(stopwords);
		
		//TextProcessor p = new SingleWordCounter("nils"); //D1-D3
		
		//D4
		List<TextProcessor> pList = new ArrayList<TextProcessor>();  //D4
		pList.add(new SingleWordCounter("nils"));
		pList.add(new SingleWordCounter("norge"));
		pList.add(new MultiWordCounter(REGIONS)); //D5
		pList.add(r); //D8

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			for(TextProcessor p : pList)
				p.process(word);
		}
		s.close();
		
		for(TextProcessor p : pList)
			p.report();
		
		long t1 = System.nanoTime();
        System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");

	}
}