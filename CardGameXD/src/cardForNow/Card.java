package cardForNow;

public class Card {

	enum Cards{
		
		carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8, carta9;
		
		//aqui se cria o retorno do tipo das cartas pra usar (nome bosta mudar!!!!)
		private static Cards[] cratas = Cards.values();
		public static Cards getCards(int i) {
			return cratas[i];
		}
	}
		
	
	private final String back;
	private final Cards frenteCarta;
		
	public Card(Cards frente) {
		this.frenteCarta = frente;
		this.back = "back-of-the-cards.png"; // nome do arquivo seja la qual for...
	}
}
