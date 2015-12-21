package com.riphtix.vgmad.entity.particle;

import com.riphtix.vgmad.entity.Entity;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class BloodParticle extends Entity {

	private Sprite sprite;

	private int life;
	private int time = 0;

	protected double xa;
	protected double ya;
	protected double za;

	protected double xx;
	protected double yy;
	protected double zz;

	public BloodParticle(int x, int y, int life, int color) {
		this.x = x;
		this.y = y;

		this.xx = x;
		this.yy = y;

		this.life = life + (random.nextInt(20) - 10);
		sprite = new Sprite(2, color);

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}

	public void tick() {//public void update()
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		za -= 0.1;

		if (zz < 0) {
			zz = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}

		move(xx + xa, (yy + ya) + (zz + za));
	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).isSolid()) solid = true;
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}
}