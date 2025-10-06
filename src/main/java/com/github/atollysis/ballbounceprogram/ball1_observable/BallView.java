/**
 * BallView is the View component of the system:
 * 		- Communicates with Model to show data
 * 		- Only shows data, NOT the process
 * 
 * STYLE NOTES
 * Since Observable/Observer is deprecated, the class is
 * formatted using older conventions, like:
 * 		- Required "this" keyword
 * 		- Constructor initialization of fields
 * 		- Magic numbers
 */
package ball1_observable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

@SuppressWarnings("deprecation")
class BallView extends JPanel implements Observer {
	
	/*
	 * FIELDS
	 */
	private static final long serialVersionUID = 1L;
	private final int size;
	private Point position;

	/*
	 * CONSTRUCTOR
	 */
	public BallView(Ball model){
		this.size = model.getSize();
		this.position = new Point(1, 1);
		this.setBackground(Color.BLACK);
	}

	/*
	 * OVERRIDDEN METHOD
	 * Called upon repaint() to draw the ball
	 */
	public void paint(Graphics g){
		super.paint(g);
		this.drawBall(g);
	}
	
	private void drawBall(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(
				this.position.x,
				this.position.y,
				this.size,
				this.size);
	}

	/*
	 * OBSERVER METHOD
	 * Called by Observable class (if registered)
	 */
	public void update(Observable obs, Object args){
		this.position = (Point) args;
		this.repaint();
	}
	
}
