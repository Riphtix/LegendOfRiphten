package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.gfx.Font;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.util.Vector2i;

public class UILabel extends UIComponent{

	public String text;
	private Font font;

	public UILabel(Vector2i position, String text) {
		super(position);
		font = new Font();
		this.text = text;
	}

	public void render(Screen screen){
		font.render(position.x + offset.x, position.y + offset.y, -4, 0xff000000, text, screen);
	}
}
