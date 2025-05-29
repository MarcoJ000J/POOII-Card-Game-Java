package game;

import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * @author Marco AFR.JR.
 * The class of the cards*/
public class Card {
	// valores temporarios necessario mundanças
	public int cardWidth = 90;
	public int cardHeight = 128;

	// just add here
	// mudar para array?
	private final static String[] cards = { "frontTemp", "frontTemp2", "frontTemp3", "frontTemp4", "frontTemp5",
			"frontTemp6", "frontTemp7", "frontTemp8", "frontTemp9" };

	public final static int maxCards = cards.length;

	public String nomeCarta;

	public final ImageIcon atrasCarta;
	public final ImageIcon frenteCarta;

	/*
	 * Se for TRUE a parte vista é a frente da carta se FALSE a parte e tras
	 */
	public boolean isUp = true;

	/*
	 * Construtor da carta
	 * 
	 * @param int frente (index)
	 */
	public Card(int frente) {
		this.nomeCarta = cards[frente];

		// para poder escalonar pro tamanho que eu quiser :)
		// getClass().getResourse(String) é necessario?
		Image tempCardImag = new ImageIcon("res\\frontCards\\" + cards[frente] + ".png").getImage();

		this.frenteCarta = new ImageIcon(
				tempCardImag.getScaledInstance(cardWidth, cardHeight, java.awt.Image.SCALE_SMOOTH));

		/* utilizado para setar a parte de tras da carta */
		// no futuro mudar para poder ser alterado pelo jogador(no menu?)
		Image tempBackImage = new ImageIcon("res\\backCards\\" + "back1" + ".png").getImage();

		// nome do arquivo
		this.atrasCarta = new ImageIcon(
				tempBackImage.getScaledInstance(cardWidth, cardHeight, java.awt.Image.SCALE_SMOOTH));

	}

	// necessario?
	public String getCardId() {
		return this.nomeCarta;
	}

	public static int getMaxCards() {
		return maxCards;
	}

	/*
	 * Responsavel por retornar a parte da carta que esta virada para cima
	 * dependendo da variavel "isUp" it depends of @see isUp;
	 * 
	 * @return ImageIcon of the image tha the user shoud see
	 */
	public ImageIcon getUpFace() {
		if (isUp == false) {
			return this.atrasCarta;
		} else {
			return this.frenteCarta;
		}
	}
}
