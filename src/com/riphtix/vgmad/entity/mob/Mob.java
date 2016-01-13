package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.items.Inventory;
import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.items.armor.Armor;
import com.riphtix.vgmad.entity.items.weapons.Weapon;
import com.riphtix.vgmad.entity.projectile.DragonProjectile;
import com.riphtix.vgmad.entity.projectile.SorceressProjectile;
import com.riphtix.vgmad.entity.projectile.FireMageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.ui.UILabel;
import com.riphtix.vgmad.level.Level;
import com.riphtix.vgmad.level.tile.GateTile;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;
import com.riphtix.vgmad.util.Vector2i;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	public boolean walking = false;

	public double health;
	public double maxHealth;
	public double mana;
	public double maxMana;
	public int xp;
	public int totalXP;
	public int rank;
	public int lives;
	public double armor;
	public double protectSpell;

	public int rightXOffset;
	public int leftXOffset;
	public int topYOffset;
	public int bottomYOffset;

	//Resource Items
	protected Item iron = new Item().iron;
	protected Item key = new Item().key;
	protected Item dragonHeart = new Item().dragonHeart;
	//Weapons
	protected Weapon starterFireStaff = new Item().starterFireStaff;
	protected Weapon commonFireStaff = new Item().commonFireStaff;
	//Armor
	protected Armor starterChestPlate = new Item().starterChestPlate;
	protected Armor starterLeggings = new Item().starterLeggings;
	protected Armor starterHelmet = new Item().starterHelmet;
	protected Armor commonChestPlate = new Item().commonChestPlate;
	protected Armor commonLeggings = new Item().commonLeggings;
	protected Armor commonHelmet = new Item().commonHelmet;

	public enum Classification {
		BASIC, CHAMPION, LORD, BOSS
	}

	public Classification classification;

	/*public enum StatusEffect{
		NORMAL, FIRE, STUN, POISON, SLOW;

		public double fireDamage = new Random().nextInt(5) + 5;
		public int fireDamageDuration = 540;
		public double stunDuration = 180;
		public double poisonDamage = new Random().nextInt(4) + 3;
		public int poisonDamageDuration = 540;
		public double slowDuration = 120;
	}*/

	//public StatusEffect effect = StatusEffect.NORMAL;

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public Direction dir;

	public MobHitbox hitbox;

	public Inventory inventory;

	/*public void getStatusEffects(){
		int timer = 0;
		if(effect == StatusEffect.NORMAL){
			timer = 0;
		}
		if(effect == StatusEffect.FIRE){
			while(timer < effect.fireDamageDuration) {
				if (time % 60 == 0) {
					health -= effect.fireDamage;
				}
				timer++;
			}
			effect = StatusEffect.NORMAL;
		}
		if(effect == StatusEffect.STUN){
			while(timer < effect.stunDuration){
				level.getClientPlayer().xa = 0;
				level.getClientPlayer().ya = 0;
				timer++;
			}
			effect = StatusEffect.NORMAL;
		}
		if(effect == StatusEffect.POISON){
			while(timer < effect.poisonDamageDuration){
				if(time % 60 == 0){
					health -= effect.poisonDamage;
				}
				timer++;
			}
			effect = StatusEffect.NORMAL;
		}
		if(effect == StatusEffect.SLOW){
			while(timer < effect.slowDuration){
				level.getClientPlayer().xa *= .5;
				level.getClientPlayer().ya *= .5;
			}
		}
	}*/

	public void move(double xa, int leftXWidth, int rightXWidth, double ya, int topYHeight, int bottomYHeight) {
		if (xa != 0 && ya != 0) {
			move(xa, leftXWidth, rightXWidth, 0, topYHeight, bottomYHeight);
			move(0, leftXWidth, rightXWidth, ya, topYHeight, bottomYHeight);
			return;
		}

		if (ya < 0) dir = Direction.UP;
		if (ya > 0) dir = Direction.DOWN;
		if (xa < 0) dir = Direction.LEFT;
		if (xa > 0) dir = Direction.RIGHT;

		// Collision handling
		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!isCollision(abs(xa), leftXWidth, rightXWidth, ya, topYHeight, bottomYHeight)) {
					this.x += abs(xa);
				}
				if (this instanceof Player) {
					if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
						if (level.getClientPlayer().inventory.contains(key)) {
							level.changeTileProperties((int) x >> 4, (int) y >> 4, false);
							level.getClientPlayer().inventory.remove(key);
							level.getClientPlayer().helpLabel.setText("Key Used");
							level.getClientPlayer().help1Label.setText("");
						} else {
							level.getClientPlayer().helpLabel.setText("Sorry... you need a key!");
							level.getClientPlayer().help1Label.setText("Some Genies drop keys!!!");
						}
					} else if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && !level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
						if (level == Level.floor1) {
							Game.setLevel(Level.floor2);
						} else if(level != Level.floor1 && level == Level.floor2){
							Game.setLevel(Level.floor3);
						}
					}
				}

				xa -= abs(xa);
			} else {
				if (!isCollision(abs(xa), leftXWidth, rightXWidth, ya, topYHeight, bottomYHeight)) {
					this.x += xa;
				}
				if (this instanceof Player) {
					if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
						if (level.getClientPlayer().inventory.contains(key)) {
							level.changeTileProperties((int) x >> 4, (int) y >> 4, false);
							level.getClientPlayer().inventory.remove(key);
							level.getClientPlayer().helpLabel.setText("Key Used");
							level.getClientPlayer().help1Label.setText("");
						} else {
							level.getClientPlayer().helpLabel.setText("Sorry... you need a key!");
							level.getClientPlayer().help1Label.setText("Some Genies drop keys!!!");
						}
					} else if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && !level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
						if (level == Level.floor1) {
							Game.setLevel(Level.floor2);
						} else if(level != Level.floor1 && level == Level.floor2){
							Game.setLevel(Level.floor3);
						}
					}
				}

				xa = 0;
			}
		}

		while (ya != 0)

		{
			if (Math.abs(ya) > 1) {
				if (!isCollision(xa, leftXWidth, rightXWidth, abs(ya), topYHeight, bottomYHeight)) {
					this.y += abs(ya);
				}
				if (this instanceof Player) {
					if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
						if (level.getClientPlayer().inventory.contains(key)) {
							level.changeTileProperties((int) x >> 4, (int) y >> 4, false);
							level.getClientPlayer().inventory.remove(key);
							level.getClientPlayer().helpLabel.setText("Key Used");
							level.getClientPlayer().help1Label.setText("");
						} else {
							level.getClientPlayer().helpLabel.setText("Sorry... you need a key!");
							level.getClientPlayer().help1Label.setText("Some Genies drop keys!!!");
						}
					} else if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && !level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
						if (level == Level.floor1) {
							Game.setLevel(Level.floor2);
						} else if(level != Level.floor1 && level == Level.floor2){
							Game.setLevel(Level.floor3);
						}
					}
				}

				ya -= abs(ya);
			} else {
				if (!isCollision(xa, leftXWidth, rightXWidth, abs(ya), topYHeight, bottomYHeight)) {
					this.y += ya;
				}
				if (this instanceof Player) {
					if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
						if (level.getClientPlayer().inventory.contains(key)) {
							level.changeTileProperties((int) x >> 4, (int) y >> 4, false);
							level.getClientPlayer().inventory.remove(key);
							level.getClientPlayer().helpLabel.setText("Key Used");
							level.getClientPlayer().help1Label.setText("");
						} else {
							level.getClientPlayer().helpLabel.setText("Sorry... you need a key!");
							level.getClientPlayer().help1Label.setText("Some Genies drop keys!!!");
						}
					} else if (level.getTile((int) x >> 4, (int) y >> 4) instanceof GateTile && !level.getTile((int) x >> 4, (int) y >> 4).isLocked()) {
						if (level == Level.floor1) {
							Game.setLevel(Level.floor2);
						} else if(level != Level.floor1 && level == Level.floor2){
							Game.setLevel(Level.floor3);
						}
					}
				}

				ya = 0;
			}
		}

	}

	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public abstract void tick();//public abstract void update()

	public abstract void render(Screen screen);

	protected void shoot(double x, double y, double dir, Entity entity) {
		Projectile p = null;
		if (entity instanceof Shooter) {
			p = new SorceressProjectile(x, y, dir, entity.weapon);
		}
		if (entity instanceof ChampionShooter) {
			p = new SorceressProjectile(x, y, dir, entity.weapon);
		}
		if (entity instanceof Player) {
			p = new FireMageProjectile(x, y, dir, entity.weapon);
		}
		if (entity instanceof Boss){
			p = new DragonProjectile(x, y, dir, ((Boss) entity).dragonHeart);
		}
		level.add(p);
	}
}
