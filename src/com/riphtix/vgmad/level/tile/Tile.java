package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class Tile {

	public int x;
	public int y;
	public Sprite sprite;

	public static Tile grassTile = new GrassTile(Sprite.grass);

	public Tile(Sprite sprite){
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen){

	}

	public boolean isSolid(){//public boolean solid()
		return false;
	}
}
