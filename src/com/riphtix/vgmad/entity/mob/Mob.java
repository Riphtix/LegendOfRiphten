package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.handler.Keyboard;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected Keyboard key;

	public void move(int xa, int ya) {
		if (ya < 0) dir = 0;
		if (xa > 0) dir = 1;
		if (ya > 0) dir = 2;
		if (xa < 0) dir = 3;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public void tick() {//public void update()

	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		if(level.getTile((x + xa) / 16, (y + ya) / 16).isSolid()) solid = true;
		return solid;
	}

	public void render() {

	}

}
