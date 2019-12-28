package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import application.Game;
import enumeration.ID;

public class BasicEnemy extends GameObject {

	private Handler handler;
	
	public BasicEnemy() {
		super();
	}

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new  Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.02f, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 16, 16);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}