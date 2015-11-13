package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.entity.mob.Player;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class MageProjectile extends Projectile {

	public MageProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = .1;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.fireBoltSprite;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void tick() {//public void update()
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range){
			remove();
		}
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) x - 8, (int) y - 4, sprite);
	}
}
