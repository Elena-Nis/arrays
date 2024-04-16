package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Canvas extends Shape implements Iterable<Shape> {
   protected Shape[] shapes=new Shape[0];

   public Canvas(long id) {
	   super(id);
	   
   }
   
   
   public void addShape(Shape shape) {
		if(Arrays.indexOf(shapes, shape) <0) shapes=Arrays.add(shapes, shape);
		else {	throw new IllegalStateException();}
	}
   
   public void removeShape(long id) {
	   shapes = Arrays.removeIf(shapes, shp -> shp.getId() == id);
   }

   @Override
   public Iterator<Shape> iterator() {
	   return new CanvasIterator();
   }
   private class CanvasIterator implements Iterator<Shape> {
	   int currentIndex = 0;
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

}
   @Override
   public int square() {
	   int result = 0;
		for(Shape shape: shapes) {
			result += shape.square();
		}
		return result;
   }

   public int perimeter() {
		int result = 0;
		for(Shape shape: shapes) {
			result += shape.perimeter();
		}
		return result;
	}


}
