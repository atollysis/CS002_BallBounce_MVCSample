/**
 * BallView is the View component of the system:
 * 		- Communicates with Model to show data
 * 		- Only shows data, NOT the process
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
 * 		- Overridden methods marked with \@Override.
 * 		- Calls paintComponent() instead of paint().
 */
package com.github.atollysis.ballbounceprogram.ball2_propertychange;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

class BallView extends JPanel implements PropertyChangeListener {
	
	/*
	 * FIELDS
	 */
	private static final long serialVersionUID = 1L;
	private static final String PROPERTY_POS = "position";
	// Instance
	private final int size;
	private Point position;

	/*
	 * CONSTRUCTOR
	 */
	public BallView(Ball model){
		size = model.getSize();
		position = new Point(
				model.getX(),
				model.getY());
		setBackground(Color.BLACK);
	}

	/*
	 * OVERRIDDEN DRAW METHOD
	 * Called upon repaint() to draw the ball.
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBall(g);
	}
	
	private void drawBall(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(
				position.x,
				position.y,
				size,
				size);
	}
	
	/*
	 * PROPERTY CHANGED METHOD
	 * Called by firePropertyChange().
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(PROPERTY_POS)) {
			position = (Point) evt.getNewValue();
			repaint();
		}
	}
	
}
