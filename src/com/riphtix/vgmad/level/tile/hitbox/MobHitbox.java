package com.riphtix.vgmad.level.tile.hitbox;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.Tile;

public class MobHitbox extends Tile {

	public MobHitbox(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen){
		screen.renderSprite(x, y, sprite, true);
	}
}
