/**
 * Main is the entry point of the program.
 * 
 * STYLE NOTES
 * As the most modern equivalent to the previous examples, it keeps the
 * conventions from the PropertyChangeSupport example:
 * 		- "this" keyword only appears when necessary.
 * 		- "final" fields where applicable.
 * 		- Initialization of fields in the declaration.
 * Additionally:
 * 		- Strictly no magic numbers.
 * 
 * RUNNING NOTES
 * In Eclipse, a new Maven run configuration is needed with javafx:run
 * as the goal.
 */
package com.github.atollysis.ballbounceprogram.ball3_javafx;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	/*
	 * FIELDS
	 */
	private static final String TITLE = "Ball Application";
	private static final Dimension2D DIMS = new Dimension2D(1200, 700);

	/*
	 * MAIN
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		var controller = new BallController();
		var scene = new Scene(
				controller,
				DIMS.getWidth(),
				DIMS.getHeight());
		
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
