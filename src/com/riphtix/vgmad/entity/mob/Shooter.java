package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.particle.Particle;
import com.riphtix.vgmad.entity.projectile.MageProjectile;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;
import com.riphtix.vgmad.util.Debug;
import com.riphtix.vgmad.util.Vector2i;

import java.awt.*;
import java.util.List;

public class Shooter extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.sorceress_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.sorceress_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.sorceress_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.sorceress_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int time = 0;
	private int xa = 0;
	private int ya = 0;

	private Entity rand = null;

	private int firerate = 0;

	public Shooter(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = animSprite.getSprite();
		firerate = MageProjectile.FIRE_RATE;
		range = 336;

		hitbox = new Rectangle(x, y, 19, 16);
	}

	public void tick() {
		time++; //time % 60 == 0 is 1 second
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if (random.nextInt(4) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		if (walking) animSprite.tick();
		else animSprite.setFrame(0);
		if(firerate > 0) firerate--;
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}
		if (xa != 0 || ya != 0) {
			//move(xa, 8, -10, ya, 0, 14);
			walking = true;
		} else {
			walking = false;
		}
		shootClosest();
		//shootRandom();
	}

	private void shootRandom() {
		if (time % 60 == 0) {
			List<Entity> entities = level.getEntities(this, 336);
			entities.add(level.getClientPlayer());
			int index = random.nextInt(entities.size());
			rand = entities.get(index);
		}

		if (rand != null) {
			if (!(rand instanceof Particle) && !(rand instanceof ParticleSpawner) && firerate <= 0) {
				double dx = rand.getX() - x;
				double dy = rand.getY() - y;
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir, this);
				firerate = MageProjectile.FIRE_RATE;
			}
		}
	}

	private void shootClosest() {
		List<Entity> entities = level.getEntities(this, 336);
		entities.add(level.getClientPlayer());

		double min = 0;
		Entity closest = null;
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			double distance = Vector2i.getDistance(new Vector2i((int) x, (int) y), new Vector2i((int) e.getX(), (int) e.getY()));
			if (i == 0 || distance < min) {
				min = distance;
				closest = e;
			}
		}

		if (closest != null) {
			if (!(closest instanceof Particle) && !(closest instanceof ParticleSpawner) && firerate <= 0) {
				double dx = closest.getX() - x;
				double dy = closest.getY() - y;
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir, this);
				firerate = MageProjectile.FIRE_RATE;
			}
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x - 16, (int) y - 16, sprite);
	}
}
