package com.riphtix.vgmad.gfx.ui;

public class UIButtonListener {

	public void mouseEnteredButtonBounds(UIButton button) {
		System.out.println("mouseEnteredButtonBounds called!");
		button.setColor(0xff9a9a9a);

	}

	public void mouseExitedButtonBounds(UIButton button) {
		System.out.println("mouseExitedButtonBounds called!");
		button.setColor(0xff6a6a6a);
	}

	public void buttonPressed(UIButton button) {
		System.out.println("buttonPressed called!");
		button.setColor(0xffbababa);
	}

	public void buttonReleased(UIButton button) {
		System.out.println("buttonReleased called!");
		button.setColor(0xff9a9a9a);
	}

}
