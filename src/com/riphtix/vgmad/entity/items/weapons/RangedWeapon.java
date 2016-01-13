package com.riphtix.vgmad.entity.items.weapons;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.hitbox.ItemHitbox;

public class RangedWeapon extends Weapon {

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
	 * @param range
	 * @param damage
	 * @param damageType
	 */

	public RangedWeapon(String name, int rarity, Sprite sprite, double range, double damage, DamageType damageType) {
		super(name, rarity, sprite, damage, damageType);
		this.range = range;
		hitbox = new ItemHitbox(Sprite.hitbox16x16);
		addItem(this);
	}

	public void render(Screen screen) {
		screen.renderItem((int) x, (int) y, this);
		hitbox.render((int) x, (int) y, screen);
	}
}
