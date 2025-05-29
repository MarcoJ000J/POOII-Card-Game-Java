package main;

import util.BackgroundPanel;

import javax.swing.JFrame;

import game.BattlePanel;
import game.Card;
import game.CardPanel;
import menu.winMenu;

//talvez usar deltaTime para criar fpc e fazer animações
//sera que deixo aqui?
public class GameFrame extends JFrame {

	/*
	 * criando as variaveis, de maneira modular, a dificuldade, apesar de nao ter
	 * sido uma exigencia, é facil de aplicar e pode dar uma profundidade maior ao
	 * jogo caso eu queira termina-lo.
	 * 
	 * @author Marco AFR.Jr.
	 */

	// tavez eu tire daqui
	boolean jogoTerminado;
	
	// nem é nescessario, mas eu quero (^._.^)ﾉ
	int difficulty = 0;
	int maxDif = Card.getMaxCards() - 5; // maximum difficulty

	// quantidade de pares de cartas
	int qtdCards;
	
	//o painel com o background, principal, todo o resto vai nele
	BackgroundPanel gamePanel;
	
	public GameFrame(int setDifficulty) {
		// mudar nome
		setTitle("A INCRIVEL XD Card Game");
		setSize(1000, 600);

		//instancia com o vaminho para a imagem de background
		gamePanel = new BackgroundPanel("res\\background\\slaFundo.jpg");
		
		// tem que ser limitado a quantidade de cartas disponiveis, como fazer isso?
		// qual erro por aqui?
		if (setDifficulty <= maxDif) {
			this.difficulty = setDifficulty;
		}
				
		setLocationRelativeTo(null);
		
		add(new CardPanel(difficulty).getPanel());
		
		setResizable(false);
		setVisible(true);
	}

}
