package com.riphtix.vgmad.level;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.handler.Mouse;
import com.riphtix.vgmad.level.tile.Tile;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Level {

	protected int width;
	protected int height;
	protected int[] tilesInt;
	protected int[] tiles;
	private static Screen screen;
	public Keyboard key;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();

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
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).tick();
		}
	}

	public List<Projectile> getProjectiles(){
		return projectiles;
	}

	public List<Entity> getEntities(){
		return entities;
	}

	private void time() {

	}

	public boolean tileCollision(double x, double y, double xa, double ya, int size) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (((int)x + (int)xa) + c % 2 * size / 8) / 16;
			int yt = (((int)y + (int)ya) + c / 2 * size / 8) / 16;
			if (getTile(xt, yt).isSolid()) solid = true;
		}
		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		this.screen = screen;
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

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		//screen.renderSprite(Mouse.getX() + 56, Mouse.getY() + 128 * 2, Sprite.aimBox);
	}

	public void add(Entity e) {
		entities.add(e);
	}

	public void addProjectile(Projectile p) {
		p.init(this);
		projectiles.add(p);
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
