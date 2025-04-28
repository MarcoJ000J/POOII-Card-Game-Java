package cardForNow;

import cardForNow.Card.Cards;

public class Deck {
	
	Card[] cartas;
	
	public Deck(int qtdCards) {
		
		for(int i = 0; i < qtdCards*2; i++) {
			int atual = i;
			
			cartas = new Card[qtdCards];
			
			// aff
			
			cartas[i++] = new Card(Cards.getCards(atual));
			cartas[i++] = new Card(Cards.getCards(atual));		
		}
	}
}