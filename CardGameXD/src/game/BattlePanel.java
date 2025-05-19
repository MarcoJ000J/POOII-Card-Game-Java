package game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.BackgroundPanel;

public class BattlePanel{
	
	Entity mainCaracter;
	Entity enemy;

	JPanel doEnemy = new JPanel();
	JPanel main = new JPanel();
	
	int i;
	
	// setando o background
	BackgroundPanel panel = new BackgroundPanel("res\\background\\slaFundo.jpg");
	
	public BattlePanel(int nivel, String enemy) {
		i = 0;
		
		this.mainCaracter = new Entity("Kim Dojka", nivel);
		this.enemy = new Entity(enemy);
		
		main.setLayout(new GridBagLayout());
		
		//criando os parametros para o layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE; // não redimensiona o botão
		gbc.anchor = GridBagConstraints.CENTER; // centraliza o conteúdo
		gbc.insets = new Insets(5, 5, 5, 5); // espaçamento entre cartas

		
		JButton atk = new JButton("Atk");
		
		atk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//fazer
			}
		});
		
	    gbc.gridx = i;
	    gbc.gridy = i;
	    i++;
		main.add(atk, gbc);
		
				
	}
}
