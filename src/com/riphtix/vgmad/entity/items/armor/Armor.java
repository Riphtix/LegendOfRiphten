package com.riphtix.vgmad.entity.items.armor;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.gfx.Sprite;

public class Armor extends Item {

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

	public Armor(String name, int rarity, Sprite sprite, double armor) {
		super(name, rarity, sprite);
		this.armor = armor;
	}
}
