package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.exp.Experience;
import com.riphtix.vgmad.entity.items.Inventory;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.handler.Sound;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;
import com.riphtix.vgmad.level.tile.hpBar.MobHealthBar;

public class Dummy extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.femaleElf_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.femaleElf_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.femaleElf_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.femaleElf_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int time = 0;
	private int xa = 0;
	private int ya = 0;

	public MobHitbox hitbox;
	public MobHealthBar healthBar0;
	public MobHealthBar healthBar25;
	public MobHealthBar healthBar50;
	public MobHealthBar healthBar75;
	boolean health75 = false;
	boolean health50 = false;
	boolean health25 = false;
	boolean health0 = false;

	public Inventory inventory;

	public Dummy(int x, int y, int rank, Classification classification) {
		this.x = x << 4;
		this.y = y << 4;
		this.rank = rank;
		this.classification = classification;
		sprite = animSprite.getSprite();
		hitbox = new MobHitbox(Sprite.hitbox21x32);
		inventory = new Inventory();
		healthBar0 = new MobHealthBar((int) this.x - 10, (int) this.y - 20, Sprite.healthBar0);
		healthBar25 = new MobHealthBar((int) this.x - 5, (int) this.y - 20, Sprite.healthBar25);
		healthBar50 = new MobHealthBar((int) this.x, (int) this.y - 20, Sprite.healthBar50);
		healthBar75 = new MobHealthBar((int) this.x + 5, (int) this.y - 20, Sprite.healthBar75);

		//Dummy default attributes
		maxHealth = Experience.calculateMobHealth(this, 100);
		health = maxHealth;
		maxMana = Experience.calculateMobMana(this, 100);
		mana = maxMana;
		armor = Experience.calculateMobArmor(this, 0);
		protectSpell = 0.0;
	}

	public void tick() {//public void update()
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

		//getStatusEffects();

		healthBar0.setXY(this.x - 10, this.y - 20);
		healthBar25.setXY(this.x - 5, this.y - 20);
		healthBar50.setXY(this.x, this.y - 20);
		healthBar75.setXY(this.x + 5, this.y - 20);
		if(health / maxHealth > 0 && !health0){
			level.add(healthBar0);
			if(health / maxHealth > .25 && !health25){
				level.add(healthBar25);
				if(health / maxHealth > .5 && !health50){
					level.add(healthBar50);
					if(health / maxHealth > .75 && !health75){
						level.add(healthBar75);
						health75 = true;
					}
					health50 = true;
				}
				health25 = true;
			}
			health0 = true;
		}

		if(health / maxHealth <= .75){
			if(health75) {
				healthBar75.remove();
				health75 = false;
			}
			if(health / maxHealth <= .5){
				if(health50) {
					healthBar50.remove();
					health50 = false;
				}
				if(health / maxHealth <= .25){
					if(health25) {
						healthBar25.remove();
						health25 = false;
					}
				}
			}
		}
	}

	public void dummyDamaged(double damage) {
		// can have a multiplier here to reduce health damage due to spells or armor
		if(armor > 0 & protectSpell > 0) {
			health -= (damage - (damage / armor) - (damage / protectSpell));
		} else health -= damage;

		if (isDead()) {
			Sound.SoundEffect.FEMALE_DEAD.play();
			System.out.println(level.getClientPlayer().totalXP);
			level.getClientPlayer().xp += Experience.calculateXPFromMob(this);
			level.getClientPlayer().totalXP += Experience.calculateXPFromMob(this);
			if(inventory != null && inventory.size() != 0){
				for(int i = 0; i < inventory.size(); i++){
					for(int j = 0; j < inventory.get(i).size(); j++){
						level.addItem(inventory.get(i).get(j), (int) x >> 4, (int) y >> 4);
					}
				}
			}
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level, 0xffc40000));
			remove();
			healthBar0.remove();
			healthBar25.remove();
			healthBar50.remove();
			healthBar75.remove();
		}
	}

	public boolean isDead() {
		return (health <= 0);
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x - 16, (int) y - 16, sprite);
		hitbox.render((int) x - 10, (int) y - 16, screen);
	}
}
