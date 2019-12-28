package entities;

import java.util.Random;

import application.Game;
import enumeration.ID;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;
	
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		if (scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			if (hud.getLevel() == 2) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			} else if (hud.getLevel() == 3) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			} else if (hud.getLevel() == 4) {
				handler.addObject(new FasterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FasterEnemy, handler));
			} else if (hud.getLevel() == 5) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
			}
		}
	}
}
