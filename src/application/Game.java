package application;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import entities.BasicEnemy;
import entities.HUD;
import entities.Handler;
import entities.KeyInput;
import entities.Player;
import entities.SmartEnemy;
import entities.Spawn;
import entities.Window;
import enumeration.ID;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -3520908160442010701L;

	public static final int WIDTH = 900, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread; // The entire game runs here
	private boolean isRunning = false;
	private HUD hud;
	
	private Random r;
	private Handler handler;
	private Spawn spawner;
	
	
	public Game() {
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		// Construct
		new Window(WIDTH, HEIGHT, "Lets Build a Game", this);
		
		hud = new HUD();
		
		r = new Random();
		
		spawner = new Spawn(handler, hud);
		
		
		
		handler.addObject(new Player(WIDTH/2-12, HEIGHT/2-32, ID.Player, handler));
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		//handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
		
		
		//handler.addObject(new Player(WIDTH/2+64, HEIGHT/2-32, ID.Player2));
		//for (int i = 0; i < 20; i++)
			//handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
			
			
		//handler.addObject(new BasicEnemy(WIDTH/2-12, HEIGHT/2-32, ID.BasicEnemy));
		
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
		hud.tick();
		spawner.tick();
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
		
		hud.render(g);
		
		bs.show();
		g.dispose();
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) 
			return var = max;
		else if (var <= min) 
				return var = min;	
		else return var;	
	}
	
	public static void main(String[] args) {
		
		new Game();

	}



}