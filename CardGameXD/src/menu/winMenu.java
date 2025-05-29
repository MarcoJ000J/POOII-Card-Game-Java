package menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class winMenu extends JPanel{
	int x = 256;
	
	Image backgroundImage;
	
	public winMenu() {
		setMinimumSize(new Dimension(x, x));
		backgroundImage = new ImageIcon("res\\win.gif").getImage();
		setVisible(false);
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // nsei que isso sinceramente, ver....
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
	
}
