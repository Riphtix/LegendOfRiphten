package com.riphtix.vgmad.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int SPRITE_WIDTH;
	public final int SPRITE_HEIGHT;
	private int width, height;
	public int[] pixels;

	//Tiles
	public static SpriteSheet tiles = new SpriteSheet("/textures/tiles.png", 256);

	//Mobs
	public static SpriteSheet mobs = new SpriteSheet("/entities/mobs/mobs.png", 1024);

	public static SpriteSheet maleElf = new SpriteSheet("/entities/mobs/player/maleElf.png", 96, 128);
	public static SpriteSheet maleElf_down = new SpriteSheet(maleElf, 0, 0, 3, 1, 32);
	public static SpriteSheet maleElf_up = new SpriteSheet(maleElf, 0, 3, 3, 1, 32);
	public static SpriteSheet maleElf_left = new SpriteSheet(maleElf, 0, 1, 3, 1, 32);
	public static SpriteSheet maleElf_right = new SpriteSheet(maleElf, 0, 2, 3, 1, 32);

	public static SpriteSheet femaleElf = new SpriteSheet("/entities/mobs/player/femaleElf.png", 96, 128);
	public static SpriteSheet femaleElf_down = new SpriteSheet(femaleElf, 0, 0, 3, 1, 32);
	public static SpriteSheet femaleElf_up = new SpriteSheet(femaleElf, 0, 3, 3, 1, 32);
	public static SpriteSheet femaleElf_left = new SpriteSheet(femaleElf, 0, 1, 3, 1, 32);
	public static SpriteSheet femaleElf_right = new SpriteSheet(femaleElf, 0, 2, 3, 1, 32);

	public static SpriteSheet femaleFighter = new SpriteSheet("/entities/mobs/player/femaleFighter.png", 96, 128);
	public static SpriteSheet femaleFighter_down = new SpriteSheet(femaleFighter, 0, 0, 3, 1, 32);
	public static SpriteSheet femaleFighter_up = new SpriteSheet(femaleFighter, 0, 3, 3, 1, 32);
	public static SpriteSheet femaleFighter_left = new SpriteSheet(femaleFighter, 0, 1, 3, 1, 32);
	public static SpriteSheet femaleFighter_right = new SpriteSheet(femaleFighter, 0, 2, 3, 1, 32);

	public static SpriteSheet ghost = new SpriteSheet("/entities/mobs/ai/ghost.png", 96, 128);
	public static SpriteSheet ghost_down = new SpriteSheet(ghost, 0, 0, 3, 1, 32);
	public static SpriteSheet ghost_up = new SpriteSheet(ghost, 0, 3, 3, 1, 32);
	public static SpriteSheet ghost_left = new SpriteSheet(ghost, 0, 1, 3, 1, 32);
	public static SpriteSheet ghost_right = new SpriteSheet(ghost, 0, 2, 3, 1, 32);

	public static SpriteSheet sorceress = new SpriteSheet("/entities/mobs/ai/sorceress.png", 96, 128);
	public static SpriteSheet sorceress_down = new SpriteSheet(sorceress, 0, 0, 3, 1, 32);
	public static SpriteSheet sorceress_up = new SpriteSheet(sorceress, 0, 3, 3, 1, 32);
	public static SpriteSheet sorceress_left = new SpriteSheet(sorceress, 0, 1, 3, 1, 32);
	public static SpriteSheet sorceress_right = new SpriteSheet(sorceress, 0, 2, 3, 1, 32);

	//Other Entities
	public static SpriteSheet projectiles = new SpriteSheet("/entities/projectiles/projectiles.png", 256);

	//Debug
	public static SpriteSheet debug = new SpriteSheet("/debug/debug.png", 256);

	//Hitbox
	public static SpriteSheet mobHitbox = new SpriteSheet("/textures/mobHitbox.png", 32);
	public static SpriteSheet projectileHitbox = new SpriteSheet("/textures/projectileHitbox.png", 16);

	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		SIZE = (width == height) ? width : -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];

		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * SPRITE_WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		SPRITE_WIDTH = size;
		SPRITE_HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = (width == height) ? width : -1;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		load();
	}

	public String getPath(){
		return path;
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public int[] getPixels(){
		return pixels;
	}

	private void load() {
		try {
			System.out.print("Trying to load: " + path + "...");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println(" succeeded!");
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			System.err.println("Trying to load: " + path + "..." + " failed!");
		}

	}
}
