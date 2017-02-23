/**
 * 
 */
package de.niklasmerz.xml.searcher;

import java.io.File;

/**
 * Run XML search
 * @author niklas
 *
 */
public class XMLSearcher {

	/**
	 * Run
	 * @param args
	 */
	public static void main(String[] args) {
		String path = ".";
		if(args.length > 0) {
			path = args[0];
		}
		String namespace = "*";
		if(args.length > 1) {
			namespace = args[1];
		}
		HitList result = new HitList();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				XMLDoc doc = new XMLDoc(listOfFiles[i].getPath());
				result.addHits(doc.searchNS(namespace));
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory not parsed " + listOfFiles[i].getName());
			}
		}
		
		System.out.println("Total hits:  " + result.size());
		result.print();
		
	}

}
