package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.exp.Experience;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.handler.Sound;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;

import java.util.List;

public class Chaser extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.femaleFighter_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.femaleFighter_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.femaleFighter_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.femaleFighter_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private double xa = 0.0;
	private double ya = 0.0;
	private double speed = 1.0;

	public MobHitbox hitbox;

	public Chaser(int x, int y, int level) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = animSprite.getSprite();
		hitbox = new MobHitbox(Sprite.hitbox21x32);

		//Chaser default attributes
		health = 100;
		mana = 100;
		rank = level;
		armor = 1.0;
		protectSpell = 1.0;
	}

	private void move() {
		xa = 0;
		ya = 0;
		List<Player> players = level.getPlayers(this, 50);
		if (players.size() > 0) {
			Player player = players.get(0);
			if (x < player.getX()) xa += speed;
			else if (x > player.getX()) xa -= speed;
			if (y < player.getY()) ya += speed;
			else if (y > player.getY()) ya -= speed;
		}
		if (xa != 0 || ya != 0) {
			move(xa, 8, -10, ya, 0, 14);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void tick() {
		move();
		if (walking) animSprite.tick();
		else animSprite.setFrame(0);
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

	}

	public void chaserDamaged(double damage) {

		// can have a multiplier here to reduce health damage due to spells or armor
		health -= damage * armor * protectSpell;

		if (isDead()){
			Sound.SoundEffect.FEMALE_DEAD.play();
			level.getClientPlayer().xp += Experience.calculateXPFromMob(this);
			level.getClientPlayer().totalXP += Experience.calculateXPFromMob(this);
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level, 0xffc40000));
			remove();
		}
	}

	public boolean isDead(){
		return (health <= 0);
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
		hitbox.render((int) x - 10, (int) y - 16, screen);
	}
}
