package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.entity.items.armor.Armor;
import com.riphtix.vgmad.entity.items.armor.BodyArmor;
import com.riphtix.vgmad.entity.items.basic.ResourceItem;
import com.riphtix.vgmad.entity.items.weapons.RangedWeapon;
import com.riphtix.vgmad.entity.items.weapons.Weapon;
import com.riphtix.vgmad.entity.projectile.FireMageProjectile;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.Level;
import com.riphtix.vgmad.level.tile.hitbox.ItemHitbox;

import java.util.HashMap;

public class Item {

	protected String name;
	public Sprite sprite;
	protected int rarity;
	protected boolean removed = false;
	protected double armor;
	public ItemHitbox hitbox;

	protected double x, y;

	HashMap<String, Item> items = new HashMap<String, Item>();

	public enum ItemType{
		WEAPON, ARMOR, BASIC
	}

	public ItemType itemType;

	//ResourceItems
	public Item iron;
	public Item key;
	public Item dragonHeart;

	//Weapons
	public Weapon starterFireStaff;
	public Weapon commonFireStaff;
	public Weapon uncommonFireStaff;

	//Armor
	public Armor starterChestPlate;
	public Armor starterLeggings;
	public Armor starterHelmet;
	public Armor commonChestPlate;
	public Armor commonLeggings;
	public Armor commonHelmet;
	public Armor uncommonChestPlate;
	public Armor uncommonLeggings;
	public Armor uncommonHelmet;

	private Level level;

	public Item(){
		//Resource Items
		iron = new ResourceItem("Iron", 1, Sprite.ironSprite);
		key = new ResourceItem("Key", 1, Sprite.keySprite);
		dragonHeart = new ResourceItem("DragonHeart", 5, Sprite.dragonHeart);

		//Weapons
		starterFireStaff = new RangedWeapon("FireStaffS", 0, Sprite.fireStaffSprite, 200, 25, Weapon.DamageType.DAMAGE_OVER_TIME);
		commonFireStaff = new RangedWeapon("FireStaffC", 1, Sprite.fireStaffSprite, 200, 30, Weapon.DamageType.DAMAGE_OVER_TIME);
		uncommonFireStaff = new RangedWeapon("FireStaffU", 2, Sprite.fireStaffSprite, 200, 40, Weapon.DamageType.DAMAGE_OVER_TIME);

		//Armor
		starterChestPlate = new BodyArmor("ChestS", 0, Sprite.chestPlateSprite, .75, Armor.ArmorType.BODY);
		starterLeggings = new BodyArmor("LegsS", 0, Sprite.leggingsSprite, .6, Armor.ArmorType.LEGS);
		starterHelmet = new BodyArmor("HelmetS", 0, Sprite.helmetSprite, .55, Armor.ArmorType.HEAD);
		commonChestPlate = new BodyArmor("ChestC", 1, Sprite.chestPlateSprite, .8, Armor.ArmorType.BODY);
		commonLeggings = new BodyArmor("LegsC", 1, Sprite.leggingsSprite, .65, Armor.ArmorType.LEGS);
		commonHelmet = new BodyArmor("HelmetC", 1, Sprite.helmetSprite, .6, Armor.ArmorType.HEAD);
		uncommonChestPlate = new BodyArmor("ChestU", 2, Sprite.chestPlateSprite, 1, Armor.ArmorType.BODY);
		uncommonLeggings = new BodyArmor("LegsU", 2, Sprite.leggingsSprite, .7, Armor.ArmorType.LEGS);
		uncommonHelmet = new BodyArmor("HelmetU", 2, Sprite.helmetSprite, .65, Armor.ArmorType.HEAD);
	}

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
	 */

	public Item(String name, int rarity, Sprite sprite){
		this.name = name;
		this.rarity = rarity;
		this.sprite = sprite;
	}

	public void init(Level level){
		this.level = level;
	}

	public void setXY(int x, int y){
		this.x = x << 4;
		this.y = y << 4;
	}

	public String getName(){
		return name;
	}

	public void addItem(Item item){
		String name = item.getName();
		items.put(name, item);
	}

	public void tick(){//public void update()

	}

	public void remove(){
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void render(Screen screen){

	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}

	public double getArmor(){
		return this.armor;
	}
}
