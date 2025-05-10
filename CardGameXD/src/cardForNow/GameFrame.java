package cardForNow;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	/*
	 * criando as variaveis, de maneira modular, a dificuldade, apesar de nao ter sido uma exigencia, é facil de 
	 * aplicar e pode dar uma profundidade maior ao jogo caso eu queira termina-lo. 
	 * 
	 * */
	
	//tavez eu tire daqui
	boolean jogoTerminado;
	//nem é nescessario
	int difficulty = 0;
	int maxDif = Card.getMaxCards() - 5; // maximum difficulty 
	
	//quantidade de pares de cartas
	int qtdCards;
	
	//talvez?
	int columns;
	int rows = 2;
	
	public GameFrame(int setDifficulty) {
		//mudar nome
		setTitle("A INCRIVEL XD Card Game");
		setSize(1000,600);
		
		//tem que ser limitado a quantidade de cartas disponiveis, como fazer isso? qual erro por aqui?
		if(setDifficulty <= maxDif) {
			this.difficulty = setDifficulty;
		}
		
		gameCardSet();
		
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		setResizable(false);
		setVisible(true);
	}
	
	//provavelmente melhor em outro lugar.
	public void gameCardSet() {
		
		//preciso criar um baralho novo em toda partida nova?
		Deck cartasNaMesa = new Deck(difficulty);
				
		columns = cartasNaMesa.cartas.size()/rows;
	}
}
