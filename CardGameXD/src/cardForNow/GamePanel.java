package cardForNow;

import javax.swing.JFrame;

public class GamePanel extends JFrame {
	
	/*
	 * criando as variaveis, de maneira modular, a dificuldade, apesar de nao ter sido uma exigencia, é facil de 
	 * aplicar e pode dar uma profundidade maior ao jogo caso eu queira termina-lo. 
	 * 
	 * */
	//tavez eu tire daqui
	boolean jogoTerminado;
	//nem é nescessario
	int difficulty = 0;
	
	//impedir algum erro
	int maxDif = Card.getMaxCards() - 5; // maximum difficulty 
	
	//quantidade de pares de cartas
	int qtdCards; // int max = Card.Cards.cratas.length;
	
	public GamePanel(int difficulty) {
		setTitle("A INCRIVEL XD Card Game");
		setSize(1000,600);
		
		this.difficulty = difficulty;
		gameSet();
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		setVisible(true);
	}
	
	//provavelmente melhor em outro lugar.
	public void gameSet() {
		
		
		
		//preciso criar um baralho novo em toda partida nova?
		Deck cartasNaMesa = new Deck(difficulty);
		
		
		
	}
}
