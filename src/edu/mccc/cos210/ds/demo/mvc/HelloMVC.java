package edu.mccc.cos210.ds.demo.mvc;

public class HelloMVC {
	private static final boolean PEOPLE = true;
	public static void main(String... args) {
		if (PEOPLE) {
			new Controller(new Model1(), new View());
		} else {
			new Controller(new Model2(), new View());
		}
	}
}
