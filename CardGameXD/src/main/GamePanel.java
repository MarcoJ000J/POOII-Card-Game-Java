package main;

import javax.swing.JFrame;

import cardForNow.Deck;

public class GamePanel extends JFrame {
	
	
	int dificulty = 0;
	//quantidade de pares de cartas
	int qtdCards;
	
	public GamePanel() {
		setTitle("A INCRIVEL XD Card Game");
		setSize(1000,600);
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		setVisible(true);
	}
	
	
	public void gameSet() {
		
		// quantidade de cartas relativa a dificuldade selecionada
		if(dificulty == 0) {
			qtdCards = 5;
		}
		else if(dificulty == 1) {
			qtdCards = 7;
		}
		else if(dificulty == 2) {
			qtdCards = 9;
		}
		
		Deck cartasNaMesa = new Deck(qtdCards);
		
	}
}
