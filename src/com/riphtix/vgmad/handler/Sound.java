package com.riphtix.vgmad.handler;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Sound extends Thread {
	public enum SoundEffect {
		LAUNCH_FIREBALL("/sounds/fireball.wav"),
		PLAYER_DEAD("/sounds/death.wav"),
		FEMALE_DEAD("/sounds/girlDeath.wav"),
		LIFE_LOST("/sounds/lifeMinusOne.wav"),
		FOOTSTEP_1("/sounds/footstep1.wav"),
		FOOTSTEP_2("/sounds/footstep2.wav"),
		FOOTSTEP_3("/sounds/footstep3.wav"),
		FEMALE_DAMAGE_9("/sounds/girlDamage9.wav"),
		MALE_HIT("/sounds/manHit.wav"),
		COLLECT_ITEM_POP("/sounds/itemPop.wav");

		public enum Volume{
			MUTE, LOW, MEDIUM, HIGH
		}

		public Volume volume = Volume.LOW;

		private Clip clip;

		SoundEffect(String soundFileName){
			try{
				URL url = this.getClass().getResource(soundFileName);
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}

		public void play(){
			FloatControl soundLevel = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			if(volume != Volume.MUTE){
				if(clip.isRunning())
					clip.stop();
				clip.setFramePosition(0);
				clip.start();
			} if(volume == Volume.LOW){
				soundLevel.setValue(-10.0f);
			}if(volume == Volume.MEDIUM){
				soundLevel.setValue(0f);
			}if(volume == Volume.HIGH){
				soundLevel.setValue(10.0f);
			}
		}

		public static void init(){
			values();
		}
	}
}
