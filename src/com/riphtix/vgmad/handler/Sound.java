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
		WALKING("/sounds/woodfootstepEdited.wav"),
		FEMALE_DAMAGE_9("/sounds/girlDamage9.wav"),
		PLAYER_HIT("/sounds/manHit.wav");

		public static enum Volume{
			MUTE, LOW, MEDIUM, HIGH
		}

		public static Volume volume = Volume.LOW;

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
			if(volume != Volume.MUTE){
				if(clip.isRunning())
					clip.stop();
				clip.setFramePosition(0);
				clip.start();
			}
		}

		public static void init(){
			values();
		}
	}

	/*public static void playClip(File clipFile) throws IOException,
			UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		AudioClip clip = Applet.newAudioClip(new URL("file:/E:/Coding/Workspace/VideoGameMarketingAndDesignFinal/res" + clipFile));
		clip.play();
	}

	public static void play(String path){
		try {
			playClip(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
}
