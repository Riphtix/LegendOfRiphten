package com.riphtix.vgmad.gfx.ui;

public class UIButtonListener {

	public void mouseEnteredButtonBounds(UIButton button) {
		button.setColor(0xff9a9a9a);

	}

	public void mouseExitedButtonBounds(UIButton button) {
		button.setColor(0xff6a6a6a);
	}

	public void buttonPressed(UIButton button) {
		button.setColor(0xffbababa);
	}

	public void buttonReleased(UIButton button) {
		button.setColor(0xff9a9a9a);
	}

}
