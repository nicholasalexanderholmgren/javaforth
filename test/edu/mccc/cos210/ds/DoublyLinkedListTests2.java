package edu.mccc.cos210.ds;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class DoublyLinkedListTests2 {
	@Test
	public void TenMillionRandomOperation() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		Random random = new Random();
		int count = 0;
		for (int i = 0; i < 10000000; i++) {
			int operation;
			if(count == 0) {
				operation = random.nextInt(2);
			}
			else {
				operation = random.nextInt(4);
			}
			int valueToAdd = random.nextInt();
			switch (operation) {
				case 0:
					list.addFirst(valueToAdd);
					assertTrue(list.getFirst() == valueToAdd);
					break;
				case 1:
					list.addLast(valueToAdd);
					assertTrue(list.getLast() == valueToAdd);
					break;
				case 2:
					list.removeFirst();
					break;
				case 3:
					list.removeLast();
			}
			list.getFirst();
			list.getLast();
		}
	}
	
	@Test
	public void sizingRemovingFirst() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addFirst(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(TEST_SIZE - i, testDLL.getSize());
			testDLL.removeFirst();
		}
		if (testDLL.getSize() != 0) {
			testDLL.getFirst();
			testDLL.getLast();
		}
	}
}