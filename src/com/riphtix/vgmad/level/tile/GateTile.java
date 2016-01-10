package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class GateTile extends Tile {

	public GateTile(Sprite sprite, boolean locked) {
		super(sprite);
		this.locked = locked;
		if (!isLocked()){
			this.sprite = Sprite.unlockedGateSprite;
		}
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	public void tick(){
		render(x, y, screen);
	}

	public boolean isDoorway(){
		return true;
	}

	public boolean isLocked(){
		return locked;
	}

	public void setLocked(boolean locked){
		this.locked = locked;
	}
}
