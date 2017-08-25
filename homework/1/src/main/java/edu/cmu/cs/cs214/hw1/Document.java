package edu.cmu.cs.cs214.hw1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 *  Document class is used to store the words read from URL, and compute the
 *  cosine similarity against another document.
 *  Designed for cmu 15-214 Principles of Software Construction.
 * @author haoxu
 *
 */
public class Document {

	/**
	 * words represent the document words.
	 */
	private Map<String, Integer> words;
	
	/**
	 * public constructor Document.
	 * @param urlString for input
	 * @throws MalformedURLException check if the URL is readable.
	 * @throws IOException check if there is IO exception
	 */
	public Document(String urlString) throws MalformedURLException, IOException {
		
		Scanner sc = null;
		
		words = new HashMap<String, Integer>();
		
		try {
			sc = new Scanner(new URL(urlString).openStream());
			//System.out.println(sc);
			while (sc.hasNext()) {
				String word = sc.next();
				//System.out.println(line);
				//String[] wordsFromText = line.split("\\W");
				//for (String word : wordsFromText) {
					//System.out.println(word);
					if (words.get(word) == null) {
						words.put(word, 1);
					} else {
						words.put(word, words.get(word) + 1);
					}
				//}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find the file");
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	/**
	 * getMap convert the stored words into Map.
	 * @return Map
	 */
	public Map<String, Integer> getMap() {
		Map<String, Integer> newWords = new HashMap<String, Integer>(words);
		return newWords;
	}
	
	/**
	 * getSimilarity method computes the cosine similarity.
	 * @param other for input
	 * @return cosine similarity
	 */
	public double getSimilarity(Map<String, Integer> other) {
		double norm1 = getNorm(words);
		//System.out.println(norm1);
		double norm2 = getNorm(other);
		//System.out.println(norm2);
		double product = dotProduct(other);
		//System.out.println(product);
		return Math.acos(product / (norm1 * norm2));
	}
	
	/**
	 * getNorm method compute the norm of a map.
	 * @param map for input
	 * @return double
	 */
	private double getNorm(Map<String, Integer> map) {
		double sum = 0;
		for (String word : map.keySet()) {
			sum += Math.pow(map.get(word),2);
		}
		return Math.sqrt(sum);
	}
	
	/**
	 * dotProduct method computes the product between two maps.
	 * @param map for input
	 * @return product
	 */
	private double dotProduct(Map<String, Integer> map) {
		
		double product = 0.0;
		for (String key : words.keySet()) {
			if (map.containsKey(key)) {
				product += words.get(key) * map.get(key);
			}
		}
		return product;
	}
	
	@Override
	/**
	 * toString returns a short string that identifies
	 * the URL represented by the document.
	 */
	public String toString() {
		return words.toString();
	}
}
