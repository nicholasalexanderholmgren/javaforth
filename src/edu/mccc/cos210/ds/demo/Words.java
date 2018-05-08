package edu.mccc.cos210.ds.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Words {
	private static final char[] letters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '-', '\'',};
	private static final char[] nonLetters = new char[] {'\'', '-'};
	public static void main(String... args) throws IOException {
		ArrayList<String> words = new ArrayList<String>();
		StringBuilder word = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "\\data\\77.txt"));
		try {
			String line = reader.readLine();
			while(line != null) {	
				boolean lastCharIsNotLetter = false;
				for(char c : line.toCharArray())	{
					c = Character.toLowerCase(c);
					boolean isLetter = contains(letters, c);			
					if(isLetter && !contains(nonLetters, c)) {
						word.append(c);	
						lastCharIsNotLetter = false;
					}
					else if(isLetter) {
						if(lastCharIsNotLetter) {
							if(word.length() > 0)
								word.deleteCharAt(word.length() - 1);
							submitWordToList(words, word);
						}
						else {
							lastCharIsNotLetter = true;
							word.append(c);	
						}
					}
					else {
						submitWordToList(words, word);
					}
				}
				submitWordToList(words, word);
				line = reader.readLine();
			}
			Collections.sort(words);
			FileWriter writer = new FileWriter(new File("").getAbsolutePath() + "\\data\\output.txt");
			try {
				for (String wordString : words) {
					writer.write(wordString);
					writer.write("\r\n");
				}	
			}
			finally {
				writer.close();
			}	
		}
		finally {
			reader.close();
		}
	}
	private static void submitWordToList(ArrayList<String> words, StringBuilder word) {
		if(word.length() == 0)
			return;
		String wordString = word.toString();
		word.delete(0, word.length());
		if(wordString.startsWith("'") && !wordString.contentEquals("'tis") && wordString.length() > 1)
			wordString = wordString.substring(1, wordString.length() - 1);
		if(!words.contains(wordString) && wordString.length() != 0&& !(wordString.length() == 1 && contains(nonLetters, wordString.charAt(0)))){
			words.add(wordString);
			//System.out.println("Adding word " + wordString);
		}
	}
	private static boolean contains(char[] array, char c){
		boolean result = false;
		for	(int i = 0; i < array.length; i++) {
			if(array[i] == c)
			{
				result = true;
				break;
			}
		}
		return result;
	}
}
