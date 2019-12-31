package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import application.Game;
import enumeration.ID;

public class BossBulletEnemy extends GameObject {

	private Handler handler;
	Random r = new Random();
	
	public BossBulletEnemy() {
		super();
	}

	public BossBulletEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		//if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		if (y >= Game.HEIGHT)
			handler.removeObject(this);
		
		handler.addObject(new  Trail((int)x, (int)y, ID.Trail, Color.RED, 16, 16, 0.02f, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}