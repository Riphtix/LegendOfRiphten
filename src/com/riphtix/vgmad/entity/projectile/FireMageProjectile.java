package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.items.weapons.Weapon;
import com.riphtix.vgmad.entity.mob.*;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.handler.Sound;
import com.riphtix.vgmad.level.tile.hitbox.ProjectileHitbox;

public class FireMageProjectile extends Projectile {

	public static final int FIRE_RATE = 15; //Higher = slower

	public ProjectileHitbox hitbox;

	public FireMageProjectile(double x, double y, double dir, Weapon weapon) {
		super(x, y, dir, ProjectileType.FIRE);
		range = weapon.getRange();
		//speed = DIRECTION_TEST_SPEED;
		//speed = TEST_SPEED;
		speed = NORMAL_SPEED;
		//speed = FAST_SPEED;
		damage = 25;
		cost = 10;
		sprite = Sprite.rotate(Sprite.fireBoltSprite, angle);
		hitbox = new ProjectileHitbox(Sprite.rotate(Sprite.hitbox16x8, angle));
		Sound.SoundEffect.LAUNCH_FIREBALL.play();

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void tick() {//public void update()
		int hbSpriteWidth = hitbox.sprite.getWidth();
		int hbSpriteHeight = hitbox.sprite.getHeight();
		Mob closest = level.getClosestMob(this, (int) x, (int) y, hbSpriteWidth, hbSpriteHeight);

		if (closest != null) {
			if (!(closest instanceof Player)) {
				if (closest instanceof Shooter) {
					Shooter closestShooter = (Shooter) closest;
					if (mobHitboxCollision(closestShooter.hitbox, this.hitbox)) {
						level.add(new ParticleSpawner((int) x, (int) y, 22, 30, level, 0xffc40000));
						closestShooter.shooterDamaged(damage);
						//closestShooter.effect = Mob.StatusEffect.FIRE;
						Sound.SoundEffect.FEMALE_DAMAGE_9.play();
						remove();
					}
				} else if (closest instanceof Dummy) {
					Dummy closestDummy = (Dummy) closest;
					if (mobHitboxCollision(closestDummy.hitbox, this.hitbox)) {
						level.add(new ParticleSpawner((int) x, (int) y, 22, 30, level, 0xffc40000));
						closestDummy.dummyDamaged(damage);
						//closestDummy.effect = Mob.StatusEffect.FIRE;
						Sound.SoundEffect.FEMALE_DAMAGE_9.play();
						remove();
					}
				} else if (closest instanceof Chaser) {
					Chaser closestChaser = (Chaser) closest;
					if (mobHitboxCollision(closestChaser.hitbox, this.hitbox)) {
						level.add(new ParticleSpawner((int) x, (int) y, 22, 30, level, 0xffc40000));
						closestChaser.chaserDamaged(damage);
						//closestChaser.effect = Mob.StatusEffect.FIRE;
						Sound.SoundEffect.FEMALE_DAMAGE_9.play();
						remove();
					}
				} else if (closest instanceof AStar) {
					AStar closestAStar = (AStar) closest;
					if (mobHitboxCollision(closestAStar.hitbox, this.hitbox)) {
						level.add(new ParticleSpawner((int) x, (int) y, 22, 30, level, 0xffc40000));
						closestAStar.aStarDamaged(damage);
//						closestAStar.effect = Mob.StatusEffect.FIRE;
						//Sound.SoundEffect.FEMALE_DAMAGE_9.play();
						remove();
					}
				} else if (closest instanceof ChampionShooter) {
					ChampionShooter closestChampionShooter = (ChampionShooter) closest;
					if(mobHitboxCollision(closestChampionShooter.hitbox, this.hitbox)){
						level.add(new ParticleSpawner((int) x, (int) y, 22, 30, level, 0xffc40000));
						closestChampionShooter.championShooterDamaged(damage);
//						closestChampionShooter.effect = Mob.StatusEffect.FIRE;
						Sound.SoundEffect.MALE_HIT.play();
						remove();
					}
				}
			}
		}

		if (isCollision(x, -7, 8, y, 0, 8)) {
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
		hitbox.render((int) x - 8, (int) y, screen);
	}
}
