package cardForNow;

import main.BackgroundPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.IOException;
import java.io.InputStream;

//talvez usar deltaTime para criar fpc e fazer animações
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
	static boolean jogoPronto = false;

	static int attempts = 0;

	// nem é nescessario, mas eu quero (^._.^)ﾉ
	int difficulty = 0;
	int maxDif = Card.getMaxCards() - 5; // maximum difficulty

	// quantidade de pares de cartas
	int qtdCards;

	// talvez?
	int columns;
	int rows = 2;

	// o timer para ver as cartas
	Timer paraEsconderCartas;

	// aiai
	JPanel boardPanel;
	JPanel textPanel = new JPanel();
	static JLabel textLabel = new JLabel();

	// custom font, mudar daqui?
	public Font fonte;

	public void carregarFonte() throws IOException, FontFormatException {
		InputStream is = getClass().getResourceAsStream("/fonts/Pixel Lofi.otf");
		fonte = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(18f);
	}

	
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

		gameCardSet();
	
		// hmmm
		//pack();

		paraEsconderCartas.start();

		setResizable(false);
		setVisible(true);
	}

	// provavelmente melhor em outro lugar.
	public void gameCardSet() {
		//mudar o layout para que as coisas fiquem no lugar certo
		gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		// preciso criar um baralho novo em toda partida nova?
		Deck cartasNaMesa = new Deck(difficulty);

		columns = cartasNaMesa.cartas.size() / rows;

		// criando a mesa e pondo as cartas nela como botões
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridBagLayout());
		boardPanel.setOpaque(false);

		//criando os parametros para o layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE; // não redimensiona o botão
		gbc.anchor = GridBagConstraints.CENTER; // centraliza o conteúdo
		gbc.insets = new Insets(5, 5, 5, 5); // espaçamento entre cartas

		for (int i = 0; i < cartasNaMesa.board.size(); i++) {
			int row = i / columns;
		    int col = i % columns;

		    gbc.gridx = col;
		    gbc.gridy = row;
		    
			boardPanel.add(cartasNaMesa.board.get(i), gbc);
		}

		// mudar daqui?
		/*
		 * função para carragar a fonte
		 * @trows IOException
		 * @trows FontFormatException
		 */
		try {
			carregarFonte();
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		// pondo as tentativas na tela
		textLabel.setFont(fonte);
		textLabel.setForeground(Color.WHITE);//mudando a cor da fonte
		textLabel.setHorizontalAlignment(JLabel.LEFT);
		textLabel.setText("Tentativas: " + Integer.toString(attempts));
		textLabel.setOpaque(false); // para ficar transparente;

		//pondo o texto na label de texto pra por na tela
		textPanel.add(textLabel);		
		textPanel.setOpaque(false);

		// pondo as coisas no jogo
		gamePanel.add(boardPanel);
		gamePanel.add(textPanel);
		
		//pndo o jogo na tela
		this.add(gamePanel);

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
