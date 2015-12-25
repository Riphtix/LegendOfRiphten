package com.riphtix.vgmad.level.tile.hpBar;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.level.tile.Tile;

public class MobHealthBar extends Entity {

	public SpriteSheet sheet;

	public MobHealthBar(int x, int y, SpriteSheet sheet) {
		super(x, y, new Sprite(sheet, 20, 2));
		this.sheet = sheet;
	}

	public  SpriteSheet getHealthBar(double health) {

		System.out.println(health);

		if (health > .90 && health <= .95) return new SpriteSheet("/ui/hpBars/95Percent.png", 20, 2);
		else if (health > .85 && health <= .90) return new SpriteSheet("/ui/hpBars/90Percent.png", 20, 2);
		else if (health > .80 && health <= .85) return new SpriteSheet("/ui/hpBars/85Percent.png", 20, 2);
		else if (health > .75 && health <= .80) return new SpriteSheet("/ui/hpBars/80Percent.png", 20, 2);
		else if (health > .70 && health <= .75) return new SpriteSheet("/ui/hpBars/75Percent.png", 20, 2);
		else if (health > .65 && health <= .70) return new SpriteSheet("/ui/hpBars/70Percent.png", 20, 2);
		else if (health > .60 && health <= .65) return new SpriteSheet("/ui/hpBars/65Percent.png", 20, 2);
		else if (health > .55 && health <= .60) return new SpriteSheet("/ui/hpBars/60Percent.png", 20, 2);
		else if (health > .50 && health <= .55) return new SpriteSheet("/ui/hpBars/55Percent.png", 20, 2);
		else if (health > .45 && health <= .50) return new SpriteSheet("/ui/hpBars/50Percent.png", 20, 2);
		else if (health > .40 && health <= .45) return new SpriteSheet("/ui/hpBars/45Percent.png", 20, 2);
		else if (health > .35 && health <= .40) return new SpriteSheet("/ui/hpBars/40Percent.png", 20, 2);
		else if (health > .30 && health <= .35) return new SpriteSheet("/ui/hpBars/35Percent.png", 20, 2);
		else if (health > .25 && health <= .30) return new SpriteSheet("/ui/hpBars/30Percent.png", 20, 2);
		else if (health > .20 && health <= .25) return new SpriteSheet("/ui/hpBars/25Percent.png", 20, 2);
		else if (health > .15 && health <= .20) return new SpriteSheet("/ui/hpBars/20Percent.png", 20, 2);
		else if (health > .10 && health <= .15) return new SpriteSheet("/ui/hpBars/15Percent.png", 20, 2);
		else if (health > .05 && health <= .10) return new SpriteSheet("/ui/hpBars/10Percent.png", 20, 2);
		else if (health > 0.0 && health <= .05) return new SpriteSheet("/ui/hpBars/5Percent.png", 20, 2);
		else return new SpriteSheet("/ui/hpBars/100Percent.png", 20, 2);
	}

	public void tick(){
	}

	public void render(int x, int y, double health, Screen screen){
		this.x = x;
		this.y = y;
		getHealthBar(health / 100);
		screen.renderSheet(x, y, sheet, true);
	}
	
}
