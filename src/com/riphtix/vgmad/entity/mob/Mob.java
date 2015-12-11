package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.entity.projectile.MageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.handler.Keyboard;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	public boolean walking = false;

	//TODO: Damage from projectiles and npc health
	protected int health;
	protected int mana;
	protected int xp;

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public Direction dir;

	public void move(double xa, double ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if (ya < 0) dir = Direction.UP;
		if (ya > 0) dir = Direction.DOWN;
		if (xa < 0) dir = Direction.LEFT;
		if (xa > 0) dir = Direction.RIGHT;

		//collision handling
		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!collision(abs(xa), ya)) {
					this.x += abs(xa);
				}
				xa -= abs(xa);
			} else {
				if (!collision(abs(xa), ya)) {
					this.x += xa;
				}
				xa = 0;
			}
		}
		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!collision(xa, abs(ya))) {
					this.y += abs(ya);
				}
				ya -= abs(ya);
			} else {
				if (!collision(xa, abs(ya))) {
					this.y += ya;
				}
				ya = 0;
			}
		}

	}

	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public abstract void tick();//public abstract void update()

	public abstract void render(Screen screen);

	protected void shoot(double x, double y, double dir, Entity entity) {
		Projectile p = null;
		if (entity instanceof Shooter || entity instanceof Player) {
			p = new MageProjectile(x, y, dir);
		}
		level.add(p);
	}

	private boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 * 15) / 16;
			double yt = ((y + ya) - c / 2 * 15) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).isSolid()) solid = true;
		}
		return solid;
	}

}
