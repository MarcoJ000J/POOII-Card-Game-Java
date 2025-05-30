package game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;

public class Deck {
	ArrayList<Card> cartas;
	// as mesmas cartas só que botões
	ArrayList<JButton> board;

	JButton temp1 = null;/* primeira catar escolhida a ser comparada */
	JButton temp2 = null;/* segunda carta escolhida a ser comparada */

	static int qtdCards;
	static int viradas;

	Timer nah;
	
	public Deck(int difficulty) {
		// quantidade de cartas relativa a dificuldade selecionada
		// fazer ser modular por que eu posso :)
		qtdCards = difficulty + 5;
		

		// a quantidade de cartas na mesa é em dobro;
		cartas = new ArrayList<Card>();
		
		//para randomizar quais cartas aparecem!
		Random random = new Random();
		ArrayList<Integer> jaForam;
		/*
		 * Coloca as cartas no deck, selecionando as aleatoriamente para ter variedade!
		 */
		jaForam = new ArrayList<Integer>();
		for (int i = 0; i < qtdCards;) {
			int temp = random.nextInt(Card.getMaxCards());
						
			if(i == 0){
				
				cartas.add(new Card(temp));
				cartas.add(new Card(temp));
				
				jaForam.add(temp);
				
				i++;
			}else {
				boolean jaFoi = false;
				for(int j = 0; j < jaForam.size(); j++) {
					if(jaForam.get(j) == temp) {
						jaFoi = true;
					}
				}if(!jaFoi) {
						cartas.add(new Card(temp));
						cartas.add(new Card(temp));
						
						jaForam.add(temp);
						
						i++;	
					}
			}

		}

		// auto embaralha as cartas para por na mesa
		embaralhar();
	}

	// mudar o nome?
	public void porDeckAMesa() {
		board = new ArrayList<JButton>();

		for (int i = 0; i < cartas.size(); i++) {
			JButton x = new JButton();

			// (>_<)
			x.setPreferredSize(new Dimension(cartas.get(0).cardWidth,
					cartas.get(0).cardHeight));

			// mudar? updateDEck faz isso.
			x.setIcon(cartas.get(i).getUpFace());

			x.setName(cartas.get(i).getCardId());
			
			//mudanças visuais no butão
			x.setBorderPainted(false);
			x.setContentAreaFilled(false);
			
			// why?
			x.setFocusable(false);

			x.addActionListener(new ActionListener() {
				//vou precisar mudar
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!CardPanel.jogoPronto) {
						return;
					} else {
						
						JButton temp = (JButton) e.getSource();
						int index = board.indexOf(temp);
												
						if (temp.getIcon() == cartas.get(index).atrasCarta) {

							cartas.get(index).isUp = true;
							
							if (temp1 == null) {
							
								temp1 = temp;
								index = board.indexOf(temp1);
								
							} else if (temp2 == null) {
								temp2 = temp;
								
								CardPanel.attempts++;
								
								if(temp2.getName() == temp1.getName()) {
									temp1 = null;
									temp2 = null;
									
									CardPanel.hits++;
									viradas++;
									
									CardPanel.upDateTextLabel();
								}else {
									CardPanel.jogoPronto = false;
									nah = new Timer(1000, new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent e) {
											CardPanel.jogoPronto = true;
											int index = board.indexOf(temp1);
											cartas.get(index).isUp = false;
									
											index = board.indexOf(temp2);
											cartas.get(index).isUp = false;
											
											temp1 = null;
											temp2 = null;
																					
											updateBoard();
										}
									});
									nah.setRepeats(false);
									nah.start();
									
									CardPanel.upDateTextLabel();
								}
								
							}
						} 
						
						updateBoard();
						CardPanel.winwin();
					}
				}
			});
			board.add(x);
		}
	}

	// nao sei se é o melhor nao / isso aqui ja poe as cartas nos botões automatico,
	// será bom?
	public void embaralhar() {
		Random random = new Random();
		int n = cartas.size();

		for (int i = 0; i < n; i++) {

			// embaralhar as cartas
			int valorRandom = random.nextInt(n - i);

			Card temp = cartas.get(i);
			cartas.set(i, cartas.get(valorRandom));
			cartas.set(valorRandom, temp);
		}

		porDeckAMesa();
	}

	public void hideAllCards() {
		for (Card carta : this.cartas) {
			carta.isUp = false;
		}
		updateBoard();
	}

	/*
	 * atualiza os imageIcons dos botões no array de botões que vai para a tela
	 */
	private void updateBoard() {
		for (int i = 0; i < board.size(); i++) {
			board.get(i).setIcon(cartas.get(i).getUpFace());
		}
	}
}