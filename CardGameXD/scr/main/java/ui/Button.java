package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import util.FontLoader;

public class Button extends JButton{

	Font fonte = new FontLoader().fonte;
	/**
	 * Construtor que cria um JButton com imagem e texto
	 */
	public Button(String image, String text) {
		setFont(fonte);
		setForeground(Color.WHITE);

		ImageIcon a = new ImageIcon(image);
		Image temp = new ImageIcon(image).getImage();

		//corrigir, se nao so serve pros botoes no menu
		ImageIcon icon = new ImageIcon(temp.getScaledInstance(a.getIconWidth(),
				a.getIconHeight(), java.awt.Image.SCALE_SMOOTH));

		setMinimumSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

		setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

		setPreferredSize(getSize());
		setIcon(icon);

		setText(text);
		setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.HORIZONTAL);

		//mudanças visuais no butão
		setBorderPainted(false);
		setContentAreaFilled(false);

		setVisible(true);
	}

	/**
	 * Construtor que cria um JButton com imagem e texto *E REESCALA* o botao
	 */
	public Button(String image, String text, int scaleWidth, int scaleHeight) {
		setFont(fonte);
		setForeground(Color.WHITE);

		ImageIcon a = new ImageIcon(image);
		Image temp = new ImageIcon(image).getImage();

		//corrigir, se nao so serve pros botoes no menu
		ImageIcon icon = new ImageIcon(temp.getScaledInstance(a.getIconWidth()+scaleWidth,
				a.getIconHeight()+scaleHeight, java.awt.Image.SCALE_SMOOTH));

		setMinimumSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

		setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

		setPreferredSize(getSize());
		setIcon(icon);

		setText(text);
		setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.HORIZONTAL);

		//mudanças visuais no butão
		setBorderPainted(false);
		setContentAreaFilled(false);

		setVisible(true);
	}

	/**
	 * Construtor que cria um botao com imagem
	 */
	public Button(String image) {
		setForeground(Color.WHITE);

		ImageIcon a = new ImageIcon(image);
		Image temp = new ImageIcon(image).getImage();

		ImageIcon icon = new ImageIcon(temp.getScaledInstance(a.getIconWidth(),
				a.getIconHeight(), java.awt.Image.SCALE_SMOOTH));

		setMinimumSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

		setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

		setPreferredSize(getSize());
		setIcon(icon);

		//mudanças visuais no butão
		setBorderPainted(false);
		setContentAreaFilled(false);
		setVisible(true);
	}
}
