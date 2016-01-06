package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.gfx.Sprite;

public class Armor extends Item {
	private double defense;

	public Armor(String name, int itemID, int rarity, double defense, Sprite sprite) {
		super(name, itemID, rarity, sprite);
		this.defense = defense;
	}
}
