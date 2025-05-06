package cardForNow;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {
	//valores temporarios necessario mundanças
	int cardWidth = 90;
	int cardHeight = 128;
	
	// just add here 
	final static String[] cards = {"carta1", "carta2", "carta3", "carta4", "carta5", "carta6", "carta7"
			, "carta8", "carta9"};
	/** 
	 *  :-)
	 */
	
	public final static int maxCards = cards.length;
	
	//valor para comparações das cartas
	public final String nomeCarta;
	
	//mudat para imageIcon :(
	private final ImageIcon atrasCarta;
	private final ImageIcon frenteCarta;
	
	private boolean isUp = false;
	//public String upFace;
	
	public Card(int frente) {
		this.nomeCarta = cards[frente];
		
		//para poder escalonar pro tamanho que eu quiser :)
		Image tempCardImag = new ImageIcon(cards[frente] + ".png").getImage();
		
		this.frenteCarta = new ImageIcon(tempCardImag.getScaledInstance(cardWidth, cardHeight, java.awt.Image.SCALE_SMOOTH));
		this.atrasCarta = new ImageIcon("res/" + "backTemp" + ".png"); // nome do arquivo
		
	}
	
	public String getCardId() {
		return this.nomeCarta;
	}
	
	public static int getMaxCards() {
		return maxCards;
	}

	public ImageIcon getUpFace() {
		if (isUp == false) {
			return this.atrasCarta;
		}else {
			return this.frenteCarta;
		}
	}
}
