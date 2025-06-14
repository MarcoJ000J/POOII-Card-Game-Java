package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.google.gson.annotations.SerializedName;

import main.GameFrame;
import ui.RestartButton;
import util.BackgroundPanel;
import util.FontLoader;

/**
 * Classe do painel onde o  jogo acontece!
 */
public class CardPanel{
	//responsavel por liberar as cartas para clicar
	static boolean jogoPronto = false;

	public BackgroundPanel panel = new BackgroundPanel("/background/slaFundo.jpg");

	//cria a fonte que sera usada @see FontLoader
	Font fonte = new FontLoader().fonte;

	// cria o tabuleiro, o painel de texto e o texto que sera inserido
	JPanel boardPanel;
	JPanel textPanel = new JPanel();
	static JLabel textLabel = new JLabel();

	// define a forma do tabuleiro
	int columns;
	int rows = 2;
	static int difficulty;


	//tentativas e acertos.
	public static int attempts;
	public static int hits;
	
	@SerializedName("Nome")
	public static String nome;
	
	@SerializedName("Pontos")
	public static int points;

	/**
	 * Timer que esconde as cartas no inicio do jogo
	 */
	Timer paraEsconderCartas;
	public static RestartButton restart;

	public static GridBagConstraints gbc = new GridBagConstraints();

	public CardPanel(int difficulty, GameFrame frame) {
		//setup the variables
		attempts = 0;
		hits = 0;
		points = 0;
		this.difficulty = difficulty;

		gameCardSet();

		paraEsconderCartas.start();
	}

	//criar uma classe
	/**
	 * Função responsavel por criar e configurar o baralho e os botoes na tela
	 * @see Deck
	 * Alem de por o texto
	 * @see upDateTextLabel()
	 */
	public void gameCardSet() {
		panel.setLayout(new GridBagLayout());

		// cria um baralho novo para cada partida
		Deck cartasNaMesa = new Deck(difficulty);

		columns = cartasNaMesa.cartas.size() / rows;

		// criando a mesa e pondo as cartas nela como botões
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridBagLayout());
		boardPanel.setOpaque(false);

		//criando os parametros para o layout
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
		textLabel.setHorizontalAlignment(SwingConstants.LEFT);
		upDateTextLabel();
		textLabel.setOpaque(false); // para ficar transparente;

		//pondo o texto na label de texto pra por na tela
		textPanel.add(textLabel);
		textPanel.setOpaque(false);

		// pondo o tabuleiro e o texto no painel do jogo (que entao vai ser adcionado ao
		//   gameFrame)
		panel.add(boardPanel, gbc);
		panel.add(textPanel, gbc);

		/**
		 * Inicializa o timer que esconde as cartas apos 1.5 segundos
		 * @see paraEsconderCartas
		 */
		paraEsconderCartas = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartasNaMesa.hideAllCards();
				jogoPronto = true;
			}
		});
		paraEsconderCartas.setRepeats(false);
	}
	/**
	 * Função responsavel por atualizar o texto na tela;
	 */
	public static void upDateTextLabel() {
		calcPoints();
		textLabel.setText("Tentativas: " + Integer.toString(attempts)
		+ " Acertos: " + Integer.toString(points));
	}
	/**
	 * Calcula od pontos
	 */
	public static void calcPoints() {
		points = ((500 + difficulty) * (hits)) - (attempts * 25);
	}
	
}
