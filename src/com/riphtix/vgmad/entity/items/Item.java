package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.entity.items.basic.ResourceItem;
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
	public ItemHitbox hitbox;

	protected double x, y;

	HashMap<String, Item> items = new HashMap<String, Item>();

	public static Item iron;

	private Level level;


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

	public static void initItems(){
		iron = new ResourceItem("Iron", 1, Sprite.ironSprite);
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
		return x;
	}
	
	public double getY() {
		return y;
	}
}
