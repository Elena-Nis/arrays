package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import telran.util.Arrays;

class ArraysTests {
Integer[] numbers = {100, -3, 23, 4, 8, 41, 56, -7};
String[] strings = {"abc", "lmn", "123", null, "a"};
String[] stringsMin = {"abc", "lmn", "123",  "y"};
	@Test
	void indexOfTest() {
		assertEquals(1, Arrays.indexOf(strings, "lmn"));
		assertEquals(3, Arrays.indexOf(strings, null));
		assertEquals(-1, Arrays.indexOf(numbers, 150));
		assertEquals(4, Arrays.indexOf(numbers, 8));
	}
	@Test
	void minValueTest() {
		assertEquals("y", Arrays.min(stringsMin, new StringLengthComparator()));
		assertEquals("123", Arrays.min(stringsMin, new StringsComparator()));
	}
	@Test
	void bubbleSortTest() {
		Integer [] expected = {4, 8, 56, 100, 41, 23, -3, -7};
		Integer [] numbersCopy = java.util.Arrays.copyOf(numbers, numbers.length);
		Arrays.bubbleSort(numbersCopy, new EvenOddComparator());
		assertArrayEquals(expected, numbersCopy);
		
	}

	@Test
	void compatorEvenOddTest() {
		Comparator<Integer> comp = new EvenOddComparator();
		assertTrue(comp.compare(5,  2) >0 );
		assertTrue(comp.compare(2,  5) < 0);
		assertTrue(comp.compare(4,  4) == 0);
		assertTrue(comp.compare(7,  7) == 0);
		assertTrue(comp.compare(4,  8) < 0);
		assertTrue(comp.compare(8,  4) > 0);
		assertTrue(comp.compare(5,  7) > 0);
		assertTrue(comp.compare(7,  5) < 0);
	}
	
	
	
}

