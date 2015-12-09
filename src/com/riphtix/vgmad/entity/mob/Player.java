package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.projectile.MageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.gfx.ui.UIComponent;
import com.riphtix.vgmad.gfx.ui.UILabel;
import com.riphtix.vgmad.gfx.ui.UIManager;
import com.riphtix.vgmad.gfx.ui.UIPanel;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.handler.Mouse;
import com.riphtix.vgmad.util.Vector2i;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.maleElf_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.maleElf_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.maleElf_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.maleElf_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int fireRate = 0;

	private UIManager ui;

	public Player(Keyboard input) {
		this.input = input;
		sprite = animSprite.getSprite();
		fireRate = MageProjectile.FIRE_RATE;

		ui = Game.getUIManager();
		UIPanel panel = new UIPanel(new Vector2i((300 - 100) * 3, 0), new Vector2i(100 * 3, 168 * 3));
		ui.addPanel(panel);
		panel.addComponent(new UILabel(new Vector2i(10, 24), "Hello"));
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = animSprite.getSprite();
		fireRate = MageProjectile.FIRE_RATE;

		ui = Game.getUIManager();
		UIPanel panel = new UIPanel(new Vector2i((300 - 100) * 3, 0), new Vector2i(100 * 3, 300 *3));
		ui.addPanel(panel);
		panel.addComponent(new UILabel(new Vector2i(10, 24), "Label PlayerTag").setColor(0xff000000));
	}

	public void tick() {//public void update()
		if (walking) animSprite.tick();
		else animSprite.setFrame(0);
		if (fireRate > 0) fireRate--;
		double xa = 0, ya = 0;
		double speed = 1.5;
		if (input.UP) {
			animSprite = up;
			ya -= speed;
		} else if (input.DOWN) {
			animSprite = down;
			ya += speed;
		}
		if (input.LEFT) {
			animSprite = left;
			xa -= speed;
		} else if (input.RIGHT) {
			animSprite = right;
			xa += speed;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else walking = false;

		clear();
		tickShooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) {
				level.getProjectiles().remove(i);
			}
		}
	}

	private void tickShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir, this);
			fireRate = MageProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
	}

}