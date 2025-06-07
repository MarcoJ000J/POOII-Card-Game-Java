package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.CardPanel;
import main.GameFrame;
import ui.Button;
import util.FontLoader;

/**
 * Cria um painel como um popup pra mostrar os pontos
 */
public class WinMenu extends JPanel{
	//tamnho da imagem
	int x = 256;

	Image backgroundImage;
	Font fonte = new FontLoader().fonte;


	public WinMenu(GameFrame frame) {
		setPreferredSize(new Dimension(x, x));

		setLayout(new BorderLayout());

		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());

		//exibe a pontuação
		JLabel pontuacao = new JLabel("Pontuação: " + Integer.toString(CardPanel.attempts));
		pontuacao.setOpaque(false);
		pontuacao.setForeground(Color.white);
		pontuacao.setFont(fonte);

		add(pontuacao, BorderLayout.NORTH);
		
		//pega o nome
		JTextField getNome = new JTextField(15);
		Button enviaNome = new Button("scr/main/resources/ui/sendButton2.png");
		
		enviaNome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardPanel.nome = getNome.getText();
				System.out.println(CardPanel.nome);
				frame.backToMenu(frame);
			}
		});
		
		JPanel paraPegarNome = new JPanel();
		
		paraPegarNome.add(getNome);
		paraPegarNome.add(enviaNome);
		
		paraPegarNome.setOpaque(false);
		
		add(paraPegarNome, BorderLayout.SOUTH);
		
		setOpaque(false);

		backgroundImage = new ImageIcon(getClass().getResource("/win.gif")).getImage();
		setVisible(true);
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // nsei que isso sinceramente, ver....
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}
