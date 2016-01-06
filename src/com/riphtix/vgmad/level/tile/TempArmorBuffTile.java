package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class TempArmorBuffTile extends Tile {

	public TempArmorBuffTile(Sprite sprite) {
		super(sprite);
	}

	public boolean hasBuff(){
		return true;
	}

	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
}
