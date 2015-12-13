package com.riphtix.vgmad.level.tile.hitbox;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.Tile;

public class PlayerHitbox extends Tile {
	
	public PlayerHitbox(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen){
		screen.renderPlayerHitbox(x, y, this);
	}

	public boolean isSolid(){
		return true;
	}
}
