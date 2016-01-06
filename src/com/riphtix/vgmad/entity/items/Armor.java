package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.gfx.SpriteSheet;

public class Armor extends Item {
	private double defense;

	public Armor(String name, int itemID, int rarity, double defense, SpriteSheet sheet) {
		super(name, itemID, rarity, sheet);
		this.defense = defense;
	}
}
