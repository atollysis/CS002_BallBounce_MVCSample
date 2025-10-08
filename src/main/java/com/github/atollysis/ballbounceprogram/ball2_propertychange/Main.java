/**
 * Main is the entry point of the program.
 * 
 * STYLE NOTES
 * As PropertyChangeSupport is used in Swing (and probably even today),
 * more modern conventions have been used, including:
 * 		- Using SwingUtilities.invokeLater() (best practice)
 * 			* OR EventQueue.invokeLater(), which is actually
 * 				called by SwingUtilities and appears in the
 * 				default WindowBuilder template provided by
 * 				Eclipse.
 * 		- invokeLater() needs a Runnable, which usually takes
 * 			in an anonymous class in older conventions. A lambda
 * 			takes its place in modern conventions (shown here).
 */
package com.github.atollysis.ballbounceprogram.ball2_propertychange;

import javax.swing.SwingUtilities;

class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				new BallController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
