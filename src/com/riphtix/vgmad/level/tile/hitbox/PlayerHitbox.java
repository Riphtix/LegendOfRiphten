package com.riphtix.vgmad.level.tile.hitbox;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.Tile;

import java.awt.*;

public class PlayerHitbox extends Tile {

	private int width, height;
	
	public PlayerHitbox(Sprite sprite, int width, int height) {
		super(sprite);
		this.width = width;
		this.height = height;
	}

	public void render(int x, int y, Screen screen){
		Rectangle bounds = new Rectangle(x, y, width, height);
		screen.drawRect(x, y, width, height, 0x00000000, true);
	}

	public boolean isSolid(){
		return true;
	}
}
