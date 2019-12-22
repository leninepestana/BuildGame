package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import application.Game;
import enumeration.ID;

public class Player extends GameObject {

	Random r = new Random();
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		//velX = 1;
		//velX = r.nextInt(5) + 1;
		//velY = r.nextInt(5);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 46);
		y = Game.clamp(y, 0, Game.HEIGHT - 68);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
		
	}

	

}
