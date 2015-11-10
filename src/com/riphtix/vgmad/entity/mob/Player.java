package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.handler.Keyboard;

public class Player extends Mob {

	private Keyboard input;


	public Player(Keyboard input) {
		this.input = input;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
	}

	public void tick() {//public void update()
		int xa = 0;
		int ya = 0;

		if (input.UP) ya--;
		if (input.DOWN) ya++;
		if (input.LEFT) xa--;
		if (input.RIGHT) xa++;

		if (xa != 0 || ya != 0) move(xa, ya);
	}

	public void render(Screen screen) {
		screen.renderPlayer(x, y, Sprite.player0);
	}

}
