package com.riphtix.vgmad.entity.spawner;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.level.Level;

public abstract class Spawner extends Entity {

	public enum Type {
		MOB, PARTICLE
	}

	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
