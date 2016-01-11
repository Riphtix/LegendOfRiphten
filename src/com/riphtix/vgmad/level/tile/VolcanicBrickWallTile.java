package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class VolcanicBrickWallTile extends Tile {
	
	public VolcanicBrickWallTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	public boolean isSolid() {
		return true;
	}
}
