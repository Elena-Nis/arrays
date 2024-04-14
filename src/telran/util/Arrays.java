package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;



public class Arrays {
	public static  <T> int indexOf(T[] array, T element) {
		int index = 0;
		while(index < array.length && !equals(array[index], element)) {
			index++;
		}
		return index == array.length ? -1 : index;
	}

	private static <T> boolean equals(T elem1, T elem2) {
		
		return elem1 == null ? elem1 == elem2 : elem1.equals(elem2);
	}
	public static <T> T min(T[] array, Comparator<T> comp) {
		T res=null;
		if (array != null && array.length > 0) {
			res = array[0];
			for(int i = 1; i < array.length; i++) {
				if (comp.compare(res, array[i]) > 0) {
					res = array[i];
				}
			}
		}
		return res;
		
	}
	
	public static <T> void bubbleSort(T[] array, Comparator<T> comp) {
		boolean isUnsorted = true;
		int length = array.length;
		while(isUnsorted) {
			length--;
			isUnsorted = false;
			for(int i = 0; i < length; i++) {
				if(comp.compare(array[i], array[i + 1]) > 0) {
					swap(array, i, i + 1);
					isUnsorted = true;
				}
			}
		}
	}
	private static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] =tmp;
	}

	//@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends Comparable<T>> void bubbleSort(T[] array) {
	//Comparable[] compArray=castToComparableArray(array);
	bubbleSort(array,(a,b)->a.compareTo(b));
    
    //A generic type T does not mean that objects of that type implement  
    //Comparable or Comparator interface. 
    //Type T has access only to methods of the Object class: 
    //equals, hashCode, toString, getClass, notifyAll, wait. 
    //Methods compare, equals (Comparator), compareTo (Comparable), test, negate (Predicate) are not available.
    //To ensure that objects have a Comparable or Comparator
    //data type, you can explicitly specify this in the method signature. 
    //This method is considered more preferable. 
    //         public static <T extends Comparable<T>> void bubbleSort(T[] array)
    //Another way is casting within a method.
	}
	
//	@SuppressWarnings("rawtypes")
//	private static <T> Comparable[] castToComparableArray(T[] array){
//	    for (T element:array) {
//		  if(!(element instanceof Comparable)) 
//			 throw new IllegalArgumentException("Array elements do not implement the interface Comparable");
//	    }
//	    Comparable[] compArray=new Comparable[array.length];
//	      for(int i=0;i<array.length; i++) {
//		  compArray[i]= (Comparable) array[i];
//		 }
//	return compArray;
//	}
	

	
	public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {
		//left index = 0
		//right index = array.length - 1
		//middle (left + right) / 2
		//left part - left index, right index = middle - 1
		//right part - left index = middle + 1, right index
		//while left <= right
		//returns exactly what the standard binarySearch does
		//if there are several equaled elements no guarantee that
		// being returned index is the one to the first occurrence
		int left = 0;
		int right = array.length - 1;
		int middle = (left + right) / 2;
		int res = -1;
		while(left <= right && (res = comp.compare(key, array[middle])) != 0) {
			if (res > 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
			middle = (left + right) / 2;
		}
		return left > right ? -(left + 1) : middle;
	}
	public static <T> T[] search(T[] array, Predicate<T> predicate) {
		//Impossible to allocate memory for generic array
		//Only Arrays.copyOf may be used
		T[] arResult = java.util.Arrays.copyOf(array, array.length);
		int index = 0;
		for(int i = 0; i < array.length; i++) {
			if(predicate.test(array[i])) {
				arResult[index++] = array[i];
			}
		}
		return java.util.Arrays.copyOf(arResult,index);
	}
	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
		//removes all elements of array matching a given predicate
		//returns new array with no elements matching a given predicate
		return search(array, predicate.negate());
	}
	public static <T> T[] add (T[] array, T element) {
		T[] result = java.util.Arrays.copyOf(array, array.length + 1);
		result[array.length] = element;
		return result;
	}
	public static <T> T[] copy(T[] array) {
		return java.util.Arrays.copyOf(array, array.length);
	}


    public static <T> T[] insert (T[] array, T element, int index) {
	   if(index<0||index>array.length||array==null||element==null) {
		throw new IndexOutOfBoundsException("Index out of range");
		}
	 
	//Array.newInstance - Static method in class java.lang.reflect.
	//Array, creates a new array of the specified type and length. 
	//But it returns type Object.
	//(T[]) - casting from type Object.
	//getClass() - returns the  type array.
	//getComponentType() - returns the type of array elements.
	//This method is used to create an array of the same type, but with new data.
	//But if you need to expand an existing array by adding an element, 
	//then it is better to use
	      T[] resArray = java.util.Arrays.copyOf(array, array.length + 1);
	      resArray[index]=element;
	      System.arraycopy(array,index,resArray,index+1,resArray.length-1-index);
	
//	   @SuppressWarnings("unchecked")
//	   T[] resArray= (T[]) Array.newInstance(array.getClass().getComponentType(), array.length+1);
//	   resArray[index]=element;
//	   System.arraycopy(array,0,resArray,0,index);
//	   System.arraycopy(array,index,resArray,index+1,resArray.length-1-index);
	   return resArray;
   }
    
    public static <T> T[] insertSorted (T[] array, T elem, Comparator<T> comp) {
    if(!isArraySorted(array,comp)) {
    	throw new IllegalArgumentException("The array is not sorted");}
    int index=binarySearch(array, elem, comp);
    if (index<0) index=-index-1;
    	 
	return insert(array, elem, index);		 
 }
    private static <T> boolean isArraySorted(T[] array, Comparator<T> comp) {
		 boolean res=true;
		 int i=1;
		 while (i<array.length && res)
			 if( comp.compare(array[i-1],array[i])>0) res=false;
		return res;
	}

	
	    	
 
}
