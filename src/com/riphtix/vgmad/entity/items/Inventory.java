package com.riphtix.vgmad.entity.items;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

	HashMap<String, ArrayList<Item>> inventory = new HashMap<String, ArrayList<Item>>();
	ArrayList<ArrayList<Item>> items = new ArrayList<ArrayList<Item>>();

	public void add(Item item){
		String name = item.getName();
		ArrayList<Item> itemGroup = inventory.get(name);
		if(itemGroup == null){ //add a new group, if none exist yet
			itemGroup = new ArrayList<Item>();
			inventory.put(name, itemGroup);
			items.add(itemGroup);
		}
		itemGroup.add(item);
	}

	public void remove(Item item){
		String name = item.getName();
		inventory.remove(name);
	}

	public int size(){
		return inventory.size();
	}

	public boolean contains(String name){
		boolean contains = false;
		if(inventory.containsKey(name)){
			contains = true;
		}
		return contains;
	}

	public ArrayList<Item> get(int i){
		return items.get(i);
	}

	public Item get(int i, int j){
		return items.get(i).get(j);
	}
}
