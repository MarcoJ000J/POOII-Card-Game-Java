package main;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import game.Card;
import game.CardPanel;
import game.Deck;
import menu.*;
import ui.RestartButton;
import util.BackgroundPanel;
import util.MusicPlayer;


/**
 * Classe do frame principal, tudo que aparece na tela é posto aqui.
 * @author Marco AFR.Jr.
 */
//talvez usar deltaTime para criar fpc e fazer animações
public class GameFrame extends JFrame {
	 // criando as variaveis, de maneira modular, a dificuldade, apesar de nao ter
	 // sido uma exigencia, é facil de aplicar e pode dar uma profundidade maior ao
	 // jogo caso eu queira termina-lo.

	public static volatile  boolean jogoTerminado = false;

	private BackgroundPanel tela = null;
	Menu mainMenu = null;
	Placar mainPlacar = null; 
	RestartButton restart = null;

	private int difficulty = 0;
	// maximum difficulty
	int maxDif = Card.getMaxCards() - 5;

	// quantidade de pares de cartas
	int qtdCards;

	//Verifica qando o jogo acaba
	private Timer endGameTimer;

	public static GridBagConstraints gbcM = new GridBagConstraints();

	public GameFrame() {
		//configuração da tela
		super("A INCRIVEL Card Game XD");
		setSize(1000, 600);

		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		setResizable(false);

		///configura o gridbagconstraints para o painel;
		gbcM.gridwidth = 2;
		gbcM.gridheight = 2;
		gbcM.weightx = 1.0;
		gbcM.weighty = 1.0;

		gbcM.fill = GridBagConstraints.BOTH;

		mainMenu = new Menu(this, tela);

		add(mainMenu.painel, gbcM);

		//Ao fechar encerra a thread que toca a musica
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	exit();
		    }
		});

		setVisible(true);
	}
	
	public void exit() {
        MusicPlayer.endBackgroundMusic();

        if(endGameTimer != null) {
        	endGameTimer.stop();
        }

        dispose(); // fecha a janela manualmente
        System.exit(0); // garante encerramento completo
	}

	/**
	 * Função responsavel por verificar a condição de fim de jogo.
	 */
	private void check(Container container, GameFrame frame) {
		//se ja tem um timer, para ele
	    if (endGameTimer != null && endGameTimer.isRunning()) {
	        endGameTimer.stop();
	    }

	    //cria o action listener do timer que realiza os endgame functions;
		endGameTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
						if(jogoTerminado) {
							//para que o painel do winmenu mao fique esticado
							CardPanel.gbc.fill = GridBagConstraints.CENTER;

							WinMenu inEnd = new WinMenu(frame);

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
	
	/**
	 * Função responsavel por trazer o frame de volta ao menu
	 * @see WinMenu
	 * (por enquanto a unica forma de voltar)
	 */
	public void backToMenu(GameFrame frame) {
		//para ter certeza que nao ha nada na tela
		if(tela != null) {remove(tela);}
		
		if(mainMenu != null) {mainMenu = null;}
		
		if(mainPlacar != null) {
			remove(mainPlacar.painel);
			mainPlacar = null;
		}
		
		//por algun motivo esse nao some
		if(restart != null) {remove(restart);}
		
		///configura o gridbagconstraints para o painel;
		gbcM.gridwidth = 2;
		gbcM.gridheight = 2;
		gbcM.weightx = 1.0;
		gbcM.weighty = 1.0;

		gbcM.fill = GridBagConstraints.BOTH;
		
		//reiniciando as variaveis
		jogoTerminado = false;
		Deck.viradas = 0;
		
		mainMenu = new Menu(frame, tela);
		
		frame.add(mainMenu.painel, gbcM);
		frame.repaint();
		frame.revalidate();
	}

	/**
	 * Inicia / Reinicia o jogo usado por
	 * @see Menu
	 */
	public void restartGame(int difficulty, GameFrame frame) {
		//remove "game" para nao ter infinitos paineis na tela;
		if(tela != null) {remove(tela);}

		if(mainMenu != null) {
			remove(mainMenu.painel);
			mainMenu = null;
		}
		
		//configura o gridbagconstraints para o botao e colocaele no FRAME
		// pra que nao fique torto :3
		GameFrame.gbcM.gridx = 0;
		GameFrame.gbcM.gridy = 0;
		GameFrame.gbcM.gridwidth = 1;
		GameFrame.gbcM.gridheight = 1;
		GameFrame.gbcM.weightx = 0;
		GameFrame.gbcM.weighty = 0;

		GameFrame.gbcM.anchor = GridBagConstraints.NORTHEAST;
		GameFrame.gbcM.fill = GridBagConstraints.NONE;
		restart = new RestartButton(frame);
		add(restart, GameFrame.gbcM);

		//reiniciando as variaveis
		jogoTerminado = false;
		Deck.viradas = 0;

		tela = new CardPanel(difficulty, frame).panel;

		check(tela, this);

		///configura o gridbagconstraints para o painel;
		gbcM.gridwidth = 2;
		gbcM.gridheight = 2;
		gbcM.weightx = 1.0;
		gbcM.weighty = 1.0;

		gbcM.fill = GridBagConstraints.BOTH;
		add(tela, gbcM);

		revalidate();
		repaint();
	}
	/**
	 * Função responsavel por levar o game frame ate o painelde placar,
	 *  tanto essa quanto as outras deveriam ter uma classe de controle responsavel
	 *  e nao estar tudo no frame mas...
	 */
	public void showPlacar(GameFrame frame) {
		//para ter certeza que nao ha nada na tela
		if(tela != null) {remove(tela);}
		
		if(mainMenu != null) {
			remove(mainMenu.painel);
			mainMenu = null;
		}
		//por algun motivo esse nao some
		if(CardPanel.restart != null) {remove(CardPanel.restart);}
		
		///configura o gridbagconstraints para o painel;
		gbcM.gridwidth = 2;
		gbcM.gridheight = 2;
		gbcM.weightx = 1.0;
		gbcM.weighty = 1.0;

		gbcM.fill = GridBagConstraints.BOTH;
		
		mainPlacar = new Placar(this);
		
		add(mainPlacar.painel, gbcM);
		
		revalidate();
		repaint();
	}
}
