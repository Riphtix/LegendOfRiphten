package com.riphtix.vgmad.entity.items.weapons;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.hitbox.ItemHitbox;

public class Weapon extends Item {

	public enum DamageType{
		DAMAGE_OVER_TIME, STUN, LIFE_STEAL, POISON
	}

	public DamageType damageType;

	protected double damage, range, damageOverTime;
	protected Mob user;
	public ItemHitbox hitbox;

	/**
	 * rarity is a number ranging from 0 - 5
	 * 0 starter
	 * 1 common
	 * 2 uncommon
	 * 3 rare
	 * 4 ultra rare
	 * 5 legendary
	 *
	 * @param name
	 * @param rarity
	 * @param sprite
	 * @param damage
	 * @param damageType
	 */

	public Weapon(String name, int rarity, Sprite sprite, double damage, DamageType damageType) {
		super(name, rarity, sprite);
		this.damageType = damageType;
		this.damage = damage;
		hitbox = new ItemHitbox(Sprite.hitbox16x16);
	}

	public double getDamage(){
		return damage;
	}

	public void setDamageOverTime(double damage){
		damageOverTime = damage;
	}

	public double getDamageOverTime() {
		return damageOverTime;
	}

	public void setUser(Mob user){
		this.user = user;
	}

	public Mob getUser(){
		return user;
	}

	public void setRange(double range){

	}

	public double getRange(){
		return range;
	}

	public void render(Screen screen){
		screen.renderItem((int) x, (int) y, this);
		hitbox.render((int) x, (int) y, screen);
	}
}
