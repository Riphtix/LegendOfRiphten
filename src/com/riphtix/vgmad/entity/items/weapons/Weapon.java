package com.riphtix.vgmad.entity.items.weapons;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.gfx.Sprite;

public class Weapon extends Item {
	
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
	public Weapon(String name, int rarity, Sprite sprite) {
		super(name, rarity, sprite);
	}
}
