package com.riphtix.vgmad.entity.items.basic;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.hitbox.ItemHitbox;

public class ResourceItem extends Item {

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
	public ResourceItem(String name, int rarity, Sprite sprite) {
		super(name, rarity, sprite);
		this.itemType = ItemType.BASIC;
		hitbox = new ItemHitbox(Sprite.hitbox16x16);
		addItem(this);
	}

	public void collect(){
		System.out.println("You just picked up: " + name);
	}

	public void render(Screen screen){
		screen.renderItem((int) x, (int) y, this);
		hitbox.render((int) x, (int) y, screen);
	}
}
