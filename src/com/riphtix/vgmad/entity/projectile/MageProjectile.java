package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.util.AffineTransform;

import java.awt.*;

public class MageProjectile extends Projectile {

	public static final int FIRE_RATE = 15; //Higher = slower

	public MageProjectile(double x, double y, double dir, Entity entity) {
		super(x, y, dir);
		range = entity.range;
		speed = NORMAL_SPEED;
		damage = 20;
		sprite = Sprite.rotate(Sprite.fireBoltSprite, angle);
		hitbox = new Rectangle((int) x, (int) y, 15 * 3, 15 * 3);

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void tick() {//public void update()
		/*if (level.tileCollision((int) (x + nx), (int) (y + ny), 8, 7, 7)) {
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
			remove();
		}*/
		if(hitboxCollision(level.getClientPlayer(), this, -8, -8)){
			System.out.println("player hitbox hit!!!");
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
			remove();
		}

		if(isCollision(x, -7, 8, y, 0, 8)){
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
			remove();
		}
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		hitbox.x += (int) nx * 3;
		hitbox.y += (int) ny * 3;
		if (distance() > range) {
			remove();
		}

	}

	private double distance() {
		return Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 8, (int) y - 4, this);
	}
}
