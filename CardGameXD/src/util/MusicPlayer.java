package util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class MusicPlayer {
	public static Clip background = null;
	
	public void startBackgroundMusic(){
		File caminho = new File("res\\musicFiles\\Origins.wav");
		AudioInputStream audio = null;
		
		try {
			audio =  AudioSystem.getAudioInputStream(caminho);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			background = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			background.open(audio);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		background.loop(Clip.LOOP_CONTINUOUSLY);
		
		background.start();
	}
	
	public static void endBackground() {
		background.stop();
		background.close();
	}
}
