package com.riphtix.vgmad.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH;
	public final int HEIGHT;
	public int[] pixels;

	//Tiles
	public static SpriteSheet tiles = new SpriteSheet("/textures/tiles.png", 256);

	//Mobs
	public static SpriteSheet mobs = new SpriteSheet("/entities/mobs/mobs.png", 1024);

	public static SpriteSheet player = new SpriteSheet("/entities/mobs/player/maleElf.png", 96, 128);
	public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 3, 1, 32);
	public static SpriteSheet player_up = new SpriteSheet(player, 0, 3, 3, 1, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 0, 1, 3, 1, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 0, 2, 3, 1, 32);

	public static SpriteSheet dummy = new SpriteSheet("/entities/mobs/player/femaleElf.png", 96, 128);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 3, 1, 32);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 0, 3, 3, 1, 32);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 0, 1, 3, 1, 32);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 0, 2, 3, 1, 32);

	public static SpriteSheet chaser = new SpriteSheet("/entities/mobs/player/femaleFighter.png", 96, 128);
	public static SpriteSheet chaser_down = new SpriteSheet(chaser, 0, 0, 3, 1, 32);
	public static SpriteSheet chaser_up = new SpriteSheet(chaser, 0, 3, 3, 1, 32);
	public static SpriteSheet chaser_left = new SpriteSheet(chaser, 0, 1, 3, 1, 32);
	public static SpriteSheet chaser_right = new SpriteSheet(chaser, 0, 2, 3, 1, 32);

	//Other Entities
	public static SpriteSheet projectiles = new SpriteSheet("/entities/projectiles/projectiles.png", 256);

	//Debug
	public static SpriteSheet debug = new SpriteSheet("/debug/debug.png", 256);

	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		SIZE = (width == height) ? width : -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];

		for(int y0 = 0; y0 < h; y0++){
			int yp = yy + y0;
			for(int x0 = 0; x0 < w; x0++){
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++){
			for(int xa = 0; xa < width; xa++){
				int[] spritePixels = new int[spriteSize * spriteSize];
				for(int y0 = 0; y0 < spriteSize; y0++){
					for(int x0 = 0; x0 < spriteSize; x0++){
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
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
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = (width == height) ? width : -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}

	public Sprite[] getSprites(){
		return sprites;
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
