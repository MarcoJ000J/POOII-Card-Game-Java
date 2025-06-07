package ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.GameFrame;
import menu.Menu;

/**
 * botao para restart (devia usar Button seria melhor)
 * @see  CardPanel
 */
public class RestartButton extends Button{
	public RestartButton(GameFrame frame) {
		super("scr/main/resources/ui/saveButton.png");
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.restartGame(Menu.difficulty, frame);
			}
		});

		setVisible(true);
	}

}
