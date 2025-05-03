package cardForNow;

public class Card {
	
	// just add here 
	final static String[] cards = {"carta1", "carta2", "carta3", "carta4", "carta5", "carta6", "carta7"
			, "carta8", "carta9"};
	/** 
	 *  :-)
	 */
	
	public final static int maxCards = cards.length;
	
	//mudat para imageIcon :(
	private final String atrasCarta;
	private final String frenteCarta;
	
	private boolean isUp = false;
	//public String upFace;
	
	public Card(int frente) {
		
		this.frenteCarta = cards[frente] + ".png";
		this.atrasCarta = "backTemp.png"; // nome do arquivo
		
	}
	
	public static int getMaxCards() {
		return maxCards;
	}

	public String getUpFace() {
		if (isUp == false) {
			return this.atrasCarta;
		}else {
			return this.frenteCarta;
		}
	}
}
