package com.riphtix.vgmad.level;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.entity.mob.Player;
import com.riphtix.vgmad.entity.particle.Particle;
import com.riphtix.vgmad.entity.projectile.FireMageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.level.tile.hitbox.PlayerHitbox;
import com.riphtix.vgmad.level.tile.Tile;
import com.riphtix.vgmad.util.Vector2i;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Level {

	//Variables
	protected int width, height;
	protected int[] tilesInt, tiles;
	private static Screen screen;
	public Keyboard key;

	//lists of entities for rendering and tracking
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Player> players = new ArrayList<Player>();
	private List<Mob> mobs = new ArrayList<Mob>();

	private int time = 0;

	//used to compare 2 nodes for mob ai navigation
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return +1;
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
	};

	//creates a new level
	public static Level spawn = new SpawnLevel("/levels/spawnLevel.png");

	//Level Constructor
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	//Level Constructor
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	//generates a level (overridden by other classes)
	protected void generateLevel() {

	}

	//loads a level from a file (overridden by other classes
	protected void loadLevel(String path) {

	}

	//updates the entities
	public void tick() {//public void update()
		time++;
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).tick();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).tick();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).tick();
		}
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).tick();
		}
		remove();
	}

	//removes the entities from the map
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}

		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}

		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).isRemoved()) mobs.remove(i);
		}
	}

	//returns the projectile list
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	//returns the entity list
	public List<Entity> getEntities() {
		return entities;
	}

	//returns the mob list
	public List<Mob> getMobs() {
		return mobs;
	}

	//keeps track of the timer
	private void time() {

	}

	//collision with square objects
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).isSolid()) solid = true;
		}
		return solid;
	}

	public boolean mobCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (!(getTile(xt, yt) instanceof PlayerHitbox) && (getTile(xt, yt).isSolid())) solid = true;
		}
		return solid;
	}

	public Mob getClosestMob(Projectile projectile, int x, int y, int width, int height) {
		List<Entity> entities = getEntities(projectile, width, height);
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i) instanceof Player) {
				players.add((Player) mobs.get(i));
				mobs.remove(mobs.get(i));
			} else entities.add(mobs.get(i));
		}

		double min = 0;
		Entity closest = null;
		for (int i = 0; i < entities.size(); i++) {
			Mob m = mobs.get(i);
			if (m instanceof Player) continue;
			double distance = Vector2i.getDistance(new Vector2i(x, y), new Vector2i((int) m.getX(), (int) m.getY()));
			if (i == 0 || distance < min) {
				min = distance;
				closest = m;
			}
		}
		Mob closestMob = null;
		if (closest != null) {
			closestMob = (Mob) closest;
		}

		return closestMob;
	}

	//collision with uneven objects (width 2 height 3)
	public boolean tileCollision(int x, int y, int width, int height, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * width + xOffset) >> 4;
			int yt = (y - c / 2 * height + yOffset) >> 4;
			if (getTile(xt, yt).isSolid()) solid = true;
		}
		return solid;
	}

	// Do this if the player runs out of lives
	public void gameOver() {
		System.out.println("Player is dead!!!");
		System.exit(0);
	}

	//renders all of the lists to the screen
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
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(screen);
		}
		//screen.renderSprite(Mouse.getX() + 56, Mouse.getY() + 128 * 2, Sprite.aimBox);
	}

	//adds an entity to its respective list
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (!(e instanceof Player) && e instanceof Mob) {
			mobs.add((Mob) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}

	//returns a list of players
	public List<Player> getPlayer() {
		return players;
	}

	//returns a specific player at the index given (i)
	public Player getPlayerAt(int i) {
		return players.get(i);
	}

	//gets the player on your machine
	public Player getClientPlayer() {
		return players.get(0);
	}

	//pathfinder method break apart in depth later
	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile at = getTile(x + xi, y + yi);
				if (at == null) continue;
				if (at.isSolid()) continue;
				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95);
				double hCost = getDistance(a, goal);
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost) continue;
				if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
			}
		}
		closedList.clear();
		return null;
	}

	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector)) return true;
		}
		return false;
	}

	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance == 1 ? 1 : 0.95;
	}

	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (entity.equals(e)) continue;
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(entity);
		}

		return result;
	}

	public List<Entity> getEntities(Entity e, int width, int height) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX() - 2;
		int ey = (int) e.getY() - 2;
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (entity.equals(e)) continue;
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= width || distance <= height) result.add(entity);
		}
		return result;
	}

	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(player);
		}
		return result;
	}

	public List<Player> getPlayers(Entity e, int width, int height) {
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= width || distance <= height) result.add(player);
		}
		return result;
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