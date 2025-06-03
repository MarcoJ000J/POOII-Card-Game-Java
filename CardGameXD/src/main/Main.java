package main;

import util.MusicPlayer;

public class Main {

	public static void main(String[] args){
		/*
		 * temporario enquanto não ha um menu para começar o jogo
		 */
		new GameFrame(0);// 4 é o maximo				
		new MusicPlayer().startBackgroundMusic();
	}
}
