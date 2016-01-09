package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.items.Inventory;
import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.projectile.FireMageProjectile;
import com.riphtix.vgmad.entity.projectile.PlayerFireMageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.Tile;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	public boolean walking = false;


	//TODO: npc health
	public double health;
	public double maxHealth;
	public double mana;
	public double maxMana;
	public int xp;
	public int lives;
	public int xpLevel;
	public double armor;
	public double baseArmor;
	public double protectSpell;

	public int rightXOffset;
	public int leftXOffset;
	public int topYOffset;
	public int bottomYOffset;

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
				xa -= abs(xa);
			} else {
				if (!isCollision(abs(xa), leftXWidth, rightXWidth, ya, topYHeight, bottomYHeight)) {
					this.x += xa;

				}
				xa = 0;
			}
		}
		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!isCollision(xa, leftXWidth, rightXWidth, abs(ya), topYHeight, bottomYHeight)) {
					this.y += abs(ya);
				}
				ya -= abs(ya);
			} else {
				if (!isCollision(xa, leftXWidth, rightXWidth, abs(ya), topYHeight, bottomYHeight)) {
					this.y += ya;
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
			p = new FireMageProjectile(x, y, dir, entity);
		}
		if (entity instanceof Player) {
			p = new PlayerFireMageProjectile(x, y, dir, entity);
		}
		level.add(p);
	}
}
