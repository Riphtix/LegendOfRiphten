package com.riphtix.vgmad.entity;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.level.Level;

import java.util.Random;

public class Entity {
	public int x;
	public int y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void tick() {//public void update()

	}

	public void render(Screen screen) {

	}

	public void remove() {
		//remove from level
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level){
		this.level = level;
	}
}
