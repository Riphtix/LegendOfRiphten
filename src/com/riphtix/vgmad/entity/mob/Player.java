package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.Game;
import com.riphtix.vgmad.entity.exp.Experience;
import com.riphtix.vgmad.entity.items.Inventory;
import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.items.armor.Armor;
import com.riphtix.vgmad.entity.items.basic.ResourceItem;
import com.riphtix.vgmad.entity.items.weapons.Weapon;
import com.riphtix.vgmad.entity.projectile.PlayerFireMageProjectile;
import com.riphtix.vgmad.entity.projectile.Projectile;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.gfx.ui.*;
import com.riphtix.vgmad.gfx.ui.UIManager;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.handler.Mouse;
import com.riphtix.vgmad.handler.Sound;
import com.riphtix.vgmad.level.tile.hitbox.PlayerHitbox;
import com.riphtix.vgmad.util.ImageUtils;
import com.riphtix.vgmad.util.Vector2i;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class Player extends Mob {

	private String name;
	private Keyboard input;
	private Sprite sprite;
	private boolean walking;

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.maleElf_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.maleElf_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.maleElf_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.maleElf_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int firerate = 0;

	private UIManager ui;
	private UIProgressBar uiHealthBar;
	private UIProgressBar uiManaBar;
	private UIProgressBar uiExperienceBar;
	UIProgressMark uiHP25Percent, uiHP50Percent, uiHP75Percent;
	UIProgressMark uiMP25Percent, uiMP50Percent, uiMP75Percent;
	UIProgressMark uiXP25Percent, uiXP50Percent, uiXP75Percent;

	private UIButton uiButtonOptions;
	private UIButton uiButtonImageTest;

	private BufferedImage image;

	public PlayerHitbox hitbox;

	private int time = 0;

	public Player(String name, int x, int y, Keyboard input) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = animSprite.getSprite();
		firerate = PlayerFireMageProjectile.FIRE_RATE;
		hitbox = new PlayerHitbox(Sprite.hitbox21x32);
		inventory = new Inventory();
		range = 336;

		/*inventory.add(Item.fireStaff);
		System.out.println("Just added the item: " + Item.fireStaff.getName());
		System.out.println("inventory.size(): " + inventory.size());
		System.out.println("inventory.get(0): " + inventory.get(0));
*/
		rightXOffset = 8;
		leftXOffset = -10;
		topYOffset = 0;
		bottomYOffset = 14;

		ui = Game.getUIManager();
		UIPanel panel = (UIPanel) new UIPanel(new Vector2i((300 - 80) * 3, 0), new Vector2i(80 * 3, 300 * 3)).setColor(0xff505050);
		ui.addPanel(panel);

		UILabel nameLabel = new UILabel(new Vector2i(40, 200), name);
		nameLabel.setColor(0xffa0a0a0);
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		nameLabel.dropShadow = true;
		panel.addComponent(nameLabel);

		uiHealthBar = new UIProgressBar(new Vector2i(35, 210), new Vector2i(180, 15));
		uiHealthBar.setColor(0xff5f5f5f);
		uiHealthBar.setForegroundColor(new Color(0xffc70000));
		uiHealthBar.dropShadow = true;
		uiHealthBar.dropShadowOffset = 1;
		panel.addComponent(uiHealthBar);
		UILabel hpLabel = new UILabel(new Vector2i(uiHealthBar.position.x - 28, uiHealthBar.position.y + 12), "HP:");
		hpLabel.setColor(0xffa0a0a0);
		hpLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		hpLabel.dropShadow = true;
		hpLabel.dropShadowOffset = 1;
		panel.addComponent(hpLabel);

		uiManaBar = new UIProgressBar(new Vector2i(35, 235), new Vector2i(180, 15));
		uiManaBar.setColor(0xff5f5f5f);
		uiManaBar.setForegroundColor(new Color(0xff009696));
		uiManaBar.dropShadow = true;
		uiManaBar.dropShadowOffset = 1;
		panel.addComponent(uiManaBar);
		UILabel mpLabel = new UILabel(new Vector2i(uiManaBar.position.x - 30, uiManaBar.position.y + 12), "MP:");
		mpLabel.setColor(0xffa0a0a0);
		mpLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		mpLabel.dropShadow = true;
		mpLabel.dropShadowOffset = 1;
		panel.addComponent(mpLabel);

		uiExperienceBar = new UIProgressBar(new Vector2i(35, 260), new Vector2i(180, 15));
		uiExperienceBar.setColor(0xff5f5f5f);
		uiExperienceBar.setForegroundColor(new Color(0xffff7700));
		uiExperienceBar.dropShadow = true;
		uiExperienceBar.dropShadowOffset = 1;
		panel.addComponent(uiExperienceBar);
		UILabel xpLabel = new UILabel(new Vector2i(uiExperienceBar.position.x - 28, uiExperienceBar.position.y + 12), "XP:");
		xpLabel.setColor(0xffa0a0a0);
		xpLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		xpLabel.dropShadow = true;
		xpLabel.dropShadowOffset = 1;
		panel.addComponent(xpLabel);

		uiHP25Percent = new UIProgressMark(new Vector2i(80, 210), new Vector2i(1, 15));
		panel.addComponent(uiHP25Percent);
		uiHP50Percent = new UIProgressMark(new Vector2i(125, 210), new Vector2i(1, 15));
		panel.addComponent(uiHP50Percent);
		uiHP75Percent = new UIProgressMark(new Vector2i(170, 210), new Vector2i(1, 15));
		panel.addComponent(uiHP75Percent);
		uiMP25Percent = new UIProgressMark(new Vector2i(80, 235), new Vector2i(1, 15));
		panel.addComponent(uiMP25Percent);
		uiMP50Percent = new UIProgressMark(new Vector2i(125, 235), new Vector2i(1, 15));
		panel.addComponent(uiMP50Percent);
		uiMP75Percent = new UIProgressMark(new Vector2i(170, 235), new Vector2i(1, 15));
		panel.addComponent(uiMP75Percent);
		uiXP25Percent = new UIProgressMark(new Vector2i(80, 260), new Vector2i(1, 15));
		panel.addComponent(uiXP25Percent);
		uiXP50Percent = new UIProgressMark(new Vector2i(125, 260), new Vector2i(1, 15));
		panel.addComponent(uiXP50Percent);
		uiXP75Percent = new UIProgressMark(new Vector2i(170, 260), new Vector2i(1, 15));
		panel.addComponent(uiXP75Percent);

		//player default attributes
		health = 100;
		maxHealth = 100;
		mana = 100;
		maxMana = 100;
		xp = 0;
		lives = 10;
		xpLevel = 1;
		armor = 1.0;
		baseArmor = 1.0;
		protectSpell = 1.0;

		uiButtonOptions = new UIButton(new Vector2i(139, 178), new Vector2i(75, 24), new UIActionListener() {
			public void performAction() {
				JFrame frame = new JFrame("Controls");

				JPanel jPanel = new JPanel();
				jPanel.setLayout(new FlowLayout());

				JLabel jLabel = new JLabel("<html>> W - Move up<br>> A - Move left<br>> S - Move down<br>> D - Move right<br>> Left click - Shoot</html>");

				jPanel.add(jLabel);

				frame.add(jPanel);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		uiButtonOptions.setButtonListener(new UIButtonListener() {
			public void buttonPressed(UIButton button) {
				super.buttonPressed(button);
				button.performAction();
				button.ignoreNextPress();
			}
		});
		uiButtonOptions.setText("Controls");
		uiButtonOptions.setFont(new Font("Verdana", Font.BOLD, 15));
		uiButtonOptions.setDropShadow(true, 1);
		uiButtonOptions.dropShadow = true;
		panel.addComponent(uiButtonOptions);

		/*to use changeBrightness method in ImageUtils class type:
		BufferedImage imageHover = ImageUtils.changeBrightness(image, amount);
		positive amount is brighter
		negative amount is darker
		*/

		try {
			URL url = this.getClass().getResource("/ui/home.png");
			image = ImageIO.read(url);
			System.out.println(image.getType());
		} catch (IOException e) {
			e.printStackTrace();
		}

		uiButtonImageTest = new UIButton(new Vector2i(206, 468), image, new UIActionListener() {
			public void performAction() {
				System.exit(0);
			}
		});
		uiButtonImageTest.setButtonListener(new UIButtonListener() {
			public void mouseEnteredButtonBounds(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(image, 50));
			}

			public void mouseExitedButtonBounds(UIButton button) {
				button.setImage(image);
			}

			public void buttonPressed(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(image, 75));
			}

			public void buttonReleased(UIButton button) {
				button.setImage(image);
			}
		});
		uiButtonImageTest.dropShadow = true;
		panel.addComponent(uiButtonImageTest);
	}

	public String getName() {
		return name;
	}

	public void tick() {//public void update()
		time++;
		if (walking) {
			if (time % 20 == 0) {
				Random random = new Random();
				int randomInt = random.nextInt(3) + 1;
				if (randomInt == 1) {
					Sound.SoundEffect.FOOTSTEP_1.volume = Sound.SoundEffect.Volume.MEDIUM;
					Sound.SoundEffect.FOOTSTEP_1.play();
				} else if (randomInt == 2) {
					Sound.SoundEffect.FOOTSTEP_2.volume = Sound.SoundEffect.Volume.MEDIUM;
					Sound.SoundEffect.FOOTSTEP_2.play();
				} else if (randomInt == 3) {
					Sound.SoundEffect.FOOTSTEP_3.volume = Sound.SoundEffect.Volume.MEDIUM;
					Sound.SoundEffect.FOOTSTEP_3.play();
				}
			}
			animSprite.tick();
		} else animSprite.setFrame(0);
		if (firerate > 0) firerate--;
		double xa = 0, ya = 0;
		double speed = 1.5;

		int hbSpriteWidth = hitbox.sprite.getWidth();
		int hbSpriteHeight = hitbox.sprite.getHeight();
		Item closest = level.getClosestItem(this, (int) x, (int) y, hbSpriteWidth, hbSpriteHeight);

		if (closest != null) {
			if (closest instanceof ResourceItem) {
				ResourceItem closestItem = (ResourceItem) closest;
				if (itemCollision(closestItem.hitbox, this.hitbox)) {
					inventory.add(closest);
					closest.remove();
					Sound.SoundEffect.COLLECT_ITEM_POP.play();
				}
			} else if (closest instanceof Weapon) {
				Weapon closestItem = (Weapon) closest;
				if (itemCollision(closestItem.hitbox, this.hitbox)) {
					inventory.add(closest);
					closest.remove();
					Sound.SoundEffect.COLLECT_ITEM_POP.play();
				}
			} else if (closest instanceof Armor) {
				Armor closestItem = (Armor) closest;
				if (itemCollision(closestItem.hitbox, this.hitbox)) {
					inventory.add(closest);
					closest.remove();
					Sound.SoundEffect.COLLECT_ITEM_POP.play();
				}
			}
		}
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
			move(xa, rightXOffset, leftXOffset, ya, topYOffset, bottomYOffset);
			walking = true;
		} else walking = false;

		clear();
		tickShooting();

		if (health <= 0) {
			health = 100;
		}

		if (health < maxHealth - .1) {
			health += .1;
		}

		if (mana < maxMana) {
			//mana += .25;
			mana++;
		}

		if (xp >= Experience.getXPToNextLevel()) {
			xp -= Experience.getXPToNextLevel();
			xpLevel++;
		}

		uiHealthBar.setProgress(health / maxHealth);
		uiManaBar.setProgress(mana / maxMana);
		uiExperienceBar.setProgress(xp / Experience.getXPToNextLevel());
	}

	public void playerDamaged(double damage) {

		health -= damage * armor * protectSpell;
		// can have a multiplier here to reduce health damage due to spells or armor

		if (isDead()) {
			if (lives > 0) {
				lives -= 1;
				Sound.SoundEffect.PLAYER_DEAD.play();
				Sound.SoundEffect.LIFE_LOST.play();
			} else level.gameOver();
		}
	}

	public boolean isDead() {
		return (health <= 0);
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
		if (Mouse.getX() > 660)
			return;

		if (Mouse.getButton() == 1 && firerate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			if (mana >= PlayerFireMageProjectile.cost) {
				shoot(x, y, dir, this);
				mana -= PlayerFireMageProjectile.cost;
			}
			firerate = PlayerFireMageProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
		hitbox.render((int) (x - 11), (int) (y - 16), screen);
	}
}