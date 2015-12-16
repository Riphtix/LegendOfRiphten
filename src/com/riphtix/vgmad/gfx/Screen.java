package com.riphtix.vgmad.gfx;

import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.level.tile.Tile;

import java.util.Random;

public class Screen {
	public int width;
	public int height;

	//Map size in tiles
	private final int MAP_WIDTH = 64;
	private final int MAP_HEIGHT = 64;
	private final int MAP_SIZE = MAP_WIDTH * MAP_HEIGHT;
	//Tile size in pixels
	private final int TILE_WIDTH = 16;
	private final int TILE_WIDTH_SHIFT = 4;
	private final int TILE_HEIGHT = 16;
	private final int TILE_HEIGHT_SHIFT = 4;
	private final int TILE_SIZE = TILE_WIDTH * TILE_HEIGHT;

	//Screen offsets
	public int xOffset;
	public int yOffset;

	//color control
	public int[] pixels;

	//# of tiles in the map
	public int[] tiles = new int[MAP_SIZE];

	private Random random = new Random();

	//Screen Constructor
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

		//random colors
		for (int i = 0; i < MAP_SIZE; i++) {
			//use tiles[i] = 0x(hex code) to set a specific color
			tiles[i] = random.nextInt(0xffffff);
		}
		//tiles[0] = 0x000000;
	}

	//clears the screen
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	//draws a SpriteSheet to the screen
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		//sets the color for every x and y value
		for (int y = 0; y < sheet.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sheet.getPixels()[x + y * sheet.getWidth()];
			}
		}
	}

	//draws a sprite to the screen
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		//if fixed is true then the sprite doesn't move but if it is false it does
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		//sets the color for every x and y value
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if(col != 0xffff00ff && col != 0xff7f007f) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderTextCharacter(int xp, int yp, Sprite sprite, int color, boolean fixed) {
		//if fixed is true then the sprite doesn't move but if it is false it does
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		//sets the color for every x and y value
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if(col != 0xffff00ff && col != 0xff7f007f) pixels[xa + ya * width] = color;
			}
		}
	}

	//draws a projectile to the screen
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;

		//sets the color for every x and y value
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < -p.getSpriteSize() || ya >= height) break;
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				//this allows me to use "True Pink" (ff00ff) and a sort of purple (7f007f) color as a grid on my sprites
				//and it will only render if the pixels' colors are not pink or purple
				if (col != 0xffff00ff && col != 0xff7f007f) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}

	//draws a tile to the screen
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;

		//sets the color for every x and y value
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < -tile.sprite.SIZE || ya >= height) break;
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int col = tile.sprite.pixels[x + y * tile.sprite.SIZE];
				if(col != 0xffff00ff && col != 0xff7f007f){
					pixels[xa + ya * width] = col;
				}
			}
		}
	}

	//draws a mob to the screen (including players)
	public void renderMob(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < -32 || ya >= height) break;
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int col = sprite.pixels[x + y * 32];
				if (col != 0xffff00ff && col != 0xff7f007f) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}

	public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed){
			xp -= xOffset;
			yp -= yOffset;
		}

		for(int x = xp; x < xp + width; x++){
			if(x < 0 || x >= this.width || yp >= this.height) continue;
			if(yp > 0) pixels[x + yp * this.width] = color;
			if(yp + height >= this.height) continue;
			if(yp + height > 0 ) pixels[x + (yp + height) * this.width] = color;
		}
		for(int y = yp; y <= yp + height; y++){
			if(xp >= this.width || y < 0 || y >= this.height) continue;
			if(xp > 0) pixels[xp + y * this.width] = color;
			if(xp + width >= this.width) continue;
			if(xp + width > 0) pixels[(xp + width) + y * this.width] = color;
		}

	}

	public void drawRect(int xp, int yp, int width, int height, int color) {
		for(int x = xp; x < xp + width; x++){
			if(x < 0 || x >= this.width || yp >= this.height) continue;
			if(yp > 0) pixels[x + yp * this.width] = color;
			if(yp + height >= this.height) continue;
			if(yp + height > 0 ) pixels[x + (yp + height) * this.width] = color;
		}
		for(int y = yp; y <= yp + height; y++){
			if(xp >= this.width || y < 0 || y >= this.height) continue;
			if(xp > 0) pixels[xp + y * this.width] = color;
			if(xp + width >= this.width) continue;
			if(xp + width > 0) pixels[(xp + width) + y * this.width] = color;
		}

	}

	//sets the x and y offsets if needed
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}