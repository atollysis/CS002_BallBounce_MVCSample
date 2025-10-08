/**
 * Ball is the Model component of the system. JavaFX uses a different
 * system for events wherein it uses properties and binds them.
 *		- All properties that will be connected uses a wrapper class
 *			for integers called IntegerProperty.
 * 		- No notification; the connection will be done in the View class
 * 			when instantiated by the Controller.
 * 		- Setters are also used to connect the window to the x/yLimit for
 * 			the move() method.
 * 
 * STYLE NOTES
 * As the most modern equivalent to the previous examples, it keeps the
 * conventions from the PropertyChangeSupport example like:
 * 		- "this" keyword only appears when necessary.
 * 		- "final" fields where applicable.
 * 		- Initialization of fields in the declaration.
 * Additionally:
 * 		- Strictly no magic numbers.
 */
package com.github.atollysis.ballbounceprogram.ball3_javafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class Ball {
	
	/*
	 * FIELDS
	 */
	// Defaults
	private static final int DEFAULT_DIR = 5;
	private static final int DEFAULT_SIZE = 50;
	private static final int DEFAULT_COORDS = DEFAULT_SIZE;
	// Instance
	private final IntegerProperty x		 = new SimpleIntegerProperty();
	private final IntegerProperty y		 = new SimpleIntegerProperty();
	private final IntegerProperty dX	 = new SimpleIntegerProperty();
	private final IntegerProperty dY	 = new SimpleIntegerProperty();
	private final IntegerProperty size	 = new SimpleIntegerProperty();
	private final IntegerProperty xLimit = new SimpleIntegerProperty();
	private final IntegerProperty yLimit = new SimpleIntegerProperty();
	
	/*
	 * CONSTRUCTORS
	 */
 	public Ball(int x, int y, int dX, int dY, int size){
		this.x.set(x);
		this.y.set(y);
		this.dX.set(dX);
		this.dY.set(dY);
		this.size.set(size);
	}
	
	public Ball() {
		this(DEFAULT_COORDS, DEFAULT_COORDS, DEFAULT_DIR, DEFAULT_DIR, DEFAULT_SIZE);
	}

	/*
	 * GETTERS
	 * Usually written in triples: get property, get/set value
	 */
	public IntegerProperty xProperty() {
		return x;
	}
	
	public IntegerProperty yProperty() {
		return y;
	}
	
	public IntegerProperty sizeProperty() {
		return size;
	}

	/*
	 * SETTERS
	 */
	public void setXLimit(int xLimit) {
		this.xLimit.set(xLimit);
	}
	
	public void setYLimit(int yLimit) {
		this.yLimit.set(yLimit);
	}
	
	/*
	 * SERVICE METHOD
	 */
	public void move() {
		// identify if already bounded, if bounded, change direction
		if (outOfBounds(x, xLimit, dX)) {
			dX.set(-dX.get());
		}
		if (outOfBounds(y, yLimit, dY)) {
			dY.set(-dY.get());
		}
		x.set(x.get() + dX.get());
		y.set(y.get() + dY.get());
//		System.out.format("Moved: %d, %d\n", x.get(), y.get()); // Debug
	}
	
	// Helper
	private boolean outOfBounds(
			IntegerProperty pos,
			IntegerProperty upperLimit,
			IntegerProperty direction) {
		/*
		 * The Circle object this will be bound to is based on
		 * its center, so the new 0 is the size/radius of the circle.
		 */
		var nextPos = pos.get() + direction.get();
		return nextPos <= size.get()
			|| nextPos + size.get() >= upperLimit.get();
	}
	
}
