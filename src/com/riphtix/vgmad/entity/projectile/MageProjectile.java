package com.riphtix.vgmad.entity.projectile;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class MageProjectile extends Projectile {

	public MageProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.grassSprite;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void tick(){//public void update()
		move();
	}

	protected void move(){
		x += nx;
		y += ny;
	}

	public void render(Screen screen){
		screen.renderSprite(x, y, sprite);
	}
}
