package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class TempArmorBuffTile extends Tile {

	private static double armorMultiplier = 25.0;
	private static long duration = 3;

	public TempArmorBuffTile(Sprite sprite) {
		super(sprite);
	}

	public boolean hasBuff(){
		return true;
	}

	public static double getBuff(){
		return armorMultiplier;
	}

	public static long getDuration(){
		return duration;
	}

	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
}
