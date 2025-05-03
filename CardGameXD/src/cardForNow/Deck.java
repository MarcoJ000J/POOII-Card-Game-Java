package cardForNow;

import java.util.Random;

public class Deck {
	
	//talvez mudar para array?
	Card[] cartas;
	int qtdCards;
	
	public Deck(int difficulty) {
		
		int atual = 0;
		
		// quantidade de cartas relativa a dificuldade selecionada
		// fazer ser modular por que eu posso :)
		qtdCards = difficulty + 5;
		
		//verify
		// a quantidade de cartas na mesa é em dobrp;
		for(int i = 0; i <= (qtdCards-1)*2; i++) {
			
			
			cartas = new Card[qtdCards*2];
			
			// aff
			
			cartas[i++] = new Card(atual);
			cartas[i++] = new Card(atual);
			atual++;
		}
		
		//auto embaralha as cartas para por na mesa
		embaralhar();
	}
	
	//nao sei se é o melhor nao 
	public void embaralhar() {
		Random random = new Random();
		int  n = cartas.length;
		
		
		for (int i = 0;i < n; i ++) {
			
			//eembaralhar as cartas
			int valorRandom = random.nextInt(n - i);
			Card temp = cartas[valorRandom];
			
			cartas[valorRandom] = cartas[i];
			cartas[i] = temp;
		}
		

	}
}