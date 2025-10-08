/**
 * Ball is the Model component of the system. As a Model, it has
 * a PropertyChangeSupport field that acts as the Observable class
 * from the previous example. In detail, the changes are:
 * 		- New PropertyChangeSupport field
 * 		- Methods for adding/removing listeners:
 * 			* addPropertyChangeListener()
 * 			* removePropertyChangeListener()
 * 		- Method: firePropertyChanged() for notifying registered observers
 * 			* Has overridden methods as well for different scenarios
 * 
 * STYLE NOTES
 * As PropertyChangeSupport is used in Swing (and probably even today),
 * more modern conventions have been used, including:
 * 		- Magic numbers avoided (unless used one time).
 * 		- "this" keyword only appears when necessary (e.g., arguments
 * 			with the same name).
 * 		- "final" fields where applicable.
 * 		- Initialization of fields in the declaration (except when it
 * 			depends on the constructor). Both constructor + declaration
 * 			initialization coexisted during Java 5-8, but more modern
 * 			conventions preferred the latter.
 */
package com.github.atollysis.ballbounceprogram.ball2_propertychange;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

class Ball {
	
	/*
	 * FIELDS
	 */
	private static final int DEFAULT_DIR = 5;
	private static final int DEFAULT_SIZE = 100;
	private static final int DEFAULT_COORDS = 1;
	private static final String PROPERTY_POS = "position";
	// Instance
	private int x, y, dX, dY;
	private final int size;
	// Observable alternative
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

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
		this(DEFAULT_COORDS, DEFAULT_COORDS, DEFAULT_DIR, DEFAULT_DIR, DEFAULT_SIZE);
	}

	/*
	 * GETTERS
	 */
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	/*
	 * PROPERTY CHANGE SUPPORT METHODS
	 * For adding/removing observer classes.
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}

	/*
	 * SERVICE METHOD
	 */
	public void move(int xLimit, int yLimit) {
		// identify if already bounded, if bounded, change direction
		if (outOfBounds(x, xLimit, dX)) {
			dX *= -1;
		}
		if (outOfBounds(y, yLimit, dY)) {
			dY *= -1;
		}
		int oldX = x;
		int oldY = y;
		x += dX;
		y += dY;
		
		/*
		 * Call propertyChange() on all registered observers.
		 * (Old value is technically not used here)
		 */
		pcs.firePropertyChange(
				PROPERTY_POS,
				new Point(oldX, oldY),
				new Point(x, y));
	}
	
	// Helper
	private boolean outOfBounds(int pos, int upperLimit, int direction) {
		int nextPos = pos + direction;
		return nextPos + size >= upperLimit
			|| nextPos <= 0;
	}
	
}
