package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.mob.Player;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.hitbox.ProjectileHitbox;

public class PlayerFireMageProjectile extends Projectile {

	public static final int FIRE_RATE = 15; //Higher = slower

	public ProjectileHitbox hitbox;

	public PlayerFireMageProjectile(double x, double y, double dir, Entity entity) {
		super(x, y, dir);
		range = entity.range;
		speed = NORMAL_SPEED;
		damage = 20;
		sprite = Sprite.rotate(Sprite.fireBoltSprite, angle);
		hitbox = new ProjectileHitbox(Sprite.rotate(Sprite.hitbox16x16, angle));

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void tick() {//public void update()
		boolean shooting = false;
		System.out.println(shooting);
		if(level.getClosestMob(this, (int) x, (int) y, hitbox.sprite.getWidth(), hitbox.sprite.getHeight()) != null &&
				mobHitboxCollision(level.getClosestMob(this, (int) x, (int) y, hitbox.sprite.getWidth(), hitbox.sprite.getHeight()), this) &&
				!(level.getClosestMob(this, (int) x, (int) y, hitbox.sprite.getWidth(), hitbox.sprite.getHeight()) instanceof Player)){
			shooting = true;
			System.out.println(shooting);
			System.out.println("mob hitbox hit!!!");
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
		if (distance() > range) {
			remove();
		}

	}

	private double distance() {
		return Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 8, (int) y - 4, this);
		hitbox.render((int) x - 8, (int) y - 4, screen);
	}
}