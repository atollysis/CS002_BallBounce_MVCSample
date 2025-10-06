/**
 * BallController is the Controller component of the system.
 * It has these responsibilities:
 * 		- Instantiate Model + View
 * 		- Connect View to Model (adding as an observer)
 * 		- Connect user input to Model methods
 * 
 * STYLE NOTES
 * Since Observable/Observer is deprecated, the class is
 * formatted using older conventions, like:
 * 		- Required "this" keyword
 * 		- Constructor initialization of fields
 * 		- Anonymous classes for action listeners
 * 		- (NOT ADDED) outerClass = this; for anonymous classes
 * 			referencing outer class fields
 * 		- Magic numbers
 */
package ball1_observable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("deprecation")
class BallController extends JFrame {
	
	/*
	 * FIELDS
	 */
	private static final long serialVersionUID = 1L;
	// Back-end
	private Ball model;
	private BallView view;
	private Timer timer;
	// GUI
	private JButton btnStep;
	private JButton btnPlay;

	/*
	 * CONSTRUCTOR
	 */
	public BallController() {
		this.model = new Ball();
		this.view = new BallView(model);
		this.model.addObserver(view);
		
		// Anonymous class for Timer
		this.timer = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.move(view.getWidth(), view.getHeight());
			}
		});
		
		this.setupLayout();

		/*
		 * USER INTERACTIONS
		 * Uses anonymous classes
		 */
		this.btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.move(view.getWidth(), view.getHeight());
			}
		});
		
		this.btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					timer.stop();
				} else {
					timer.start();
				}
			}
		});
	}
	
	private void setupLayout() {
		this.setTitle("Ball Application");
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.btnStep = newButton("STEP");
		this.btnPlay = newButton("ANIMATE");

		JPanel pnl_btnWrapper = new JPanel();
		pnl_btnWrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		pnl_btnWrapper.add(this.btnStep);
		pnl_btnWrapper.add(this.btnPlay);
		
		JPanel pnl_southWrapper = new JPanel();
		pnl_southWrapper.setLayout(new BoxLayout(pnl_southWrapper, BoxLayout.Y_AXIS));
		pnl_southWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnl_southWrapper.add(newLabel("#1: Observable + Observer"));
		pnl_southWrapper.add(pnl_btnWrapper);
		
		this.add(view);
		this.add(pnl_southWrapper, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	// Static Helper GUI Methods
	private static JButton newButton(String txt) {
		JButton btn = new JButton(txt);
		btn.setFont(new Font("Monospaced", Font.BOLD, 16));
		return btn;
	}
	
	private static JLabel newLabel(String txt) {
		JLabel lbl = new JLabel(txt);
		lbl.setFont(new Font("Monospaced", Font.BOLD, 16));
		lbl.setAlignmentX(CENTER_ALIGNMENT);
		return lbl;
	}
	
}
