package com.riphtix.vgmad;

import com.riphtix.vgmad.entity.mob.Player;
import com.riphtix.vgmad.gfx.*;
import com.riphtix.vgmad.gfx.Font;
import com.riphtix.vgmad.handler.Keyboard;
import com.riphtix.vgmad.handler.Mouse;
import com.riphtix.vgmad.level.Level;
import com.riphtix.vgmad.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

//"" is a string
//int 1
//double 1.0
//float 1.0F
//long 1L
//boolean true/false
//&& is if(a and b)
//|| is if(a or b)
//== is if(a equals b)
//++ adds one
//-- subtracts one
//! is not
//<< is bit-shifting left and a faster way to multiply
//>> is bit-shifting right and a faster way to divide
public class Game extends Canvas implements Runnable {
	//default stuff don't touch
	private static final long serialVersionUID = 1L;

	//screen size stuff
	//width * scale = screen width
	//height * scale = screen height
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 3;
	public static String title = "VGMaD: The Legends of Riphten";

	//Variables
	//Built in
	private Thread thread;
	private JFrame frame;
	//Custom Made
	private Screen screen;
	private Font font;
	private Keyboard key;
	private Level level;
	private Player player;

	private boolean running = false;

	//Image storage
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	//Game constructor
	public Game() {
		//Sets the screen size
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);

		//Initializing variables
		screen = new Screen(WIDTH, HEIGHT);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(32, 28);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		level.add(player);
		font = new Font();

		Mouse mouse = new Mouse();
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return WIDTH * SCALE;
	}

	public static int getWindowHeight() {
		return HEIGHT * SCALE;
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
				frame.setTitle(title + " | " + ticks + " tps | " + frames + " fps");
				ticks = 0;
				frames = 0;
			}
		}
	}

	public void tick() {//public void update()
		key.tick();
		level.tick();
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
		font.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();

	}
}