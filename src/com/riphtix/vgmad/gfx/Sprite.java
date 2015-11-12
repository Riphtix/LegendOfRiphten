package com.riphtix.vgmad.gfx;

public class Sprite {

	public final int SIZE;
	private int x;
	private int y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grassSprite = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite dirtSprite = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite stoneSprite = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite stoneBrickSprite = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite woodenPlankSprite = new Sprite(16, 4, 0, SpriteSheet.tiles);

	public static Sprite voidSprite = new Sprite(16, 0x000000);

	public static Sprite playerUp0 = new Sprite(32, 1, 3, SpriteSheet.mobs);
	public static Sprite playerUp1 = new Sprite(32, 0, 3, SpriteSheet.mobs);
	public static Sprite playerUp2 = new Sprite(32, 2, 3, SpriteSheet.mobs);

	public static Sprite playerDown0 = new Sprite(32, 1, 0, SpriteSheet.mobs);
	public static Sprite playerDown1 = new Sprite(32, 0, 0, SpriteSheet.mobs);
	public static Sprite playerDown2 = new Sprite(32, 2, 0, SpriteSheet.mobs);

	public static Sprite playerLeft0 = new Sprite(32, 1, 1, SpriteSheet.mobs);
	public static Sprite playerLeft1 = new Sprite(32, 0, 1, SpriteSheet.mobs);
	public static Sprite playerLeft2 = new Sprite(32, 2, 1, SpriteSheet.mobs);

	public static Sprite playerRight0 = new Sprite(32, 1, 2, SpriteSheet.mobs);
	public static Sprite playerRight1 = new Sprite(32, 0, 2, SpriteSheet.mobs);
	public static Sprite playerRight2 = new Sprite(32, 2, 2, SpriteSheet.mobs);


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
