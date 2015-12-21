package com.riphtix.vgmad.events.types;

public class MouseReleasedEvent extends MouseButtonEvent{

	protected MouseReleasedEvent(int button, int x, int y) {
		super(button, x, y, Type.MOUSE_RELEASED);
	}
}
