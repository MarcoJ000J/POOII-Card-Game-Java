package main;

import util.BackgroundPanel;
import util.MusicPlayer;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import game.Card;
import game.CardPanel;
import game.Deck;
import menu.WinMenu;
import ui.RestartButton;

//talvez usar deltaTime para criar fpc e fazer animações
public class GameFrame extends JFrame {
	/*
	 * criando as variaveis, de maneira modular, a dificuldade, apesar de nao ter
	 * sido uma exigencia, é facil de aplicar e pode dar uma profundidade maior ao
	 * jogo caso eu queira termina-lo.
	 * 
	 * @author Marco AFR.Jr.
	 */

	public static volatile  boolean jogoTerminado = false;
	
	private BackgroundPanel game = null;

	int difficulty = 0;
	// maximum difficulty
	int maxDif = Card.getMaxCards() - 5; 

	// quantidade de pares de cartas
	int qtdCards;
	
	//Verifica qando o jogo acaba
	private Timer endGameTimer;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	public GameFrame(int setDifficulty) {
		setTitle("A INCRIVEL Card Game XD");
		setSize(1000, 600);
		
		setLayout(new GridBagLayout());
		
		// tem que ser limitado a quantidade de cartas disponiveis, como fazer isso?
		// qual erro por aqui?
		if (setDifficulty <= maxDif) {
			this.difficulty = setDifficulty;
		}
				
		setLocationRelativeTo(null);
		
		restartGame();
		
		//senceramente viu.....
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.fill = GridBagConstraints.NONE;

		RestartButton restart = new RestartButton(this);
		add(restart, gbc);
				
		setResizable(false);
		setVisible(true);
		
		//Ao fechar encerra a thread que toca a musica
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        MusicPlayer.endBackgroundMusic();
		        
		        endGameTimer.stop();
		        
		        dispose(); // fecha a janela manualmente
		        System.exit(0); // garante encerramento completo
		    }
		});
	}
	
	private void check(Container container) {	
		//se ja tem um timer, para ele
	    if (endGameTimer != null && endGameTimer.isRunning()) {
	        endGameTimer.stop();
	    }
		
		endGameTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
						if(jogoTerminado) {			
							//para que o painel do winmenu mao fique esticado
							CardPanel.gbc.fill = GridBagConstraints.CENTER;
													
							WinMenu inEnd = new WinMenu();
							
							System.out.println("hahahah");
							
							container.add(inEnd, CardPanel.gbc);
							
							container.setComponentZOrder(inEnd, 0);  // 0 = topo
							
							container.revalidate();
							container.repaint();
							((Timer) arg0.getSource()).stop();
						}		
			}
		});
		
		endGameTimer.start();
	}
	
	/*
	 * Inicia / Reinicia o jogo
	 */
	public void restartGame() {
		if(game != null) {remove(game);}

		jogoTerminado = false;
		Deck.viradas = 0;
				
		game = new CardPanel(difficulty).getPanel();
		
		check(game);
		//endGameTimer.restart();		
		
		///nossssaaaa
		gbc.gridwidth = 2;  
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		gbc.fill = GridBagConstraints.BOTH;
		add(game, gbc);
		
		revalidate();
		repaint();
	}
}
