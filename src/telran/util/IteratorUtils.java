package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.shapes.Shape;


public class IteratorUtils {
	   public static  Iterator<Shape> createIterator(Shape[] shapes) {
		   return new Iterator<Shape>(){
	   
		   private int currentIndex = 0;
		   @Override
		   public boolean hasNext() {
			   return currentIndex < shapes.length;
		   }
		   @Override
		   public Shape next() {
			   if(!hasNext()) {
				   throw new NoSuchElementException();
			   }
			   return shapes[currentIndex++];
		   }

	    };
     }
}