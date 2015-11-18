package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.projectile.MageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.handler.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		sprite = animSprite.getSprite();
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = animSprite.getSprite();
		fireRate = MageProjectile.FIRE_RATE;
	}

	public void tick() {//public void update()
		if(walking) animSprite.tick();
		else animSprite.setFrame(0);
		if(fireRate > 0) fireRate--;
		int xa = 0;
		int ya = 0;
		if (anim < 7500) anim++;
		else anim = 0;

		if (input.UP){
			animSprite = up;
			ya--;
		} else if (input.DOWN){
			animSprite = down;
			ya++;
		} if (input.LEFT){
			animSprite = left;
			xa--;
		} else if (input.RIGHT){
			animSprite = right;
			xa++;
		}
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
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, sprite);
	}

}