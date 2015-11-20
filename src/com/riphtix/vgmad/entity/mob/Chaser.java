package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.SpriteSheet;

import java.util.List;

public class Chaser extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.chaser_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.chaser_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.chaser_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.chaser_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int xa = 0;
	private int ya = 0;

	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = animSprite.getSprite();
	}

	private void move() {
		xa = 0;
		ya = 0;
		List<Player> players = level.getPlayers(this, 50);
		if(players.size() > 0) {
			Player player = players.get(0);
			if (x < player.getX()) xa++;
			if (x > player.getX()) xa--;
			if (y < player.getY()) ya++;
			if (y > player.getY()) ya--;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void tick() {
		move();
		if (walking) animSprite.tick();
		else animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}

	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, sprite);
	}
}
