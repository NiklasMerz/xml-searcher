/**
 * 
 */
package de.niklasmerz.xml.searcher;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author niklas
 *
 */
public class HitList {
	
	private HashMap<String, ArrayList<String>> hits;

	/**
	 * Empty HitList
	 */
	public HitList() {
		hits = new HashMap<>();
	}
	
	public void addHits(ArrayList<Hit> hitsList) {
		for(int i = 0; i < hitsList.size(); i++) {
			Hit hit = hitsList.get(i);
			
			if(hits.containsKey(hit.getTag())){
				ArrayList<String> attributes = hits.get(hit.getTag());
				ArrayList<String> newAttributes = hit.getAttributes();
				
				for (String newValue : newAttributes){
					   if (!attributes.contains(newValue))
					      attributes.add(newValue);
					}
				hits.put(hit.getTag(), attributes);				
			}else {
				hits.put(hit.getTag(), hit.getAttributes());
			}
		}
	}
	
	public int size() {
		return this.hits.size();
	}

	public void print() {
		for(String key : hits.keySet()) {
			System.out.println(key + " " + hits.get(key));
		}
	}

}
