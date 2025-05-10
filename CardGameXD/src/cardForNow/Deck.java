package cardForNow;

import java.util.ArrayList;
import java.util.Random;

public class Deck {	
	ArrayList<Card> cartas;

	int qtdCards;
	
	public Deck(int difficulty) {
		// quantidade de cartas relativa a dificuldade selecionada
		// fazer ser modular por que eu posso :)
		qtdCards = difficulty + 5;
		
		// a quantidade de cartas na mesa é em dobro;
		cartas = new ArrayList<Card>();
		
		for(int i = 0; i < qtdCards; i++) {
			cartas.add(new Card(i));
			cartas.add(new Card(i));
		}
		
		//auto embaralha as cartas para por na mesa
		embaralhar();
	}
	
	//nao sei se é o melhor nao 
	public void embaralhar() {
		Random random = new Random();
		int  n = cartas.size();
		
		for (int i = 0;i < n; i ++) {
			
			//embaralhar as cartas
			int valorRandom = random.nextInt(n - i);
			
			Card temp = cartas.get(i);
			cartas.set(i, cartas.get(valorRandom));
			cartas.set(valorRandom, temp);
		}
		
		//para testes
		/*for(int i = 0; i < cartas.size();i++) {
			System.out.println(cartas.get(i).nomeCarta);
		}*/
	}
}