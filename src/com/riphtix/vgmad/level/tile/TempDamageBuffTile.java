package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class TempDamageBuffTile extends Tile {

	private double damageMultiplier = 2.0;
	private int time = 300;

	public TempDamageBuffTile(Sprite sprite) {
		super(sprite);
	}

	public boolean hasBuff(){
		return true;
	}

	public double getBuff(){
		return damageMultiplier;
	}

	public int getTimer(){
		return time;
	}

	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
}
