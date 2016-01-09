package com.riphtix.vgmad.level.tile.hitbox;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.tile.Tile;

import java.awt.*;

public class PlayerHitbox extends Tile {
	public int x, y;
	
	public PlayerHitbox(Sprite sprite) {
		super(sprite);
	}

	public boolean intersects(ItemHitbox hitbox) {
		int thisWidth = this.sprite.getWidth();
		int thisHeight = this.sprite.getHeight();
		int hitboxWidth = hitbox.sprite.getWidth();
		int hitboxHeight = hitbox.sprite.getHeight();
		if (hitboxWidth <= 0 || hitboxHeight <= 0 || thisWidth <= 0 || thisHeight <= 0) {
			return false;
		}
		int thisX = this.x;
		int thisY = this.y;
		int spriteX = hitbox.x;
		int spriteY = hitbox.y;
		hitboxWidth += spriteX;
		hitboxHeight += spriteY;
		thisWidth += thisX;
		thisHeight += thisY;
		//      overflow || intersect
		return ((hitboxWidth < spriteX || hitboxWidth > thisX) &&
				(hitboxHeight < spriteY || hitboxHeight > thisY) &&
				(thisWidth < thisX || thisWidth > spriteX) &&
				(thisHeight < thisY || thisHeight > spriteY));
	}

	public void render(int x, int y, Screen screen){
		this.x = x;
		this.y = y;
		screen.renderSprite(x, y, sprite, true);
	}
}
