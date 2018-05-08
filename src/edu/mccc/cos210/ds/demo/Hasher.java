package edu.mccc.cos210.ds.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import edu.mccc.cos210.ds.ISet;
import edu.mccc.cos210.ds.IVector;
import edu.mccc.cos210.ds.Set;
import edu.mccc.cos210.ds.Vector;

public class Hasher {
	private ISet<String> theSet = new Set<>();
	public static void main(String... args) throws IOException {
		Hasher h = new Hasher();
		h.doHash();
	}
	private void doHash() throws IOException {
		System.out.println(theSet);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				String s = br.readLine().trim();
				IVector<String> sa = split(s, " ");
				for (String t : sa) {
					if (t.isEmpty()) {
						continue;
					}
					theSet.add(t);
					System.out.println(theSet);
				}
			}
		}
	}
	private static IVector<String> split(String s, String sep) {
		IVector<String> iv = new Vector<>();
		int sepIndex = s.indexOf(sep);
		while (sepIndex != -1) {
			String t = s.substring(0, sepIndex).trim();
			if (!t.isEmpty()) {
				iv.pushBack(t);
			}
			s = s.substring(sepIndex + 1);
			sepIndex = s.indexOf(sep);
		}
		iv.pushBack(s);
		iv.shrinkToFit();
		System.out.println(iv);
		return iv;
	}
}
