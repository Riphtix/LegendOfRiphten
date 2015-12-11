package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.util.Vector2i;

import java.awt.*;

public class UIButton extends UIComponent {

	private UIButtonListener buttonListener;
	private UILabel label;

	public UIButton(Vector2i position, Vector2i size) {
		super(position, size);
	}

	public void setText(String text){
//		label.text = text;
	}

	public void tick(){//public void update()

	}

	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);

		if(label != null){
			label.render(g);
		}
	}
}
