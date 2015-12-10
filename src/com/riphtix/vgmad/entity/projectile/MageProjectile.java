package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class MageProjectile extends Projectile {

	public static final int FIRE_RATE = 15; //Higher = slower

	public MageProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 336;
		speed = NORMAL_SPEED;
		damage = 20;
		sprite = Sprite.rotate(Sprite.fireBoltSprite, angle);

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void tick() {//public void update()
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 8, 7, 7)) {
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
			remove();
		}
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) {
			remove();
		}

	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 8, (int) y - 4, this);
	}
}
