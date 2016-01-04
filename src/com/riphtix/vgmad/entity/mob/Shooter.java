package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.particle.Particle;
import com.riphtix.vgmad.entity.projectile.FireMageProjectile;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.handler.Sound;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;
import com.riphtix.vgmad.level.tile.hpBar.MobHealthBar;
import com.riphtix.vgmad.util.Vector2i;

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

	public MobHitbox hitbox;
	//public MobHealthBar healthBar;

	public Shooter(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = animSprite.getSprite();
		firerate = FireMageProjectile.FIRE_RATE;
		hitbox = new MobHitbox(Sprite.hitbox32x32);
		//healthBar = new MobHealthBar((int) this.x - 10, (int) this.y - 20, new SpriteSheet("/ui/hpBars/100Percent.png", 20, 2));
		range = 336;

		//Shooter default attributes
		health = 100;
		mana = 100;
		xpLevel = 1;
		armor = 1.0;
		protectSpell = 1.0;
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
		if (firerate > 0) firerate--;
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
			move(xa, 8, -10, ya, 0, 14);
			walking = true;
		} else {
			walking = false;
		}
		//healthBar.remove();
		shootClosest();
		//shootRandom();
	}

	public void shooterDamaged(double damage) {

		// can have a multiplier here to reduce health damage due to spells or armor
		health -= damage * armor * protectSpell;

		if (isDead()){
			Sound.SoundEffect.FEMALE_DEAD.play();
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level, 0xffc40000));
			remove();
		}
	}

	public boolean isDead(){
		return (health <= 0);
	}

	private void shootRandom() {
		if (time % 60 == 0) {
			List<Entity> entities = level.getEntities(this, 336);
			entities.add(level.getClientPlayer());
			int index = random.nextInt(entities.size());
			rand = entities.get(index);
		}

		if (rand != null) {
			if (!(rand instanceof Particle) && !(rand instanceof ParticleSpawner) && rand instanceof Player && firerate <= 0) {
				double dx = rand.getX() - x;
				double dy = rand.getY() - y;
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir, this);
				firerate = FireMageProjectile.FIRE_RATE;
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
			if (!(closest instanceof Particle) && !(closest instanceof ParticleSpawner) && closest instanceof Player && firerate <= 0) {
				double dx = closest.getX() - x;
				double dy = closest.getY() - y;
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir, this);
				firerate = FireMageProjectile.FIRE_RATE;
			}
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x - 16, (int) y - 16, sprite);
		hitbox.render((int) x - 10, (int) y - 16, screen);
		//level.add(new MobHealthBar((int) x - 10, (int) y - 20, healthBar.sheet));
		//healthBar.render((int) x - 10, (int) y - 20, health, screen);
	}
}
