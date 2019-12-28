package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import application.Game;
import enumeration.ID;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		//velX = 1;
		//velX = r.nextInt(5) + 1;
		//velY = r.nextInt(5);
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		// Prevents the player from going beyond the frame
		x = Game.clamp(x, 0, Game.WIDTH - 48);
		y = Game.clamp(y, 0, Game.HEIGHT - 70);
		
		handler.addObject(new  Trail(x, y, ID.Trail, Color.WHITE, 32, 32, 0.05f, handler));
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FasterEnemy || tempObject.getId() == ID.SmartEnemy) {				
				if (getBounds().intersects(tempObject.getBounds())) {
					// Collision code
					HUD.HEALTH -= 2;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	

}
