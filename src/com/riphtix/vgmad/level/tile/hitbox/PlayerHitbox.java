package com.riphtix.vgmad.level.tile.hitbox;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.Tile;

import java.awt.*;

public class PlayerHitbox extends Tile {
	public int x, y;
	
	public PlayerHitbox(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen){
		this.x = x;
		this.y = y;
		screen.renderSprite(x, y, sprite, true);
	}
}
