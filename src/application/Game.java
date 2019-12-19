package application;

import java.awt.Canvas;

import entities.Window;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -3520908160442010701L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	public Game() {
		new Window(WIDTH, HEIGHT, "Lets Build a Game", this);
	}
	public synchronized void start() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Game();

	}



}
