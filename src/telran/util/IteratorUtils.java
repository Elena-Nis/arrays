package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.shapes.Shape;


public class IteratorUtils {
	   public static <T> Iterator<T> createIterator(T[] array) {
		   return new Iterator<T>(){
	   
		   private int currentIndex = 0;
		   @Override
		   public boolean hasNext() {
			   return currentIndex < array.length;
		   }
		   @Override
		   public T next() {
			   if(!hasNext()) {
				   throw new NoSuchElementException();
			   }
			   return array[currentIndex++];
		   }

	    };
     }
}