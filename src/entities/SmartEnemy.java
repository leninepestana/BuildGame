package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import application.Game;
import enumeration.ID;

public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;
	
	public SmartEnemy() {
		super();
	}

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		// Running through the array, to see if i is the player
		// and setting the object ID.Player to i
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		
	
	}
	
	

	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
		// Difference between the enemy location and the player location
		// -8 is using the location from the middle of the player
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));
		
		
		velX = (float) ((-1.0/distance) * diffX);
		velY = (float) ((-1.0/distance) * diffY);
		
		// When the box reaches the edge of the frame, it will reverse
		// the velY direction that is going
		if (y <= 0 || y >= Game.HEIGHT - 32) 
			velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 16) 
			velX *= -1;
		
		handler.addObject(new  Trail((int)x, (int)y, ID.Trail, Color.GREEN, 16, 16, 0.02f, handler));
		//handler.addObject(new  Trail((int)x, (int)y, ID.Trail, Color.CYAN, 16, 16, 0.02f, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
