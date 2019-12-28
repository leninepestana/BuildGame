package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import application.Game;
import enumeration.ID;

public class FasterEnemy extends GameObject {

	private Handler handler;
	
	public FasterEnemy() {
		super();
	}

	public FasterEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 2;
		velY = 8;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new  Trail((int)x, (int)y, ID.Trail, Color.CYAN, 16, 16, 0.02f, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
