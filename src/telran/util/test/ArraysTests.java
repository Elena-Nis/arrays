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
		Comparator<String> compLength = (s1, s2) -> s1.length() - s2.length();
		assertEquals("y", Arrays.min(stringsMin,
				compLength));
		Comparator<String> compNative = (s1, s2) -> s1.compareTo(s2);
		assertEquals("123", Arrays.min(stringsMin, compNative));
	}
	@Test
	void bubbleSortTest() {
		Integer [] expected = {4, 8, 56, 100, 41, 23, -3, -7};
		Integer [] numbersCopy = java.util.Arrays.copyOf(numbers, numbers.length);
         /* lambda expression example */
	//	Comparator<Integer> evenOddComp = (num1, num2) ->
   //	evenOddComparator(num1, num2);
		/*************************************/
		/* method reference */
		Comparator<Integer> evenOddComp = ArraysTests::evenOddComparator;
		/*********************************************/
		Arrays.bubbleSort(numbersCopy, evenOddComp);
		assertArrayEquals(expected, numbersCopy);
		
	}
	static int evenOddComparator(Integer num1, Integer num2) {
		//first even numbers sorted in the ascending order
		//last odd numbers sorted in the descending order
		boolean isNum1Even = num1 % 2 == 0;
		boolean isNum2Even = num2 % 2 == 0;
		int res = 1;
		if (isNum1Even && isNum2Even) {
			res = Integer.compare(num1, num2);
		} else if (!isNum1Even && !isNum2Even) {
			res = Integer.compare(num2, num1);
		} else if (isNum1Even) {
			res = -1;
		}
		return res;
	}
	@Test
	void searchTest() {
		Integer[] expectedEven = {100, 4, 8,  56};
		assertArrayEquals(expectedEven, Arrays.search(numbers,
				a -> a % 2 == 0));
		Integer[] expectedNegative = {-3,-7};
		assertArrayEquals(expectedNegative, Arrays.search(numbers,
				a -> a < 0));
		
	}
	@Test
	void binarySearchTest() {
		Integer[] arraySorted1 = {10,30,45,57,78,82,95,100};
		int key1=59;
		int expectedIndex1=-4;
		int actualIndex1=Arrays.binarySearch(arraySorted1, key1,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex1,actualIndex1);
		int key11=5;
		int expectedIndex11=0;
		int actualIndex11=Arrays.binarySearch(arraySorted1, key11,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex11,actualIndex11);
		int key12=110;
		int expectedIndex12=-8;
		int actualIndex12=Arrays.binarySearch(arraySorted1, key12,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex12,actualIndex12);
		int key13=82;
		int expectedIndex13=5;
		int actualIndex13=Arrays.binarySearch(arraySorted1, key13,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex13,actualIndex13);
		int key14=30;
		int expectedIndex14=1;
		int actualIndex14=Arrays.binarySearch(arraySorted1, key14,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex14,actualIndex14);
		
		Integer[] arraySorted2 = {10};
		int key21=11;
		int expectedIndex21=-1;
		int actualIndex21=Arrays.binarySearch(arraySorted2, key21,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex21,actualIndex21);
		int key22=3;
		int expectedIndex22=0;
		int actualIndex22=Arrays.binarySearch(arraySorted2, key22,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex22,actualIndex22);
		
		String[] arraySorted3 = {"aa","ab","cd","cde","ef"};
		String key31="cd";
		int expectedIndex31=2;
		int actualIndex31=Arrays.binarySearch(arraySorted3, key31,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex31,actualIndex31);
		String key32="abbc";
		int expectedIndex32=-2;
		int actualIndex32=Arrays.binarySearch(arraySorted3, key32,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex32,actualIndex32);
		String key33="a";
		int expectedIndex33=0;
		int actualIndex33=Arrays.binarySearch(arraySorted3, key33,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex33,actualIndex33);
		String key34="ef";
		int expectedIndex34=4;
		int actualIndex34=Arrays.binarySearch(arraySorted3, key34,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex34,actualIndex34);
		String key35="h";
		int expectedIndex35=-5;
		int actualIndex35=Arrays.binarySearch(arraySorted3, key35,(a,b)-> a.compareTo(b));
		assertEquals(expectedIndex35,actualIndex35);
	}
 
	@Test
	 void removeIfTest() {
		 Integer[] numbers = {12, -7, 90, 58, 45, -5, 14};
		 Integer[] resArray1 = {12,90, 58, 45, 14};
		 Integer[] resArray2 = { -7, 45, -5};
		 assertArrayEquals(resArray1, Arrays.removeIf(numbers, a -> a<0));
		 assertArrayEquals(resArray2, Arrays.removeIf(numbers, a -> a%2==0));
	 }
	
	
}

