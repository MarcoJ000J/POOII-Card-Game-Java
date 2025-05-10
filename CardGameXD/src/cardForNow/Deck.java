package cardForNow;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;

public class Deck {	
	ArrayList<Card> cartas;
	//as mesmas cartas só que botões
	ArrayList<JButton> board;

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
	
	//mudar o nome?
	public void porDeckAMesa() {
		board = new ArrayList<JButton>();
		
		for(int i = 0; i < cartas.size(); i++) {
			JButton x = new JButton();
			
			//(>_<)	
			x.setPreferredSize(new Dimension(cartas.get(0).cardWidth
					, cartas.get(0).cardHeight));
			
			x.setIcon(cartas.get(i).getUpFace());
			
			//why?
			x.setFocusable(false);
			
			board.add(x);
		}
	}
	
	//nao sei se é o melhor nao / isso aqui ja poe as cartas nos botões automatico, será bom?
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
		
		porDeckAMesa();
		
		//para testes
		/*for(int i = 0; i < cartas.size();i++) {
			System.out.println(cartas.get(i).nomeCarta);
		}*/
	}
}