/**
 * 
 */
package de.niklasmerz.xml.searcher;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML document
 * 
 * @author niklas
 *
 */
public class XMLDoc {

	/**
	 * XML Document
	 */
	private Document doc;
	private String path;

	/**
	 * XML document from path
	 * 
	 * @param path
	 */
	public XMLDoc(String path) {
		this.path = path;

		try {
			doc = parseXML(path);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Parsing failed: " + path);
		}
	}

	/**
	 * Search by namespace
	 * 
	 * @param namespace
	 * @return
	 */
	public ArrayList<Hit> searchNS(String namespace) {
		ArrayList<Hit> hits = new ArrayList<Hit>();

		if (this.doc != null) {
			NodeList nodeList = doc.getElementsByTagNameNS(namespace, "*");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node item = nodeList.item(i);

				// Dont add this.* again
				if (!item.getLocalName().contains("this.")) {
					Hit hit = new Hit(item);
					hit.addAttributes(addDotThisAttributes(item));

					hits.add(hit);
				}
			}
			System.out.println(hits.size() + " Matches in " + path);
			return hits;
		}
		return null;
	}

	/**
	 * Add all attributes defined as this.*
	 * 
	 * @param item
	 */
	private ArrayList<String> addDotThisAttributes(Node item) {
		ArrayList<String> attributes = new ArrayList<String>();
		NodeList children = item.getChildNodes();

		// TODO stupid loop
		for (int i = 0; i < children.getLength(); i++) {
			String childNodeName = children.item(i).getLocalName();
			if (childNodeName != null && childNodeName.contains("this.")) {
				attributes.add(childNodeName.replace("this.", ""));
			}
		}
		return attributes;
	}

	/**
	 * Parse file to xml document
	 * 
	 * @param filePath
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private Document parseXML(String filePath)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(filePath);
		doc.getDocumentElement().normalize();
		return doc;
	}

}
