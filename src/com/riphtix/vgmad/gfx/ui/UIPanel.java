package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.util.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class UIPanel {
	private List<UIComponent> components = new ArrayList<UIComponent>();
	private Vector2i position;

	private Sprite sprite;

	public UIPanel(Vector2i position) {
		this.position = position;
		sprite = new Sprite(80, Game.getWindowHeight(), 0xcacaca);
	}

	public void addComponent(UIComponent component){
		components.add(component);
	}

	public void tick() {//public void update()
		for (UIComponent component : components) {
			component.setOffset(position);
			component.tick();
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(position.x, position.y, sprite, false);
		for (UIComponent component : components) {
			component.render(screen);
		}
	}
}
