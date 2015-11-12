package com.riphtix.vgmad.entity.projectile;

public class MageProjectile extends Projectile {

	public MageProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		damage = 20;
		rateOfFire = 15;

		nx = speed * Math.cos(angle);
		nx = speed * Math.sin(angle);
	}

	public void tick(){//public void update()
		move();
	}

	protected void move(){
		x += nx;
		y += ny;
	}
}
