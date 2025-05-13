package cardForNow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.IOException;
import java.io.InputStream;

public class GameFrame extends JFrame {
	
	/*
	 * criando as variaveis, de maneira modular, a dificuldade, apesar de nao ter sido uma exigencia, é facil de 
	 * aplicar e pode dar uma profundidade maior ao jogo caso eu queira termina-lo. 
	 * 
	 * */
	
	//tavez eu tire daqui
	boolean jogoTerminado;
	boolean jogoPronto;
	
	int attempts = 0;
	
	//nem é nescessario, mas eu quero (^._.^)ﾉ	
	int difficulty = 0;
	int maxDif = Card.getMaxCards() - 5; // maximum difficulty 
	
	//quantidade de pares de cartas
	int qtdCards;
	
	//talvez?
	int columns;
	int rows = 2;
	
	// o timer para ver as cartas
	Timer paraEsconderCartas;
	
	//aiai
	JPanel boardPanel;
	JPanel textPanel = new JPanel();
	JLabel textLabel = new JLabel();
	
	//custom font, mudar daqui?
	public Font fonte;
	public void carregarFonte() throws IOException, FontFormatException {
	    InputStream is = getClass().getResourceAsStream("/fonts/Pixel Lofi.otf");
	    fonte = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(18f);
	}
	   
	public GameFrame(int setDifficulty) {
		//mudar nome
		setTitle("A INCRIVEL XD Card Game");
		setSize(1000,600);
		
		//tem que ser limitado a quantidade de cartas disponiveis, como fazer isso? qual erro por aqui?
		if(setDifficulty <= maxDif) {
			this.difficulty = setDifficulty;
		}
		
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
				
		gameCardSet();
		
		//hmmm
		pack();
		
		paraEsconderCartas.start();
		
		setResizable(false);
		setVisible(true);
	}
	
	//provavelmente melhor em outro lugar.
	public void gameCardSet() {
		
		//preciso criar um baralho novo em toda partida nova?
		Deck cartasNaMesa = new Deck(difficulty);
		
		columns = cartasNaMesa.cartas.size()/rows;
		
		//criando a mesa e pondo as cartas nela como botões
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(rows, columns));
		distribuirCartas(cartasNaMesa);

		boardPanel.setMaximumSize(new Dimension(cartasNaMesa.cartas.get(0).cardWidth * 2
				, cartasNaMesa.cartas.get(0).cardHeight * 5));
		
		// mudar daqui? 
		try {
		    carregarFonte();
		} catch (IOException | FontFormatException e) {
		    e.printStackTrace();
		}
		
		//pondo as tentativas na tela
		textLabel.setFont(fonte);
		textLabel.setHorizontalAlignment(JLabel.LEFT);
		textLabel.setText("Tentativas: " + Integer.toString(attempts));	
		//porque não fica transparente?
		textLabel.setOpaque(true);
		
		
		//pondo as coisas na tela
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(textLabel, BorderLayout.EAST);
		
		paraEsconderCartas = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartasNaMesa.hideAllCards();
			}
		});
		paraEsconderCartas.setRepeats(false);
		jogoPronto = true;
		
		distribuirCartas(cartasNaMesa);
	}
	
	/*
	 * Usado para por os botões no painel, e nada mais, só para facilitar
	 * */
	private void distribuirCartas(Deck x) {
		for(int i = 0; i < x.board.size(); i++) {
			boardPanel.add(x.board.get(i));
		}
	}
}
