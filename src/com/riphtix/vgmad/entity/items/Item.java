package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.level.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Item {

	private String name;
	private int itemID;
	public SpriteSheet sheet;
	private int rarity;
	private String path;

	public static List<Item> items = new ArrayList<Item>();

	protected Level level;

	/**
	 * rarity is a number ranging from 0 - 5
	 * 0 starter
	 * 1 common
	 * 2 uncommon
	 * 3 rare
	 * 4 ultra rare
	 * 5 legendary
	 */

	public static Weapon fireStaff;

	public Item(){
		fireStaff = new Weapon("Fire Staff", 1, 0, SpriteSheet.fireStaff, Weapon.WeaponType.RANGED, Weapon.DamageType.DAMAGE_OVER_TIME);
	}

	public Item(String name, int itemID, int rarity, String path){
		this.name = name;
		this.itemID = itemID;
		this.rarity = rarity;
		this.path = path;

		addItem(fireStaff);
	}

	public String getPath(){
		return path;
	}

	public String getName(){
		return name;
	}

	public static void addItem(Item item){
		items.add(item);
	}

	public List<Item> getInventory(Mob mob){
		List<Item> result = new ArrayList<Item>();
		for(Item item : mob.inventory){
			result.add(item);
		}
		return result;
	}

	public void tick(){//public void update()

	}

	public void render(Graphics g){

	}
}
