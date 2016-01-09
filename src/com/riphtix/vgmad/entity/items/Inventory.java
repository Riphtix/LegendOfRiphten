package com.riphtix.vgmad.entity.items;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

	HashMap<String, ArrayList<Item>> inventory = new HashMap<String, ArrayList<Item>>();

	public void add(Item item){
		String name = item.getName();
		ArrayList<Item> itemGroup = inventory.get(name);
		if(itemGroup == null){ //add a new group, if none exist yet
			itemGroup = new ArrayList<Item>();
			inventory.put(name, itemGroup);
		}
		itemGroup.add(item);
	}

	public void remove(Item item){
		String name = item.getName();
		ArrayList<Item> itemGroup = inventory.get(name);
		if(itemGroup == null) return;
		itemGroup.remove(name);
		//remove an empty group
		if(itemGroup.size() == 0){
			inventory.remove(name);
		}
	}

	public int size(){
		return inventory.size();
	}

	public boolean contains(Item item){
		boolean contains = false;
		if(inventory.containsKey(item.getName())){
			contains = true;
		}
		return contains;
	}
}
