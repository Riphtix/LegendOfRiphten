package com.riphtix.vgmad.level;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.mob.Player;
import com.riphtix.vgmad.entity.particle.Particle;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.level.tile.Tile;

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
	private List<Particle> particles = new ArrayList<Particle>();
	public List<Entity> topLayer = new ArrayList<Entity>();

	private List<Player> players = new ArrayList<Player>();

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

		for(int i = 0; i < particles.size(); i++){
			particles.get(i).tick();
		}

		for(int i = 0; i < players.size(); i++){
			players.get(i).tick();
		}
		remove();
	}

	private void remove(){
		for (int i = 0; i < entities.size(); i++) {
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}

		for(int i = 0; i < particles.size(); i++){
			if(particles.get(i).isRemoved()) particles.remove(i);
		}

		for(int i = 0; i < players.size(); i++){
			if(players.get(i).isRemoved()) players.remove(i);
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

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).isSolid()) solid = true;
		}
		return solid;
	}

	public boolean tileCollision(int x, int y, int width, int height, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * width + xOffset) >> 4;
			int yt = (y - c / 2 * height + yOffset) >> 4;
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
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
		//screen.renderSprite(Mouse.getX() + 56, Mouse.getY() + 128 * 2, Sprite.aimBox);
	}

	public void add(Entity e) {
		e.init(this);
		if(e instanceof Particle){
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if(e instanceof Player) {
			players.add((Player) e);
		}else {
			entities.add(e);
		}
	}

	public List<Player> getPlayer(){
		return players;
	}

	public Player getPlayerAt(int i){
		return players.get(i);
	}

	public Player getClientPlayer(){
		return players.get(0);
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