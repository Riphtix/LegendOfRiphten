package com.riphtix.vgmad;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.mob.Player;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.ui.UIManager;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.handler.Mouse;
import com.riphtix.vgmad.level.Level;
import com.riphtix.vgmad.level.TileCoordinate;
import com.riphtix.vgmad.level.tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

/**
 * "" is a string
 * int 1
 * double 1.0
 * float 1.0F
 * long 1L
 * boolean true/false
 * && is if(a and b)
 * || is if(a or b)
 * == is if(a equals b)
 * ++ adds one
 * -- subtracts one
 * ! is not
 * << is bit-shifting left and a faster way to multiply
 * >> is bit-shifting right and a faster way to divide
 *
 * @author Riphtix
 * @since 1.0
 */

public class Game extends Canvas implements Runnable {
	//default stuff don't touch
	private static final long serialVersionUID = 1L;

	//TODO: CREATE COMMENTS EXPLAINING CODE

	//screen size stuff
	//width * scale = screen width
	//height * scale = screen height
	public static final int WIDTH = 300 - 80;
	public static final int HEIGHT = 168;
	public static final int SCALE = 3;
	public static String title = "VGMaD: The Legends of Riphten";

	//Variables
	//Built in
	private Thread thread;
	private JFrame frame;
	public static Graphics g;
	//Custom Made
	private static Screen screen;
	private static Keyboard key;
	private static Level level;
	private static Player player;
	public Item item;

	private static UIManager uiManager;

	private boolean running = false;

	//Image storage
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	//Game constructor
	public Game() {
		//Sets the screen size
		Dimension size = new Dimension(WIDTH * SCALE + 80 * 3, HEIGHT * SCALE);
		setPreferredSize(size);

		//Initializing variables
		screen = new Screen(WIDTH, HEIGHT);
		uiManager = new UIManager();
		frame = new JFrame();
		key = new Keyboard();
		item = new Item();
		setLevel(Level.floor1);

		Mouse mouse = new Mouse();
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	private static void initPlayer() {
		if (level == Level.floor1) {
			TileCoordinate playerSpawn = new TileCoordinate(32, 28);
			player = new Player("Nova", playerSpawn.x(), playerSpawn.y(), key);
			level.add(player);
		} else if (level != Level.floor1 && level == Level.floor2) {
			Tile.ironGateLockedTile.setLocked(true);
			Tile.ironGateLockedTile.sprite = Sprite.lockedGateSprite;
			TileCoordinate playerSpawn = new TileCoordinate(32, 51);
			Player oldPlayer = player;
			if (player != null) {
				Player newPlayer = new Player(oldPlayer.getName(), playerSpawn.x(), playerSpawn.y(), key);
				player = newPlayer;
				/*for (int i = 0; i < oldPlayer.inventory.size(); i++) {
					for (int j = 0; j < oldPlayer.inventory.get(i).size(); j++) {
						player.inventory.add(oldPlayer.inventory.get(i).get(j));
					}
				}*/
				player.maxHealth = oldPlayer.maxHealth;
				player.health = oldPlayer.health;
				player.maxMana = oldPlayer.maxMana;
				player.mana = oldPlayer.mana;
				player.xp = oldPlayer.xp;
				player.lives = oldPlayer.lives;
				player.rank = oldPlayer.rank;
				player.armor = oldPlayer.armor;
				player.protectSpell = oldPlayer.protectSpell;
			} else player = new Player("Nova", playerSpawn.x(), playerSpawn.y(), key);
			level.add(player);
		}
	}

	public static int getWindowWidth() {
		return WIDTH * SCALE;
	}

	public static int getWindowHeight() {
		return HEIGHT * SCALE;
	}

	public static UIManager getUIManager() {
		return uiManager;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(ticks + " tps, " + frames + " fps");
				frame.setTitle(title + " | " + ticks + " tps | " + frames + " fps | Lives left: " + player.lives);
				ticks = 0;
				frames = 0;
			}
		}
	}

	public static void setLevel(Level newLevel) {
		screen.clear();
		level = newLevel;
		initPlayer();
	}

	public static Level getLevel(){
		return level;
	}

	public void tick() {//public void update()
		key.tick();
		level.tick();
		uiManager.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}

		screen.clear();
		double xScroll = player.getX() - screen.width / 2;
		double yScroll = player.getY() - screen.height / 2;
		level.render((int) xScroll, (int) yScroll, screen);

		//font.render(0, 0, -2, 0xff000000, "I have won!!!", screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		g = bs.getDrawGraphics();
		g.setColor(new Color(0xff0000ff));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		uiManager.render(g);
		if (SplashScreen.getSplashScreen() != null) {
			SplashScreen.getSplashScreen().close();
		}
		//g.setColor(new Color(0xffff0000));
		//g.drawRect(getWindowWidth() / 2 - 33, getWindowHeight() / 2, 60, 48);
		//g.fillRect(Mouse.getX() - 24, Mouse.getY() - 24, 16 * 3, 16 * 3);
		//g.drawString("x: " + Mouse.getX() + " y: " + Mouse.getY(), 50, 50);
		//g.drawString("x: " + (Mouse.getX() - getWindowWidth() / 2) + " y: " + (Mouse.getY() - getWindowHeight() / 2), 50, 75);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setAutoRequestFocus(true);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();

	}
}