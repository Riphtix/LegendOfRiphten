package com.riphtix.vgmad.entity.mob;

import com.riphtix.vgmad.entity.exp.Experience;
import com.riphtix.vgmad.entity.spawner.ParticleSpawner;
import com.riphtix.vgmad.gfx.AnimatedSprite;
import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.gfx.Sprite;
import com.riphtix.vgmad.gfx.SpriteSheet;
import com.riphtix.vgmad.handler.Sound;
import com.riphtix.vgmad.level.Node;
import com.riphtix.vgmad.level.tile.hitbox.MobHitbox;
import com.riphtix.vgmad.util.Vector2i;

import java.util.List;

public class AStar extends Mob{

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.ghost_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.ghost_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.ghost_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.ghost_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private double xa = 0.0;
	private double ya = 0.0;
	private List<Node> path = null;
	private int time = 0;

	public MobHitbox hitbox;

	public AStar(int x, int y, int level) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = animSprite.getSprite();
		hitbox = new MobHitbox(Sprite.hitbox21x32);

		//Shooter default attributes
		health = 100;
		mana = 100;
		xpLevel = level;
		armor = 1.0;
		protectSpell = 1.0;
	}

	private void move() {
		xa = 0;
		ya = 0;
		int px = (int)level.getPlayerAt(0).getX();
		int py = (int)level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
		Vector2i goal = new Vector2i(px >> 4, py >> 4);
		if(time % 2 == 0) path = level.findPath(start, goal);
		if(path != null){
			if(path.size() > 0){
				Vector2i vec = path.get(path.size() - 1).tile;
				if(x < vec.getX() << 4) xa++;
				if(x > vec.getX() << 4) xa--;
				if(y < vec.getY() << 4) ya++;
				if(y > vec.getY() << 4) ya--;
			}
		}
		if (xa != 0 || ya != 0) {
			move(xa, 8, -10, ya, 0, 14);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void tick() {
		time++;
		move();
		if (walking) animSprite.tick();
		else animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Mob.Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Mob.Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Mob.Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Mob.Direction.RIGHT;
		}
	}

	public void aStarDamaged(double damage) {

		// can have a multiplier here to reduce health damage due to spells or armor
		health -= damage * armor * protectSpell;

		if (isDead()){
			Sound.SoundEffect.FEMALE_DEAD.play();
			level.getClientPlayer().xp += Experience.getXPGivenByMobAtLevel(this.xpLevel);
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level, 0xffc40000));
			remove();
		}
	}

	public boolean isDead(){
		return (health <= 0);
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
		hitbox.render((int) x - 10, (int) y - 16, screen);
	}
}
