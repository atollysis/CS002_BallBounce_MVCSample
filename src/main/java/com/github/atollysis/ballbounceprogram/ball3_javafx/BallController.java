/**
 * BallController is the Controller component of the system.
 * It has these responsibilities:
 * 		- Instantiate Model + View
 * 		- Connect View to Model (plugging in Model in View constructor)
 * 		- Connect user input to Model methods
 * 
 * STYLE NOTES
 * As the most modern equivalent to the previous examples, it keeps the
 * conventions from the PropertyChangeSupport example:
 * 		- "this" keyword only appears when necessary.
 * 		- "final" fields where applicable.
 * 		- Initialization of fields in the declaration.
 * 		- Lambdas for action listeners.
 * Additionally:
 * 		- "var" where it's obvious.
 * 		- Strictly no magic numbers.
 * 
 * EXTRA NOTES
 * Fonts and colors are usually represented using CSS files by
 * convention. Additionally, more modern applications use FXML
 * for setting up the GUI.
 */
package com.github.atollysis.ballbounceprogram.ball3_javafx;

import javafx.animation.Animation.Status;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

class BallController extends BorderPane {

	/*
	 * FIELDS
	 */
	private static final Font FONT = Font.font("Monospaced", FontWeight.BOLD, 16);
	private static final Insets INSETS_BOTTOM = new Insets(20);
	private static final int BTN_SPACING = 20;
	// Back-end
	private final Ball model	= new Ball();
	private final BallView view = new BallView(model);
	// Timer is a Timeline in JavaFX
	private final Timeline timeline = new Timeline(new KeyFrame(
					Duration.millis(16), 
					e -> model.move()));
	// GUI
	private final Button btnStep = newButton("Step");
	private final Button btnPlay = newButton("Animate");
	private final Label lblEra = newLabel("#3: JavaFX Properties");
	
	/*
	 * CONSTRUCTOR
	 */
	public BallController() {
		timeline.setCycleCount(Animation.INDEFINITE);
		
		setupLayout();
		
		/*
		 * USER INTERACTIONS
		 */
		btnStep.setOnAction(e -> model.move());
		
		btnPlay.setOnAction(e -> {
			if (timeline.getStatus() == Status.RUNNING) {
				timeline.pause();
			} else {
				timeline.play();
			}
		});
		
		/*
		 * Hook up view dimension / window dimension via lambdas;
		 * resizing was automatically done in Swing using BorderLayout.
		 * NOTE: no get() since it's defined as a general Number parameter.
		 */
		view.widthProperty().addListener((obs, oldVal, newVal) -> {
			model.setXLimit(newVal.intValue());
		});
		
		view.heightProperty().addListener((obs, oldVal, newVal) -> {
			model.setYLimit(newVal.intValue());
		});
	}
	
	private void setupLayout() {
		var btnWrapper = new HBox(BTN_SPACING);
		btnWrapper.setAlignment(Pos.CENTER);
		btnWrapper.getChildren().addAll(
				btnStep,
				btnPlay);
		
		var bottomWrapper = new VBox();
		bottomWrapper.setPadding(INSETS_BOTTOM);
		bottomWrapper.setAlignment(Pos.CENTER);
		bottomWrapper.getChildren().addAll(
				lblEra,
				btnWrapper);
		
		setCenter(view);
		setBottom(bottomWrapper);
	}
	
	// Static Helper GUI Methods
	private static Button newButton(String txt) {
		var btn = new Button(txt);
		btn.setFont(FONT);
		return btn;
	}
	
	private static Label newLabel(String txt) {
		var lbl = new Label(txt);
		lbl.setFont(FONT);
		return lbl;
	}
	
}
