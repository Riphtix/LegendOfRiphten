package com.riphtix.vgmad.entity.items.armor;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.hitbox.ItemHitbox;

public class Armor extends Item {

	public enum ArmorType{
		HEAD, BODY, LEGS
	}

	public ArmorType armorType;

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
	 * @param armor
	 * @param armorType
	 */

	public Armor(String name, int rarity, Sprite sprite, double armor, ArmorType armorType) {
		super(name, rarity, sprite);
		this.armor = armor;
		this.armorType = armorType;
		hitbox = new ItemHitbox(Sprite.hitbox16x16);

		if(this.armorType == ArmorType.HEAD){
			this.armor += .15;
		}
		if(this.armorType == ArmorType.BODY){
			this.armor += .25;
		}
		if(this.armorType == ArmorType.LEGS){
			this.armor += .2;
		}
	}

	public double getArmor(){
		return armor;
	}

	public void setArmor(double armor){
		this.armor = armor;
	}

	public void setUser(Mob user){
		this.user = user;
	}

	public Mob getUser(){
		return user;
	}
}
