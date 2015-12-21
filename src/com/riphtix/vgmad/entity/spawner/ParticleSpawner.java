package com.riphtix.vgmad.entity.spawner;

import com.riphtix.vgmad.entity.particle.BloodParticle;
import com.riphtix.vgmad.entity.particle.Particle;
import com.riphtix.vgmad.level.Level;

public class ParticleSpawner extends Spawner {

	private int life;

	public ParticleSpawner(int x, int y, int life, int amount, Level level, int color) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			level.add(new BloodParticle(x, y, life, color));
			remove();
		}
	}

	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life));
			remove();
		}
	}
}
