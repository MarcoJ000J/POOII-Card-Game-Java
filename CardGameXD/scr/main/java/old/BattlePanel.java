package old;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.BackgroundPanel;

public class BattlePanel{

	Entity mainCaracter;
	Entity enemy;

	JPanel doEnemy = new JPanel();
	JPanel main = new JPanel();

	JButton atk, def, skill;

	ArrayList<JButton> acoes = new ArrayList<>();

	// setando o background
	BackgroundPanel panel; //= new BackgroundPanel("res\\background\\slaFundo.jpg");

	public BattlePanel(int nivel, String enemy) {
		//mudar o layout para que as coisas fiquem no lugar certo
		panel.setLayout(new GridBagLayout());


		this.mainCaracter = new Entity("Kim Dojka", nivel);
		this.enemy = new Entity(enemy);

		main.setLayout(new GridBagLayout());
		//faz ficar transaparente...sera uma boa?
		main.setOpaque(false);

		//criando os parametros para o layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL; // não redimensiona o botão
		gbc.anchor = GridBagConstraints.CENTER; // centraliza o conteúdo
		gbc.insets = new Insets(5, 5, 5, 5); // espaçamento entre cartas

		// tem que ter um jeito melhor de fazer isso :(
		atk = new JButton("ATK");
		atk.setPreferredSize(new Dimension(100,60));
		atk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//fazer
			}
		});
		acoes.add(atk);

		def = new JButton("DEF");
		def.setPreferredSize(new Dimension(100,60));
		def.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		acoes.add(def);

		skill = new JButton("SKILL");
		skill.setPreferredSize(new Dimension(100,60));
		skill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		acoes.add(skill);

		 //gbc.gridy = 0;
		for (JButton element : acoes) {
			 //gbc.gridx = i;
			 //gbc.gridy = i;
			 main.add(element, gbc);
		}

		panel.add(main);
	}

	public BackgroundPanel getPanel() {
		return panel;
	}
}
