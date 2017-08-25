package edu.cmu.cs.cs214.hw1;

import java.io.IOException;

/**
 * Takes a list of URLs on the command line and prints the two URL whose web
 * pages have the highest cosine similarity. Prints a stack trace if any of the
 * URLs are invalid, or if an exception occurs while reading data from the URLs.
 */
public class FindClosestMatch {
	/**
	 * main class.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		int numOfDocs = args.length;
		double sim = 0.0;
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i < numOfDocs; i++) {
			Document doc1 = new Document(args[i]);
			//System.out.println(args[i]);
			//System.out.println(doc1);
			for (int j = i + 1; j < numOfDocs; j++) {
				Document doc2 = new Document(args[j]);
				double simNew = doc1.getSimilarity(doc2.getMap());
				//System.out.println(simNew);
				if (sim < simNew) {
					sim = simNew;
					index1 = i;
					index2 = j;
				}

			}
		}
		
		System.out.printf("The two URLs are:\n%s\n%s\n",args[index1], args[index2]);
		System.out.println("The similarity is: " + sim);
		
	}
}