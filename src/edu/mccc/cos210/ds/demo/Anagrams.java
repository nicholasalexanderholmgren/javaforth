package edu.mccc.cos210.ds.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.SinglyLinkedList;;

public class Anagrams {
	private ISinglyLinkedList<CodedWord> codedDictionary = new SinglyLinkedList<CodedWord>();
	public static void main(String... args) throws IOException {
		new Anagrams().doIt();
	}
	private void doIt() throws IOException {
		initDictionary();
		boolean done = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (!done) {
			String word = br.readLine();
			if (word == null || "begonex".equals(word)) {
				break;
			}
			word = cleanUp(word);
			if ("".equals(word)) {
				continue;
			}
			CodedWord codedWord = new CodedWord(word);
			for (CodedWord wordInDictionary : codedDictionary) {
				if(wordInDictionary.getCode().equals(codedWord.getCode())) {
					System.out.println(wordInDictionary.getWord());
				}
			}
		}
		br.close();
	}
	private String cleanUp(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : s.toLowerCase().toCharArray()) {
			if(c >= 'a' && c <= 'z') {
				stringBuilder.append(c);
			}
		}
		return stringBuilder.toString();
	}
	private void initDictionary() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("./data/pocket.dic"));
		String s = "";
		while ((s = br.readLine()) != null) {
			s = cleanUp(s);
			CodedWord cw = new CodedWord(s);
			codedDictionary.addFirst(cw);
			System.out.println(cw);
		}
		br.close();
	}
	class CodedWord {
		private String code;
		private String word;
		public CodedWord(String word) {
			this.word = word;
			code = encode(word);
		}
		private String encode(String word) {
			IArray<Integer> counts = new Array<>(26);
			for (int i = 0; i < counts.getSize(); i++) {
				counts.set(i, Integer.valueOf(0));
			}
			for (char c : word.toCharArray()) {
				int charIndex = c - 'a';
				counts.set(charIndex, counts.get(charIndex) + 1);
			}
			StringBuilder sb = new StringBuilder();
			for (char i = 0; i < counts.getSize(); i++) {
				for (int j = 0; j < counts.get(i); j++) {
					sb.append((char)(i + 'a'));
				}
			}
			return sb.toString();
		}
		public String getCode() {
			return code;
		}
		public String getWord() {
			return word;
		}
		@Override
		public String toString() {
			return getWord() + " : " + getCode();
		}
	}
}
