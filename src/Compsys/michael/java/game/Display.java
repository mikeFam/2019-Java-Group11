package Compsys.michael.java.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

// Initialize Canvas and JFrame for the game
public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String TITLE;
	private int WIDTH; 
	private int HEIGHT;
	
	
	
	public Display (String title,int width, int height) {
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;
		
		// create Display on constructor calls.
		createDisplay();
	}
	
	// Initializing the frame and the canvas
	private void createDisplay() {
		frame = new JFrame(TITLE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	
	// Getters 
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
