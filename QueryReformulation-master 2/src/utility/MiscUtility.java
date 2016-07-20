package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MiscUtility {

	public static String list2Str(ArrayList<String> list) {
		String content = new String();
		for (String word : list) {
			if(word!=" ")
				{
					if(word!="\n")
					{
						content += word+ " ";
					}
					else
					{
						content += word + "";
					}
				}
		}
		return content.trim();
	}

	public static String hashMap2Str(HashMap hm, int n) {
		String content = n + ",";
		Iterator it = hm.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			i++;
			if (i > n)
				break;
			Map.Entry pair = (Map.Entry) it.next();
			String keyword = pair.getKey().toString();
			content += keyword + ",";
		}
		return content;
	}

	public static HashMap sortByValues(HashMap map) {
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		int i = 0;
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

	public static void showResult(int n, HashMap hm) {
		int i = 0;
		Iterator it = hm.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			String keyword = pair.getKey().toString();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			i++;
			if (i > n)
				break;
		}
	}
}
