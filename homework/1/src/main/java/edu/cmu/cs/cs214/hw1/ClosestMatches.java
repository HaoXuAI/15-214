package edu.cmu.cs.cs214.hw1;

import java.io.IOException;

public class ClosestMatches {
	/**
	 * main class.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int numOfDocs = args.length;
		double[][] simNew = new double[numOfDocs][numOfDocs];

		for (int i = 0; i < numOfDocs; i++) {
			Document doc1 = new Document(args[i]);
			for (int j = i + 1; j < numOfDocs; j++) {
				Document doc2 = new Document(args[j]);
				simNew[i][j] = doc1.getSimilarity(doc2.getMap());
			}
		}

		for (int i = numOfDocs - 1; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				simNew[i][j] = simNew[j][i];
			}
		}

		for (int i = 0; i < numOfDocs; i++) {
			double maxSim = 0;
			int index1 = 0;
			int index2 = 0;
			for (int j = 0; j < numOfDocs; j++) {
				if (maxSim < simNew[i][j]) {
					maxSim = simNew[i][j];
					index1 = i;
					index2 = j;
				}
			}
			System.out.printf("The max similarity for URL:\n%s\nis\n%s\n", args[index1], args[index2]);
			System.out.printf("Their similarity is: %s\n", maxSim);
			
		}

	}

}
