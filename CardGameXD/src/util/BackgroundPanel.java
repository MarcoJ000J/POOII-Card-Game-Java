package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    /**
     * @since 17/05/2025
	 * A Class to generate the background for the Frames
	 */

	private Image backgroundImage;

	private int width = 1000;
	private int height = 600;

	/*
	 * Construtor que recebe o caminho da imagem de fundo
	 */
    public BackgroundPanel(String imagePath) {
    	setMinimumSize(new Dimension(width, height));

    	backgroundImage = new ImageIcon(imagePath).getImage();
    }

    public BackgroundPanel(String imagePath, int widthD, int heightD) {
    	setMinimumSize(new Dimension(widthD, heightD));

    	this.width = widthD;
    	this.height = heightD;

    	backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // nsei que isso sinceramente, ver....
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}