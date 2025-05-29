package game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.BackgroundPanel;
import util.FontLoader;

public class CardPanel{
	static boolean jogoPronto = false;

	BackgroundPanel panel = new BackgroundPanel("res\\background\\slaFundo.jpg");
	
	/*
	 *cria a fonte que sera usada @see FontLoader 
	 */
	Font fonte = new FontLoader().fonte;
	
	
	// aiai
	JPanel boardPanel;
	JPanel textPanel = new JPanel();
	static JLabel textLabel = new JLabel();
	
	// talvez?
	int columns;
	int rows = 2;
	int difficulty;
	
	static int attempts = 0;
	static int acertos = 0;

	// o timer para ver as cartas
	Timer paraEsconderCartas;
	
	public CardPanel(int difficulty) {
		attempts = 0;
		this.difficulty = difficulty;
		
		gameCardSet();
		
		paraEsconderCartas.start();
	}
	
	public BackgroundPanel getPanel() {
		return panel;
	}
	
	// provavelmente melhor em outro lugar.
	//criar uma classe
	public void gameCardSet() {
		//mudar os grid bag components... kkkkkk oq eu fiz?
		panel.setLayout(new GridBagLayout());
		
		// preciso criar um baralho novo em toda partida nova?
		Deck cartasNaMesa = new Deck(difficulty);

		columns = cartasNaMesa.cartas.size() / rows;

		// criando a mesa e pondo as cartas nela como botões
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridBagLayout());
		boardPanel.setOpaque(false);

		//criando os parametros para o layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL; // não redimensiona o botão
		gbc.anchor = GridBagConstraints.CENTER; // centraliza o conteúdo
		gbc.insets = new Insets(15, 5, 15, 5); // espaçamento entre cartas

		for (int i = 0; i < cartasNaMesa.board.size(); i++) {
			int row = i / columns;
		    int col = i % columns;

		    gbc.gridx = col;
		    gbc.gridy = row;
		    
			boardPanel.add(cartasNaMesa.board.get(i), gbc);
		}
		
		// pondo as tentativas na tela
		textLabel.setFont(fonte);
		textLabel.setForeground(Color.WHITE);//mudando a cor da fonte
		textLabel.setHorizontalAlignment(JLabel.LEFT);
		textLabel.setText("Tentativas: " + Integer.toString(attempts) + " Acertos: " 
				+ Integer.toString(acertos));
		textLabel.setOpaque(false); // para ficar transparente;

		//pondo o texto na label de texto pra por na tela
		textPanel.add(textLabel);		
		textPanel.setOpaque(false);

		// pondo as coisas no jogo... kkkkkkkkkkkkkkkkk oq eu fiz senhor?
		panel.add(boardPanel, gbc);
		panel.add(textPanel, gbc);

		paraEsconderCartas = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartasNaMesa.hideAllCards();
				jogoPronto = true;
			}
		});
		
		paraEsconderCartas.setRepeats(false);	
	}
}
