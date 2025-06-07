package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import main.GameFrame;
import ui.Button;
import util.BackgroundPanel;
import util.FontLoader;

/**
 * fazer aqui o menu pricipal, onde de começa o jogo novo (futuramente continuar um jogo antigo)
 * sair do jogo e ver placar de lideres e onde vai se escolher a dificuldade
 */
public class Menu {

	//por algum motivo os caminhos nao estoa funcionando corretamente
	public BackgroundPanel painel = new BackgroundPanel("/background/raw.png");
	
	private Font fonte = new FontLoader().fonte;

	private GridBagConstraints gbc = new GridBagConstraints();

	private Button start = new Button("scr/main/resources/ui/spr_banner_hud.png", "PLAY", 5, 30);
	private Button placar = new Button("scr/main/resources/ui/spr_banner_hud.png", "PLACAR", 5, 30);
	private Button sair = new Button("scr/main/resources/ui/spr_banner_hud.png", "QUIT",5, 30);

	//define a quantidade de cartas (fazer o usuario poder escolher)
	public static int difficulty = 0;
	
	public Menu(GameFrame frame, BackgroundPanel framePanel) {		
		painel.setLayout(new GridBagLayout());

		painel.setSize(1000, 600);
		
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		
		JLabel title = new JLabel("~~~ NECO ARC MEMORY ~~~");
		title.setFont(fonte.deriveFont(60f));
		title.setForeground(Color.WHITE);
		
		painel.add(title, gbc);

		setUpButton();
		
        JLabel descriptionLabel = new JLabel("Selecione a dificuldade: ");
        descriptionLabel.setFont(fonte);
        descriptionLabel.setForeground(Color.WHITE); //
		
		//cria o combobox para escolher a dificuldade
		Integer[] opcoes = {0, 1, 2, 3, 4};
		JComboBox<Integer> chooseDif = new JComboBox<>(opcoes);

		
		painel.add(descriptionLabel, gbc);
		
		chooseDif.setToolTipText("escolha a dificuldade!");// kkkkkkkkk
		painel.add(chooseDif, gbc);
		
		//insere o actionlistener para o Combobox de selecao de dificuldade
		chooseDif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//muda a dificuldade dependendo do selecionado
				difficulty = (int) chooseDif.getSelectedItem();
				}
		});
		
		
		//insere a action do botao de inicio
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.restartGame(difficulty, frame);
			}
		});
		
		//insere a action do botao do placar
		placar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.showPlacar(frame);
			}
		});
		
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.exit();
			}
		});
	}
	/**
	 * Função responsavel de por as botoes de seleção na tela
	 */
	private void setUpButton() {
		ArrayList<Button> menuUi = new ArrayList<>();

		//cada botao novo por aqui que sera criado automaticamente
		menuUi.add(start);
		menuUi.add(placar);
		menuUi.add(sair);

		//layout para os botoes
		gbc.gridx = 2;

		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		gbc.weightx = 0;
		gbc.weighty = 0;

		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.insets = new Insets(10, 10, 10, 10);

		for(Button x : menuUi) {
			painel.add(x,gbc);
		}
	}
}
