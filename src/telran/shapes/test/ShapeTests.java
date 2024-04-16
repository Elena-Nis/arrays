package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.*;

class ShapeTests {
private static final long ID11 = 11;
private static final int WIDTH1 = 2;
private static final int HEIGHT1 = 3;
private static final long ID21 = 21;
private static final int SIZE1 = 2;
private static final long ID12 = 12;
private static final int SIZE2 = 3;
private static final long ID22 = 22;
private static final int SIZE3 = 4;
Shape shape11 = new Rectangle(ID11, WIDTH1, HEIGHT1);
Shape shape21 = new Square(ID21, SIZE1);
Shape shape12 = new Square(ID12, SIZE2);
Shape shape22 = new Square(ID22, SIZE3);
Canvas canvas1;
Canvas canvas2;
Shape[] shapes1 = {shape11, shape12};
Shape[] shapes2; 
@BeforeEach
void setUp() {
	canvas1 = getCanvas(1, shapes1);
	shapes2 = new Shape[]{shape21, shape22, canvas1};
	canvas2 = getCanvas(2, shapes2);
	
}
	private Canvas getCanvas(long id, Shape[] shapes) {
	Canvas result = new Canvas(id);
	for(Shape shape: shapes) {
		result.addShape(shape);
	}
	return result;
}
	@Test
	void testRectangleSquare() {
		assertEquals(WIDTH1 * HEIGHT1, shape11.square());
	}
	@Test
	void testRectanglePerimeter() {
		assertEquals((WIDTH1 + HEIGHT1) * 2, shape11.perimeter());
	}
	@Test
	void testSquareSquare() {
		assertEquals(SIZE1 * SIZE1, shape21.square());
	}
	@Test
	void testSquarePerimeter() {
		assertEquals(SIZE1 * 4, shape21.perimeter());
	}
	@Test
	void testCanvasSquare() {
		assertEquals(shape11.square() + shape12.square(), canvas1.square());
	}
	@Test
	void testCanvasPerimeter() {
		assertEquals(shape11.perimeter() + shape12.perimeter(), canvas1.perimeter());
	}

	@Test
	void testRemoveShape() {
		
		Shape shape1 = new Rectangle (2,5,5);
		Shape shape2 = new Square (3,3) ;
		Shape shape3 = new Square (4,4) ;
		Shape[] shapes = new Shape[]{shape1, shape2, shape3};
		Canvas canvas =  getCanvas(1, shapes);
		Shape[] expectedShapes = new Shape[] {shape2, shape3};
		canvas.removeShape(2);
		int i=0;
		for(Shape shp:canvas) {
			assertEquals(expectedShapes[i++].getId(),shp.getId() );
		}
		assertEquals(expectedShapes.length,i);
	}
	
	@Test
	void testCanvasIterator1() {
		Canvas canvas = new Canvas(1);
		canvas.addShape(new Rectangle (2,5,5));
		canvas.addShape(new Square (3,3));
		canvas.addShape(new Square (4,4));
		Iterator<Shape> it=canvas.iterator();
		for(int i=0;i<3;i++) {
		assertTrue(it.hasNext());
		assertNotNull(it.next());
		}
		assertFalse(it.hasNext());
	}
	@Test
	void testCanvasIterator2() {
		Canvas canvas = new Canvas(1);
		canvas.addShape(new Rectangle (2,5,5));
		canvas.addShape(new Square (3,3));
		canvas.addShape(new Square (4,4));
		Iterator<Shape> it=canvas.iterator();
		for(int i=0;i<3;i++) {
		it.next();
		}
		assertThrows(NoSuchElementException.class,it::next);
	}
	

}
