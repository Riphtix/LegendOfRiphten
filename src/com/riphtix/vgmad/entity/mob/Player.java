package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.projectile.MageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.handler.Mouse;
import com.riphtix.vgmad.level.Level;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking;

	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		dir = 2;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		dir = 2;
		fireRate = MageProjectile.FIRE_RATE;
	}

	public void tick() {//public void update()

		if(fireRate > 0) fireRate--;
		int xa = 0;
		int ya = 0;
		if (anim < 7500) anim++;
		else anim = 0;

		if (input.UP) ya--;
		if (input.DOWN) ya++;
		if (input.LEFT) xa--;
		if (input.RIGHT) xa++;
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else walking = false;

		clear();
		tickShooting();
	}

	private void clear(){
		for(int i = 0; i < level.getProjectiles().size(); i++){
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()){
				level.getProjectiles().remove(i);
			}
		}
	}

	private void tickShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = MageProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.playerUp0;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerUp1;
				} else {
					sprite = Sprite.playerUp2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.playerRight0;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerRight1;
				} else {
					sprite = Sprite.playerRight2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.playerDown0;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerDown1;
				} else {
					sprite = Sprite.playerDown2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.playerLeft0;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerLeft1;
				} else {
					sprite = Sprite.playerLeft2;
				}
			}
		}

		screen.renderPlayer(x - 16, y - 16, sprite);
	}

}