package application;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import entities.Handler;
import entities.KeyInput;
import entities.Player;
import entities.Window;
import enumeration.ID;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -3520908160442010701L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean isRunning = false;
	
	private Random r;
	private Handler handler;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		// Construct
		new Window(WIDTH, HEIGHT, "Lets Build a Game", this);
		
		
		r = new Random();
		
		/*
		for (int i = 0; i < 50; i++) {
			handler.addObject(new Player(0,0, ID.Player));
		}
		*/
		
		handler.addObject(new Player(WIDTH/2-12, HEIGHT/2-32, ID.Player));
		handler.addObject(new Player(WIDTH/2+64, HEIGHT/2-32, ID.Player2));
		
	}
	
	public synchronized void start() {
		if (isRunning) return;
		
		thread = new Thread(this);
		thread.start();	
		isRunning = true;	
	}
	
	public synchronized void stop() {
		if (!isRunning) return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isRunning = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountTicks = 60.0;
		double ns = 1000000000 / amountTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		// updates the game
		handler.tick();
	}
	
	private void render() {
		// Renders the game
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		// Meat and bones of our rendering
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		bs.show();
		g.dispose();
	}
	
	public static void main(String[] args) {
		
		new Game();

	}



}