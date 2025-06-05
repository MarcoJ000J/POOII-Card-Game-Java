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
import menu.Menu;
import menu.WinMenu;
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

	int difficulty = 0;
	// maximum difficulty
	int maxDif = Card.getMaxCards() - 5;

	// quantidade de pares de cartas
	int qtdCards;

	//Verifica qando o jogo acaba
	private Timer endGameTimer;

	public static GridBagConstraints gbcM = new GridBagConstraints();

	public GameFrame(int setDifficulty) {
		//configuração da tela
		super("A INCRIVEL Card Game XD");
		setSize(1000, 600);

		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		setResizable(false);

		//verifica se a dificuldade inserida é valida;
		//mudar daqui
		if (setDifficulty <= maxDif) {
			this.difficulty = setDifficulty;
		}

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
		        MusicPlayer.endBackgroundMusic();

		        if(endGameTimer != null) {
		        	endGameTimer.stop();
		        }

		        dispose(); // fecha a janela manualmente
		        System.exit(0); // garante encerramento completo
		    }
		});

		setVisible(true);
	}

	/**
	 * Função responsavel por verificar a condição de fim de jogo.
	 */
	private void check(Container container) {
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

							WinMenu inEnd = new WinMenu();

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
	 * Inicia / Reinicia o jogo usado por
	 * @see Menu
	 */
	public void restartGame() {
		//remove "game" para nao ter infinitos paineis na tela;
		if(tela != null) {remove(tela);}

		if(mainMenu != null) {
			remove(mainMenu.painel);
			mainMenu = null;
		}

		//reiniciando as variaveis
		jogoTerminado = false;
		Deck.viradas = 0;

		tela = new CardPanel(difficulty, this).panel;

		check(tela);

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
}
