package edu.mccc.cos210.ds.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IMap.Entry;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.SortedList;

public class NaughtyToNiceFilter {
	private Array<Character> vowels;
	private Map<String, CorrectedWord> correctedWords = new Map<String, CorrectedWord>();
	public NaughtyToNiceFilter() {
		vowels = new Array<Character>(10);
		vowels.set(0, 'a');
		vowels.set(1, 'e');
		vowels.set(2, 'i');
		vowels.set(3, 'o');
		vowels.set(4, 'u');
		vowels.set(5, 'A');
		vowels.set(6, 'E');
		vowels.set(7, 'I');
		vowels.set(8, 'O');
		vowels.set(9, 'U');
	}
	public static void main(String... args) {
		NaughtyToNiceFilter n2nf = new NaughtyToNiceFilter();
		n2nf.expunge();
		n2nf.report();
	}
	private void expunge() {
		try (PrintWriter pw = new PrintWriter(new FileWriter("./data/moby/nice/nice.txt")); BufferedReader br = new BufferedReader(new FileReader("./data/moby/naughty/naughty.txt"))) {
			String s = "";
			while ((s = br.readLine()) != null) {
				pw.println(filterLine(s));
			}
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(-1);
		}
	}
	private void report() {
		try (PrintWriter pw = new PrintWriter(new FileWriter("./data/moby/report/report.txt"))) {
			summarize(pw);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(-1);
		}
	}
	private String filterLine(String s) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (i < s.length() - 4) {
				String nextFourLetters = s.substring(i, i + 4);
				if ((i == 0 || !isWord(s.substring(i - 1, i))) && isWord(nextFourLetters) && (i + 4 == s.length() || !isWord(s.substring(i + 4, i + 5)))) {
					StringBuilder correctedWord = new StringBuilder(4);
					for (int j = 0; j < 4; j++) {
						char current = nextFourLetters.charAt(j);
						if (contains(current)) {
							result.append('*');
							correctedWord.append('*');
						} else {
							result.append(current);
							correctedWord.append(current);
						}
					}
					CorrectedWord existingCorrectedWord = this.correctedWords.get(nextFourLetters);
					if (existingCorrectedWord == null) {
						this.correctedWords.put(nextFourLetters, new CorrectedWord(correctedWord.toString(), 1));
					} else {
						existingCorrectedWord.setCount(existingCorrectedWord.getCount() + 1);
					}
					i += 3;
					continue;
				}
			}
			result.append(s.charAt(i));
		}
		return result.toString();
	}
	private boolean isWord(String word) {
		for (char c : word.toLowerCase().toCharArray()) {
			if (c < 'a' || c > 'z') {
				return false;
			}
		}
		return true;
	}
	private boolean contains(char c) {
		for (char v : vowels) {
			if (c == v) {
				return true;
			}
		}
		return false;
	}
	private void summarize(PrintWriter pw) {
		pw.println("Report");
		SortedList<ProcessedWord> sortedList = new SortedList<ProcessedWord>();
		for (Entry<String, CorrectedWord> cw : correctedWords) {
			sortedList.add(new ProcessedWord(cw.getKey(), cw.getValue().getValue(), cw.getValue().getCount()));
		}
		for (ProcessedWord processedWord : sortedList) {
			pw.println(processedWord.getOriginalWord() + " : " + processedWord.getValue() + " : " + processedWord.getCount());
		}
	}
	private class CorrectedWord {
		private String value;
		private int count;
		private CorrectedWord(String value, int count) {
			this.value = value;
			this.count = count;
		}
		public String getValue() {
			return value;
		}
		@SuppressWarnings("unused")
		public void setValue(String value) {
			this.value = value;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
	}
	private class ProcessedWord extends CorrectedWord implements Comparable<ProcessedWord> {
		private String originalWord;
		private ProcessedWord(String originalWord, String correctedWord, int count) {
			super(correctedWord, count);
			this.setOriginalWord(originalWord);
		}
		public String getOriginalWord() {
			return originalWord;
		}
		public void setOriginalWord(String originalWord) {
			this.originalWord = originalWord;
		}
		@Override
		public int compareTo(ProcessedWord arg0) {
			return arg0.getCount() - this.getCount();
		}
	}
}
