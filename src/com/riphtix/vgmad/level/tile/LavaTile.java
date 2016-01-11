package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.SpriteSheet;

public class LavaTile extends Tile {

	private static AnimatedSprite lava = new AnimatedSprite(SpriteSheet.lava, 16, 16, 3);

	public LavaTile() {
		super(lava.getSprite());
	}

	public void tick(){
		lava.tick();
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	public boolean isSolid() {
		return true;
	}
}
