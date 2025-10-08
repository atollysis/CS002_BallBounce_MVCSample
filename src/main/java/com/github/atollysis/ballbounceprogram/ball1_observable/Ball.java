/**
 * Ball is the Model component of the system. As a Model,
 * it extends the Observable class, giving it access to:
 * 		- setChanged() - sets changed field to true
 * 		- notifyObservers() - updates View
 * 		- notifyObservers(Object arg) updates View (with information)
 * 
 * STYLE NOTES
 * Since Observable/Observer is deprecated, the class is
 * formatted using older conventions, like:
 * 		- Required "this" keyword
 * 		- Constructor initialization of fields
 * 		- Magic numbers
 */
package com.github.atollysis.ballbounceprogram.ball1_observable;

import java.awt.Point;
import java.util.Observable;

@SuppressWarnings("deprecation")
class Ball extends Observable {
	
	/*
	 * FIELDS
	 */
	// Instance
	private int x, y, dX, dY, size;

	/*
	 * CONSTRUCTORS
	 */
	public Ball(int x, int y, int dX, int dY, int size){
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		this.size = size;
	}
	
	public Ball() {
		this(1, 1, 5, 5, 100);
	}

	/*
	 * GETTERS
	 */
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getSize() {
		return this.size;
	}

	/*
	 * SERVICE METHOD
	 */
	public void move(int xLimit, int yLimit) {
		// identify if already bounded, if bounded, change direction
		if (this.outOfBounds(this.x, xLimit, this.dX)) {
			dX *= -1;
		}
		if (this.outOfBounds(this.y, yLimit, this.dY)) {
			dY *= -1;
		}
		this.x += this.dX;
		this.y += this.dY;
		
		/*
		 * Set this Observable as having changed, then call
		 * update() on its (registered) observers with the new value.
		 */
		this.setChanged();
		this.notifyObservers(new Point(
				this.x,
				this.y));
	}
	
	// Helper
	private boolean outOfBounds(int pos, int upperLimit, int direction) {
		int nextPos = pos + direction;
		return nextPos + this.size >= upperLimit
			|| nextPos <= 0;
	}
	
}
