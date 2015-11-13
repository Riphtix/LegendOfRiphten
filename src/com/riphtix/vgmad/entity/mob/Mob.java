package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.projectile.MageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.handler.Keyboard;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected Keyboard key;

	protected List<Projectile> projectiles = new ArrayList<Projectile>();

	public void move(int xa, int ya) {
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}

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

	protected void shoot(int x, int y, double dir){
		//dir *= 180 / Math.PI;
		Projectile p = new MageProjectile(x, y, dir);
		projectiles.add(p);
		level.add(p);
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if(level.getTile(xt, yt).isSolid()) solid = true;
		}
		return solid;
	}

	public void render() {

	}

}
