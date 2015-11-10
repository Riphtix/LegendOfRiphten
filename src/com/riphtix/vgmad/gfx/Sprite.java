package com.riphtix.vgmad.gfx;

public class Sprite {

	public final int SIZE;
	private int x;
	private int y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grassSprite = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x000000);

	public static Sprite player0 = new Sprite(16, 0, 8, SpriteSheet.tiles);
	public static Sprite player1 = new Sprite(16, 1, 8, SpriteSheet.tiles);
	public static Sprite player2 = new Sprite(16, 0, 9, SpriteSheet.tiles);
	public static Sprite player3 = new Sprite(16, 1, 9, SpriteSheet.tiles);


	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	public void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
