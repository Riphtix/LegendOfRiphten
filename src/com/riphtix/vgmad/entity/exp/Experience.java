package com.riphtix.vgmad.entity.exp;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Experience extends Game {

	private static int currentLevel;
	private static double xpToNextLevel;

	public static List<Integer> xpGivenByMobs = new ArrayList<Integer>();

	public Experience(){

	}

	public static void init(Level level){
		currentLevel = level.getClientPlayer().xpLevel;

		if(currentLevel <= 10) {
			xpToNextLevel = (40 * (currentLevel * currentLevel)) + (120 * currentLevel);
			level.getClientPlayer().armor += currentLevel * 4;
		}
		if(currentLevel > 10 && currentLevel <= 30) {
			xpToNextLevel = (-.4 * (currentLevel * currentLevel * currentLevel) + (40.4 * (currentLevel * currentLevel) + (132 * currentLevel)));
		}
		if(currentLevel > 30 && currentLevel < 60) {
			xpToNextLevel = ((65 * (currentLevel * currentLevel)) - (165 * currentLevel) - 6750) * .82;
		}

		for(int i = 0; i < 60; i++){
			xpGivenByMobs.add(i, (int)(7 * currentLevel + (currentLevel * (.05 * xpToNextLevel))));
		}
	}

	public static double getXPToNextLevel(){
		return xpToNextLevel;
	}

	public static int getXPGivenByMobAtLevel(int level){
		return xpGivenByMobs.get(level - 1);
	}

}
