package com.riphtix.vgmad.handler;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.level.Level;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	public boolean UP;
	public boolean DOWN;
	public boolean LEFT;
	public boolean RIGHT;
	public boolean F5;

	public void tick() {//public void update()
		UP = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		LEFT = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		DOWN = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		RIGHT = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		F5 = keys[KeyEvent.VK_F5];
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
