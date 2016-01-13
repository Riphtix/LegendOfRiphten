package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.hitbox.ProjectileHitbox;

public abstract class Projectile extends Entity {

	public enum ProjectileType{
		FIRE, STUN, POISON, SLOW
	}

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	protected double speed, range;
	public static double cost;
	public static double damage;
	public final double DIRECTION_TEST_SPEED = 0;
	public final double TEST_SPEED = 1;
	public final double NORMAL_SPEED = 4;
	public final double FAST_SPEED = 8;
	public ProjectileHitbox hitbox;
	public ProjectileType projectileType;

	/**
	 *
	 * @param x
	 * @param y
	 * @param dir
	 * @param projectileType
	 */

	public Projectile(double x, double y, double dir, ProjectileType projectileType) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
		this.projectileType = projectileType;
		hitbox = new ProjectileHitbox(Sprite.rotate(Sprite.hitbox16x8, angle));
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	protected void move() {

	}
}
