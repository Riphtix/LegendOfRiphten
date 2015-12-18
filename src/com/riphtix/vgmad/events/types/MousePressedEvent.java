package com.riphtix.vgmad.events.types;

public class MousePressedEvent extends MouseButtonEvent{
	
	public MousePressedEvent(int button, int x, int y) {
		super(button, x, y, Type.MOUSE_PRESSED);
	}
}
