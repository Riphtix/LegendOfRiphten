package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.gfx.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin;
	protected final int yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x;
	protected double y;
	protected double distance;
	protected double nx, ny;
	protected double speed;
	protected double range;
	protected double damage;

	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite(){
		return sprite;
	}

	public int getSpriteSize(){
		return sprite.SIZE;
	}

	protected void move() {

	}
}
