package com.riphtix.vgmad.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	//keeps track of keys
	private boolean[] keys = new boolean[65536];
	public boolean UP;
	public boolean DOWN;
	public boolean LEFT;
	public boolean RIGHT;
	public boolean F5;
	public boolean SPACE;

	public void tick() {//public void update()
		//all of the keys that are used as controls are handled here
		UP = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		LEFT = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		DOWN = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		RIGHT = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		F5 = keys[KeyEvent.VK_F5];
		SPACE = keys[KeyEvent.VK_SPACE];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
}
