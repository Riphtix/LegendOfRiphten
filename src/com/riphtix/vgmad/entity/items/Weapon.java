package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.gfx.SpriteSheet;

public class Weapon extends Item {

	public enum WeaponType {
		RANGED, MELEE, NO_WEAPON
	}

	public enum DamageType {
		BASIC, DAMAGE_OVER_TIME, SLOW
	}

	public WeaponType weaponType = WeaponType.NO_WEAPON;
	public DamageType damageType = DamageType.BASIC;

	public Weapon(String name, int itemID, int rarity, SpriteSheet sheet, WeaponType weaponType, DamageType damageType) {
		super(name, itemID, rarity, sheet);
		this.weaponType = weaponType;
		this.damageType = damageType;
	}

	public void setDamageOverTime(double damagePerSecond){

	}
}
