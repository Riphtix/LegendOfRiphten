package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.gfx.Screen;

import java.util.ArrayList;
import java.util.List;

public class UIManager {

	private List<UIPanel> panels = new ArrayList<UIPanel>();

	public UIManager(){

	}

	public void addPanel(UIPanel panel){
		panels.add(panel);
	}

	public void tick(){//public void update()
		for(UIPanel panel: panels){
			panel.tick();
		}
	}

	public void render(Screen screen){
		for(UIPanel panel: panels){
			panel.render(screen);
		}
	}
}
