package entities;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import application.Game;

public class Window extends Canvas {
	private int width;
	private int height;
	private String title;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7860769160945200800L;

//	public Window(int width, int height, String title) {
//		this.width = width;
//		this.height = height;
//		this.title = name;
//	}
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	
	
}
