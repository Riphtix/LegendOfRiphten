package com.riphtix.vgmad.level;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Level {

	protected int width;
	protected int height;
	protected int[] tilesInt;
	protected int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();

	public static Level spawn = new SpawnLevel("/levels/spawnLevel.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {

	}

	protected void loadLevel(String path) {

	}

	public void tick() {//public void update()
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).tick();
		}
	}

	private void time() {

	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	public void add(Entity entity){

	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.colorGrass) return Tile.grassTile;
		if (tiles[x + y * width] == Tile.colorDirt) return Tile.dirtTile;
		if (tiles[x + y * width] == Tile.colorStone) return Tile.stoneTile;
		if (tiles[x + y * width] == Tile.colorStoneBrick) return Tile.stoneBrickTile;
		if (tiles[x + y * width] == Tile.colorWoodenPlank) return Tile.woodenPlankTile;
		return Tile.voidTile;
	}
}
