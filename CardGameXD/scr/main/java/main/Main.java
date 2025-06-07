package main;

import java.io.IOException;

import util.MusicPlayer;

public class Main {

	public static void main(String[] args) throws IOException{
		/*
		 * temporario ?
		 */
		new GameFrame();
		new MusicPlayer().startBackgroundMusic();
	}
}
