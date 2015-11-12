package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class Tile {

	public int x;
	public int y;
	public Sprite sprite;

	public static Tile grassTile = new GrassTile(Sprite.grassSprite);
	public static Tile dirtTile = new DirtTile(Sprite.dirtSprite);
	public static Tile stoneTile = new StoneTile(Sprite.stoneSprite);
	public static Tile stoneBrickTile = new StoneBrickTile(Sprite.stoneBrickSprite);
	public static Tile woodenPlankTile = new WoodenPlankTile(Sprite.woodenPlankSprite);

	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	public static final int colorGrass = 0xff267f00;
	public static final int colorDirt = 0xff7F3300;
	public static final int colorStone = 0xff808080;
	public static final int colorStoneBrick = 0xff404040;
	public static final int colorWoodenPlank = 0xffd3783b;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}

	public boolean isSolid() {//public boolean solid()
		return false;
	}
}
