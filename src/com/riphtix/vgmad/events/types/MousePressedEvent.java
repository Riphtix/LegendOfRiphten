package com.riphtix.vgmad.events.types;

public class MousePressedEvent extends MouseButtonEvent{
	
	protected MousePressedEvent(int button, int x, int y) {
		super(button, x, y, Type.MOUSE_PRESSED);
	}
}
