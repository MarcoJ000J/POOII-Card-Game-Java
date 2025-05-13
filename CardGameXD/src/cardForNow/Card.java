package cardForNow;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {
	// valores temporarios necessario mundanças
	public int cardWidth = 90;
	public int cardHeight = 128;

	// just add here
	// mudar para array?
	final static String[] cards = { "carta1", "carta2", "carta3", "carta4", "carta5", "carta6", "carta7"
			, "carta8", "carta9" };
	
	public final static  int maxCards = cards.length;

	// valor para comparações das cartas
	public final String nomeCarta;

	// mudat para imageIcon :(
	private final ImageIcon atrasCarta;
	private final ImageIcon frenteCarta;

	/*
	 * Se for TRUE a parte vista é a frente da carta se FALSE a parte e tras
	 * */
	public boolean isUp = true;

	/*
	 * Construtor da carta
	 */
	public Card(int frente) {		
		this.nomeCarta = cards[frente];

		// para poder escalonar pro tamanho que eu quiser :)
		//getClass().getResourse(String) é necessario?
		Image tempCardImag = new ImageIcon("res\\" +  cards[frente] + ".jpg").getImage();

		this.frenteCarta = new ImageIcon(
				tempCardImag.getScaledInstance(cardWidth, cardHeight, java.awt.Image.SCALE_SMOOTH));
		
		/* utilizado para setar a parte de tras da carta*/
		//no futuro mudar para poder ser alterado pelo jogador(no menu?)
		Image tempBackImage = new ImageIcon("res\\" + "back1" + ".png").getImage();
		
		this.atrasCarta = new ImageIcon(
				tempBackImage.getScaledInstance(cardWidth, cardHeight, java.awt.Image.SCALE_SMOOTH)); // nome do arquivo

	}

	// necessario?
	public String getCardId() {
		return this.nomeCarta;
	}

	public static  int getMaxCards() {
		return maxCards;
	}

	/*Responsavel por retornar a parte da carta que esta virada para cima dependendo da variavel "isUp"*/
	public ImageIcon getUpFace() {
		if (isUp == false) {
			return this.atrasCarta;
		} else {
			return this.frenteCarta;
		}
	}
}
