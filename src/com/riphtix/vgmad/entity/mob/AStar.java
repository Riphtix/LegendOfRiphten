package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.exp.Experience;
import com.riphtix.vgmad.entity.items.Inventory;
import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.handler.Sound;
import com.riphtix.vgmad.level.Node;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;
import com.riphtix.vgmad.level.tile.hpBar.MobHealthBar;
import com.riphtix.vgmad.util.Vector2i;

import java.util.List;
import java.util.Random;

public class AStar extends Mob{

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.ghost_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.ghost_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.ghost_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.ghost_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private double xa = 0.0;
	private double ya = 0.0;
	private List<Node> path = null;
	private int time = 0;

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

	public AStar(int x, int y, int rank, Classification classification) {
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

		//Shooter default attributes
		maxHealth = Experience.calculateMobHealth(this, 100);
		health = maxHealth;
		maxMana = Experience.calculateMobMana(this, 100);
		mana = maxMana;
		armor = Experience.calculateMobArmor(this, 0);
		protectSpell = 0.0;
	}

	private void move() {
		xa = 0;
		ya = 0;
		int px = (int)level.getPlayerAt(0).getX();
		int py = (int)level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
		Vector2i goal = new Vector2i(px >> 4, py >> 4);
		if(time % 2 == 0) path = level.findPath(start, goal);
		if(path != null){
			if(path.size() > 0){
				Vector2i vec = path.get(path.size() - 1).tile;
				if(x < vec.getX() << 4) xa++;
				if(x > vec.getX() << 4) xa--;
				if(y < vec.getY() << 4) ya++;
				if(y > vec.getY() << 4) ya--;
			}
		}
		if (xa != 0 || ya != 0) {
			move(xa, 8, -10, ya, 0, 14);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void tick() {
		time++;
		move();
		if (walking) animSprite.tick();
		else animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Mob.Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Mob.Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Mob.Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Mob.Direction.RIGHT;
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

	public void aStarDamaged(double damage) {
		// can have a multiplier here to reduce health damage due to spells or armor
		if(armor > 0 & protectSpell > 0) {
			health -= (damage - (damage / armor) - (damage / protectSpell));
		} else health -= damage;

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
