package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.items.Inventory;
import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.projectile.SorceressProjectile;
import com.riphtix.vgmad.entity.projectile.FireMageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.ui.UILabel;
import com.riphtix.vgmad.level.Level;
import com.riphtix.vgmad.level.tile.GateTile;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;
import com.riphtix.vgmad.util.Vector2i;

import javax.swing.*;
import java.awt.*;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	public boolean walking = false;

	public double health;
	public double maxHealth;
	public double mana;
	public double maxMana;
	public int xp;
	public int totalXP;
	public int rank;
	public int lives;
	public double armor;
	public double protectSpell;

	public int rightXOffset;
	public int leftXOffset;
	public int topYOffset;
	public int bottomYOffset;

	public enum Classification {
		BASIC, CHAMPION, LORD, BOSS
	}

	public Classification classification;

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public Direction dir;

	public MobHitbox hitbox;

	public Inventory inventory;

	public void move(double xa, int leftXWidth, int rightXWidth, double ya, int topYHeight, int bottomYHeight) {
		if (xa != 0 && ya != 0) {
			move(xa, leftXWidth, rightXWidth, 0, topYHeight, bottomYHeight);
			move(0, leftXWidth, rightXWidth, ya, topYHeight, bottomYHeight);
			return;
		}

		if (ya < 0) dir = Direction.UP;
		if (ya > 0) dir = Direction.DOWN;
		if (xa < 0) dir = Direction.LEFT;
		if (xa > 0) dir = Direction.RIGHT;

		// Collision handling
		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!isCollision(abs(xa), leftXWidth, rightXWidth, ya, topYHeight, bottomYHeight)) {
					this.x += abs(xa);
				}
				if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
					if (level.getClientPlayer().inventory.contains(Item.key)) {
						level.changeTileProperties((int) x >> 4, (int) y >> 4, false);
						level.getClientPlayer().inventory.remove(Item.key);
						level.getClientPlayer().helpLabel.setText("Key Used");
						level.getClientPlayer().help1Label.setText("");
					} else {
						level.getClientPlayer().helpLabel.setText("Sorry... you need a key!");
						level.getClientPlayer().help1Label.setText("Some Genies drop keys!!!");
					}
				} else if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && !level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
					if (level == Level.floor1) {
						Game.setLevel(Level.floor2);
					}
				}

				xa -= abs(xa);
			} else {
				if (!isCollision(abs(xa), leftXWidth, rightXWidth, ya, topYHeight, bottomYHeight)) {
					this.x += xa;
				}
				if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
					if (level.getClientPlayer().inventory.contains(Item.key)) {
						level.changeTileProperties((int) x >> 4, (int) y >> 4, false);
						level.getClientPlayer().inventory.remove(Item.key);
						level.getClientPlayer().helpLabel.setText("Key Used");
						level.getClientPlayer().help1Label.setText("");
					} else {
						level.getClientPlayer().helpLabel.setText("Sorry... you need a key!");
						level.getClientPlayer().help1Label.setText("Some Genies drop keys!!!");
					}
				} else if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && !level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
					if (level == Level.floor1) {
						Game.setLevel(Level.floor2);
					}
				}

				xa = 0;
			}
		}

		while (ya != 0)

		{
			if (Math.abs(ya) > 1) {
				if (!isCollision(xa, leftXWidth, rightXWidth, abs(ya), topYHeight, bottomYHeight)) {
					this.y += abs(ya);
				}
				if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
					if (level.getClientPlayer().inventory.contains(Item.key)) {
						level.changeTileProperties((int) x >> 4, (int) y >> 4, false);
						level.getClientPlayer().inventory.remove(Item.key);
						level.getClientPlayer().helpLabel.setText("Key Used");
						level.getClientPlayer().help1Label.setText("");
					} else {
						level.getClientPlayer().helpLabel.setText("Sorry... you need a key!");
						level.getClientPlayer().help1Label.setText("Some Genies drop keys!!!");
					}
				} else if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && !level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
					if (level == Level.floor1) {
						Game.setLevel(Level.floor2);
					}
				}

				ya -= abs(ya);
			} else {
				if (!isCollision(xa, leftXWidth, rightXWidth, abs(ya), topYHeight, bottomYHeight)) {
					this.y += ya;
				}
				if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
					if (level.getClientPlayer().inventory.contains(Item.key)) {
						level.changeTileProperties((int) x >> 4, (int) y >> 4, false);
						level.getClientPlayer().inventory.remove(Item.key);
						level.getClientPlayer().helpLabel.setText("Key Used");
						level.getClientPlayer().help1Label.setText("");
					} else {
						level.getClientPlayer().helpLabel.setText("Sorry... you need a key!");
						level.getClientPlayer().help1Label.setText("Some Genies drop keys!!!");
					}
				} else if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && !level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
					if(level == Level.floor1){
						Game.setLevel(Level.floor2);
					}
				}

				ya = 0;
			}
		}

	}

	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public abstract void tick();//public abstract void update()

	public abstract void render(Screen screen);

	protected void shoot(double x, double y, double dir, Entity entity) {
		Projectile p = null;
		if (entity instanceof Shooter) {
			p = new SorceressProjectile(x, y, dir, entity);
		}
		if(entity instanceof ChampionShooter) {
			p = new SorceressProjectile(x, y, dir, entity);
		}
		if (entity instanceof Player) {
			p = new FireMageProjectile(x, y, dir, entity);
		}
		level.add(p);
	}
}
