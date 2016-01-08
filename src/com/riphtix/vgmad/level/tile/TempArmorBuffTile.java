package com.riphtix.vgmad.level.tile;

import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;

public class TempArmorBuffTile extends Tile {

	private static double armorMultiplier = .5;
	private static int duration = 10;

	public TempArmorBuffTile(Sprite sprite) {
		super(sprite);
	}

	public boolean hasBuff(){
		return true;
	}

	public static void onUpdate(Mob mob){
		if(duration > 0){
			System.out.println("duration: " + duration);
			System.out.println("mob.armor before: " + mob.armor);
			mob.armor *= armorMultiplier;
			System.out.println("mob.armor after: " + mob.armor);
			deincrementDuration();
		} if(duration <= 0){
			resetArmor(mob);
			System.out.println("mob.armor reset: " + mob.armor);
		}
	}

	private static int deincrementDuration(){
		return --duration;
	}

	public static void resetArmor(Mob mob){
		duration = 10;
		mob.armor /= mob.armor;
	}

	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
}
