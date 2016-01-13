package com.riphtix.vgmad.entity.items.armor;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.hitbox.ItemHitbox;

public class BodyArmor extends Armor {
	
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
	public BodyArmor(String name, int rarity, Sprite sprite, double armor, ArmorType armorType) {
		super(name, rarity, sprite, armor, armorType);
		hitbox = new ItemHitbox(Sprite.hitbox16x16);
		addItem(this);
	}

	public void render(Screen screen) {
		screen.renderItem((int) x, (int) y, this);
		hitbox.render((int) x, (int) y, screen);
	}
}
