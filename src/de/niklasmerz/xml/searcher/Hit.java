/**
 * 
 */
package de.niklasmerz.xml.searcher;

import java.util.ArrayList;

import org.w3c.dom.Node;

/**
 * Found xml tag
 * @author niklas
 *
 */
public class Hit {

	private String tag;
	private ArrayList<String> attributes;

	public Hit(Node item) {
		this.tag = item.getNodeName();
		this.attributes = new ArrayList<String>();

		for(int i = 0; i < item.getAttributes().getLength(); i++) {
			this.attributes.add(item.getAttributes().item(i).getNodeName());
		}
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @return the attributes
	 */
	public ArrayList<String> getAttributes() {
		return attributes;
	}

	/**
	 * Slow print
	 */
	public String toString() {
		String attributesSttring = "";
		
		for(int i = 0; i < attributes.size(); i++) {
			attributesSttring += attributes.get(i) + ",";
		}
		
		return tag + ": " + attributesSttring;	
	}

	/**
	 * Add a list of attributes
	 * @param attributesToAdd
	 */
	public void addAttributes(ArrayList<String> attributesToAdd) {
		this.attributes.addAll(attributesToAdd);		
	}

}
