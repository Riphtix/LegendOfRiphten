package com.riphtix.vgmad.entity.items;

import com.riphtix.vgmad.gfx.Sprite;

public class Weapon extends Item {

	public enum WeaponType {
		RANGED, MELEE, NO_WEAPON
	}

	public enum DamageType {
		BASIC, DAMAGE_OVER_TIME, SLOW
	}

	public WeaponType weaponType = WeaponType.NO_WEAPON;
	public DamageType damageType = DamageType.BASIC;

	public Weapon(String name, int itemID, int rarity, Sprite sprite, WeaponType weaponType, DamageType damageType) {
		super(name, itemID, rarity, sprite);
		this.weaponType = weaponType;
		this.damageType = damageType;
	}

	public void setDamageOverTime(double damagePerSecond){

	}
}
