package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.items.weapons.Weapon;
import com.riphtix.vgmad.level.Level;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

	HashMap<String, ArrayList<Item>> inventory = new HashMap<String, ArrayList<Item>>();
	ArrayList<ArrayList<Item>> items = new ArrayList<ArrayList<Item>>();
	Level level = Game.getLevel();

	public void add(Item item) {
		String name = item.getName();
		ArrayList<Item> itemGroup = inventory.get(name);
		if (itemGroup == null) { //add a new group, if none exist yet
			itemGroup = new ArrayList<Item>();
			inventory.put(name, itemGroup);
			items.add(itemGroup);
		}
		itemGroup.add(item);
	}

	public void remove(Item item) {
		String name = item.getName();
		ArrayList<Item> itemGroup = inventory.get(name);
		if (itemGroup == null) return;
		itemGroup.remove(name);

		if (itemGroup.size() >= 0) {
			inventory.remove(name);
		}
	}

	public void remove(ArrayList<Item> items) {
		String name = items.get(0).getName();
		inventory.remove(name);
	}

	public int size() {
		return inventory.size();
	}

	public void drop(Item item, int x, int y) {
		level.addItem(item, x, y);
	}

	public boolean contains(Item item) {
		boolean contains = false;
		if (inventory.containsKey(item.name)) {
			contains = true;
		}
		return contains;
	}

	public boolean containsType(Item.ItemType itemType){
		boolean contains = false;
		for(int i = 0; i < inventory.size(); i++){
			if(get(i).get(0).itemType == itemType){
				contains = true;
			}
		}
		return contains;
	}

	public ArrayList<Item> get(int i) {
		return items.get(i);
	}

	public Item get(int i, int j) {
		return items.get(i).get(j);
	}

	public ArrayList<Item> get(Item item) {
		return inventory.get(item.getName());
	}

	public Item get(Item item, int i) {
		return inventory.get(item).get(i);
	}
}
