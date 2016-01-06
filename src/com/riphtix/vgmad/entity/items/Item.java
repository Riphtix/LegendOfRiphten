package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Item {

	private String name;
	private int itemID;
	private Sprite sprite;
	private int rarity;

	public List<Item> items = new ArrayList<Item>();

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

	public Item fireStaff;

	public Item(String name, int itemID, int rarity, Sprite sprite){
		this.name = name;
		this.itemID = itemID;
		this.rarity = rarity;
		this.sprite = sprite;

		fireStaff = new Weapon("Fire Staff", 0, 0, Sprite.fireStaff, Weapon.WeaponType.RANGED, Weapon.DamageType.DAMAGE_OVER_TIME);
		addItem(fireStaff);
	}

	private void addItem(Item item){
		items.add(item.itemID, item);
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
