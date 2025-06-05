package ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.GameFrame;

/**
 * botao para restart (devia usar Button seria melhor)
 * @see  CardPanel
 */
public class RestartButton extends JButton{

	public RestartButton(GameFrame frame, int difficulty) {

		setPreferredSize(new Dimension(50, 50));

		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());

		// o nome da imagem esta errado kkkkkk
		Image temp = new ImageIcon("scr/main/resources/ui/saveButton.png").getImage();

		setIcon(new ImageIcon(temp));

		//mudanças visuais no butão
		setBorderPainted(false);
		setContentAreaFilled(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.restartGame(difficulty, frame);
			}
		});

		setVisible(true);
	}

}
