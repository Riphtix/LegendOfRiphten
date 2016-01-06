package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.entity.mob.Player;
import com.riphtix.vgmad.util.ImageUtils;
import com.riphtix.vgmad.util.Vector2i;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UIInventory extends UIComponent {

	protected List<UIButton> slots = new ArrayList<UIButton>(8);
	private BufferedImage slotImage;
	private Mob mob;
	
	public UIInventory(Vector2i position, Vector2i size, Mob mob) {
		super(position, size);
		this.mob = mob;
		init();
	}

	private void init() {
		color = new Color(0xff000000);
	}

	public void tick() {//public void update()
		if (mob.inventory.size() == 0) {
			while (slots.size() < 8) {
				for (int i = 0; i < 8; i++) {
					try {
						URL url = this.getClass().getResource("/textures/items/blankSlot.png");
						slotImage = ImageIO.read(url);
					} catch (IOException e) {
						e.printStackTrace();
					}
					slots.add(i, new UIButton(new Vector2i(position.x, position.y), slotImage, new UIActionListener() {
						public void performAction() {

						}
					}));

					slots.get(i).setButtonListener(new UIButtonListener(){
						public void mouseEnteredButtonBounds(UIButton button){
							button.setImage(ImageUtils.changeBrightness(slotImage, 50));
						}

						public void mouseExitedButtonBounds(UIButton button) {
							button.setImage(slotImage);
						}

						public void buttonPressed(UIButton button) {
							button.setImage(ImageUtils.changeBrightness(slotImage, 75));
						}

						public void buttonReleased(UIButton button) {
							button.setImage(slotImage);
						}
					});
				}
			}
		}
		System.out.println("slots.size(): " + slots.size());
		if(slots.size() > 0) {
			slots.get(0).position = new Vector2i(position.x + 2, position.y + 2);
			slots.get(1).position = new Vector2i(position.x + 36, position.y + 2);
			slots.get(2).position = new Vector2i(position.x + 70, position.y + 2);
			slots.get(3).position = new Vector2i(position.x + 104, position.y + 2);
			slots.get(4).position = new Vector2i(position.x + 2, position.y + 36);
			slots.get(5).position = new Vector2i(position.x + 36, position.y + 36);
			slots.get(6).position = new Vector2i(position.x + 70, position.y + 36);
			slots.get(7).position = new Vector2i(position.x + 104, position.y + 36);
		}

		for (int i = 0; i < slots.size(); i++) {
			if (mob.inventory.size() > 0 && mob.inventory.get(i) != null) {
				if (mob instanceof Player) {
					for (int j = 0; j < mob.inventory.size(); j++) {
						System.out.println("mob.inventory.get(j): " + mob.inventory.get(j));
						try {
							URL url = this.getClass().getResource(mob.inventory.get(j).sheet.getPath());
							slotImage = ImageIO.read(url);
							slots.get(i).setImage(slotImage);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					try {
						URL url = this.getClass().getResource(mob.inventory.get(i).sheet.getPath());
						slotImage = ImageIO.read(url);
						slots.get(i).setImage(slotImage);
					} catch (IOException e) {
						e.printStackTrace();

					}
				}
			}
		}
	}

	public void render(Graphics g) {
		int x = position.x + offset.x;
		int y = position.y + offset.y;

		g.setColor(color);
		g.fillRect(x, y, size.x, size.y);
		g.setColor(new Color(0xff6a6a6a));
		g.fillRect(x + 2, y + 2, 32, 32);
		g.fillRect(x + 36, y + 2, 32, 32);
		g.fillRect(x + 70, y + 2, 32, 32);
		g.fillRect(x + 104, y + 2, 32, 32);
		g.fillRect(x + 2, y + 36, 32, 32);
		g.fillRect(x + 36, y + 36, 32, 32);
		g.fillRect(x + 70, y + 36, 32, 32);
		g.fillRect(x + 104, y + 36, 32, 32);
		g.setColor(new Color(0xff606060));
		g.fillRect(x + 32, y, 2, size.y);
		g.fillRect(x + 66, y, 2, size.y);
		g.fillRect(x + 100, y, 2, size.y);
		g.fillRect(x, y + 32, size.x, 2);
	}
}
