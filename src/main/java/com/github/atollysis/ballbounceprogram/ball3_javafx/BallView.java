/**
 * BallView is the View component of the system:
 * 		- Communicates with Model to show data
 * 		- Only shows data, NOT the process
 * 		- In JavaFX MVC, it binds the properties between Model
 * 			and component (now called Node in JavaFX).
 * 
 * STYLE NOTES
 * As the most modern equivalent to the previous examples, it keeps the
 * conventions from the PropertyChangeSupport example:
 * 		- "this" keyword only appears when necessary.
 * 		- "final" fields where applicable.
 * 		- Initialization of fields in the declaration.
 * Additionally:
 * 		- Strictly no magic numbers.
 */
package com.github.atollysis.ballbounceprogram.ball3_javafx;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class BallView extends Pane {

	/*
	 * FIELDS
	 */
	private static final Background BACKGROUND = Background.fill(Color.BLACK);
	private static final Color COLOR_BALL = Color.RED;
	// Instance
	private final Circle circle;
	
	/*
	 * CONSTRUCTOR
	 */
	public BallView(Ball model) {
		setBackground(BACKGROUND);
		
		// Circle is center-based, so use size/radius instead of 0
		var radius = model.sizeProperty().get();
		circle = new Circle(
				radius, // initial X
				radius, // initial Y
				radius, // size
				COLOR_BALL);
		getChildren().add(circle);
		
		/*
		 * Bind/Connect the properties. For circles in JavaFX,
		 * the position is the centerX/YProperty.
		 */
		circle.centerXProperty().bind(model.xProperty());
		circle.centerYProperty().bind(model.yProperty());
		circle.radiusProperty().bind(model.sizeProperty());
	}
	
}
