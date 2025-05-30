package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.GameFrame;

public class RestartButton extends JButton{
	
	public RestartButton(GameFrame frame) {
		setPreferredSize(new Dimension(20, 20));
		
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.restartGame();
			}
		});
		
		setVisible(true);
	}

}
