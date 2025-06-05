package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.CardPanel;
import util.FontLoader;

public class WinMenu extends JPanel{
	int x = 256;

	Image backgroundImage;
	Font fonte = new FontLoader().fonte;


	public WinMenu() {
		setPreferredSize(new Dimension(x, x));

		setLayout(new BorderLayout());

		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());

		JLabel pontuacao = new JLabel("Pontuação: " + Integer.toString(CardPanel.attempts));
		pontuacao.setOpaque(false);
		pontuacao.setForeground(Color.white);
		pontuacao.setFont(fonte);

		add(pontuacao, BorderLayout.SOUTH);

		setOpaque(false);

		backgroundImage = new ImageIcon("scr/main/resources/win.gif").getImage();
		setVisible(true);
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // nsei que isso sinceramente, ver....
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}
