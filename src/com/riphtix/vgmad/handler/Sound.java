package com.riphtix.vgmad.handler;

import javax.sound.sampled.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {
	public static void playClip(File clipFile) throws IOException,
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
	}
}
