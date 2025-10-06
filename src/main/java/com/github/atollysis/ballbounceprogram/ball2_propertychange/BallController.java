/**
 * BallController is the Controller component of the system.
 * It has these responsibilities:
 * 		- Instantiate Model + View
 * 		- Connect View to Model (adding as a property change listener)
 * 		- Connect user input to Model methods
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
 * 		- Lambdas for action listeners.
 */
package ball2_propertychange;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class BallController extends JFrame {
	
	/*
	 * FIELDS
	 */
	private static final long serialVersionUID = 1L;
	private static final Font FONT = new Font("Monospaced", Font.BOLD, 16);
	// Back-end
	private final Ball model	= new Ball();
	private final BallView view = new BallView(model);
	
	private final Timer timer	= new Timer(
			5, // usually 16ms = 60fps; 5ms = 200FPS (or as close as it can)
			e -> model.move(view.getWidth(), view.getHeight()));
	// GUI
	private final JButton btnStep = newButton("STEP");
	private final JButton btnPlay = newButton("ANIMATE");

	/*
	 * CONSTRUCTOR
	 */
	public BallController() {
		model.addPropertyChangeListener(view);
		
		setupLayout();

		/*
		 * USER INTERACTIONS
		 */
		btnStep.addActionListener(e -> {
			model.move(view.getWidth(), view.getHeight());
		});
		
		btnPlay.addActionListener(e -> {
			if (timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
		});
	}

	private void setupLayout() {
		setTitle("Ball Application");
		setSize(1200, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnlBtnWrapper = new JPanel();
		pnlBtnWrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		pnlBtnWrapper.add(btnStep);
		pnlBtnWrapper.add(btnPlay);
		
		JPanel southWrapper = new JPanel();
		southWrapper.setLayout(new BoxLayout(southWrapper, BoxLayout.Y_AXIS));
		southWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		southWrapper.add(newLabel("#2: PropertyChangeSupport + PropertyChangeListener"));
		southWrapper.add(pnlBtnWrapper);
		
		add(view);
		add(southWrapper, BorderLayout.SOUTH);
	}
	
	// Static Helper GUI Methods
	private static JButton newButton(String txt) {
		JButton btn = new JButton(txt);
		btn.setFont(FONT);
		return btn;
	}
	
	private static JLabel newLabel(String txt) {
		JLabel lbl = new JLabel(txt);
		lbl.setFont(FONT);
		lbl.setAlignmentX(CENTER_ALIGNMENT);
		return lbl;
	}
	
}
