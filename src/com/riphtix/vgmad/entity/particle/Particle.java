package com.riphtix.vgmad.entity.particle;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class Particle extends Entity {

	private Sprite sprite;

	private int life;
	private int time = 0;

	protected double xa;
	protected double ya;
	protected double xx;
	protected double yy;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(25) - 10);
		sprite = Sprite.defaultParticle;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}

	public void tick() {//public void update()
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		this.xx += xa;
		this.yy += ya;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}
}