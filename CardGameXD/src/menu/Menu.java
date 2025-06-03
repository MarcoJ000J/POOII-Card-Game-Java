package menu;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import main.GameFrame;
import ui.Button;
import util.BackgroundPanel;
import util.FontLoader;

/* 
 * fazer aqui o menu pricipal, onde de começa o jogo novo (futuramente continuar um jogo antigo)
 * sair do jogo e ver placar de lideres e onde vai se escolher a dificuldade 
 * */
public class Menu {

	public BackgroundPanel painel = new BackgroundPanel("res\\backGround\\sla2.jpg");

	private Font fonte = new FontLoader().fonte;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private Button start = new Button("res/ui/spr_banner_hud.png", "PLAY");
	private Button placar = new Button("res/ui/spr_banner_hud.png", "PLACAR");
	private Button sair = new Button("res/ui/spr_banner_hud.png", "SAIR");
	
	public Menu(GameFrame frame, BackgroundPanel framePanel) {
		painel.setLayout(new GridBagLayout());
		
		painel.setSize(1000, 600);
		
		setUpButton();
		
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.restartGame();
			}
		});
	}
	
	private void setUpButton() {
		ArrayList<Button> menuUi = new ArrayList<Button>();
				
		menuUi.add(start);
		menuUi.add(placar);	
		menuUi.add(sair);
		
		gbc.gridx = 2;
		
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST; 
		
		gbc.insets = new Insets(10, 10, 10, 10);
		
		for(Button x : menuUi) {
			painel.add(x,gbc);
		}
	}
}
