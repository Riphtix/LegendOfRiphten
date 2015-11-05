package com.riphtix.vgmad.gfx;

import java.awt.*;
import java.util.Random;

public class Screen {
	private int width;
	private int height;

	private final int MAP_WIDTH = 8;
	private final int MAP_HEIGHT = 8;
	private final int MAP_SIZE = MAP_WIDTH * MAP_HEIGHT;
	private final int TILE_WIDTH = 16;
	private final int TILE_HEIGHT = 16;
	private final int TILE_SIZE = TILE_WIDTH * TILE_HEIGHT;

	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE; i++) {
			//use tiles[i] = 0x(hex code) to set a specific color
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
			//if (yy < 0 || yy >= height) break;
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset;
				//if (xx < 0 || xx >= width) break;
				int tileIndex = ((xx / TILE_WIDTH) & (MAP_WIDTH - 1)) + ((yy / TILE_HEIGHT) & (MAP_HEIGHT - 1)) * MAP_WIDTH;
				pixels[x + y * width] = tiles[tileIndex];

			}
		}
	}
}