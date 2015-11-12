package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.gfx.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin;
	protected final int yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double speed;
	protected double rateOfFire;
	protected double range;
	protected double damage;

	public Projectile(int x, int y, int dir){
		xOrigin = x;
		yOrigin = y;
		angle = dir;
	}

	protected void move(){

	}
}
