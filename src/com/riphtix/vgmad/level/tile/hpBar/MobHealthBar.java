package com.riphtix.vgmad.level.tile.hpBar;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;

public class MobHealthBar extends Entity {

	public MobHealthBar(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	public SpriteSheet getHealthBar(double health) {
		return null;
	}

	public void tick(){
	}

	public void setXY(double x, double y){
		this.x = x;
		this.y = y;
	}

	public void render(int x, int y, Screen screen){
		this.x = x;
		this.y = y;
		screen.renderMob(x, y, sprite);
	}
	
}
