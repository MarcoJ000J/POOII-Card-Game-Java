package game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;

import main.GameFrame;

/**
 * Classe que representa um conjunto de cartas e os botoes aquivalentes a elas @see Card.java
 * Usado por @see CardPanel
 * para mostrar as cartas
 */
public class Deck {
	ArrayList<Card> cartas;
	// as mesmas cartas só que botões
	ArrayList<JButton> board;

	JButton temp1 = null;/* primeira catar escolhida a ser comparada */
	JButton temp2 = null;/* segunda carta escolhida a ser comparada */

	public static int qtdCards = 0;
	public static int viradas = 0;

	Timer nah;

	/**
	 * Construtor de Deck, recebe uma quantidade de cartas que depende de
	 * @see difficulty
	 */
	public Deck(int difficulty) {
		// quantidade de cartas relativa a dificuldade selecionada
		// fazer ser modular por que eu posso :)
		qtdCards = difficulty + 5;


		// a quantidade de cartas na mesa é em dobro;
		cartas = new ArrayList<>();

		//para randomizar quais cartas aparecem!
		Random random = new Random();
		ArrayList<Integer> jaForam;
		/**
		 * Coloca as cartas no deck, selecionando as aleatoriamente para ter variedade!
		 */
		jaForam = new ArrayList<>();
		for (int i = 0; i < qtdCards;) {
			int temp = random.nextInt(Card.getMaxCards());

			if(i == 0){

				cartas.add(new Card(temp));
				cartas.add(new Card(temp));

				jaForam.add(temp);

				i++;
			}else {
				boolean jaFoi = false;
				for (Integer element : jaForam) {
					if(element == temp) {
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

	/**
	 * Função que efetivamente cria os botoes e seus action listeners
	 * usado por @see Deck()
	 */
	private void porDeckAMesa() {
		board = new ArrayList<>();

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

			//adiciona o action event das cartas
			x.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!CardPanel.jogoPronto) {
						//nao ativa o botao se jogo nao esta rolando
						return;
					} else {
						//pega qual é a carta
						JButton temp = (JButton) e.getSource();
						int index = board.indexOf(temp);
						
						//flipa a carta
						if (temp.getIcon() == cartas.get(index).atrasCarta) {

							cartas.get(index).isUp = true;
							
							//ve se é a primeira carta virada e se sim salva ela
							if (temp1 == null) {

								temp1 = temp;
								index = board.indexOf(temp1);

							} 
							//ve se é a segunda carta virada e se sim sava ela e depois compara
							else if (temp2 == null) {
								temp2 = temp;

								CardPanel.attempts++;
								//se as cartas foram iguais, mantem elas viradas e da ponto
								if(temp2.getName() == temp1.getName()) {
									temp1 = null;
									temp2 = null;

									CardPanel.hits++;
									viradas++;

									//som
									
									winwin();

									CardPanel.upDateTextLabel();
								}
								//se as cartas sao diferentes vira elas pra baixo denovo
								else {
									CardPanel.jogoPronto = false;
									nah = new Timer(1000, new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											CardPanel.jogoPronto = true;
											int index = board.indexOf(temp1);
											cartas.get(index).isUp = false;

											index = board.indexOf(temp2);
											cartas.get(index).isUp = false;

											//som
											
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
					}
				}
			});
			board.add(x);
		}
	}

	/**
	 * Função que verifica se o jogo acabou, usado quando um par de cartas é encontrado
	 * @see poDeckAMesa()
	 */
	public static void winwin() {
		if(Deck.viradas == Deck.qtdCards) {
			GameFrame.jogoTerminado = true;
		}
	}

	/**
	 * Função resposavel por embaralhar as cartas e tambem distribuilas chamando
	 *  @see porDeckAMesa()
	 */
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

	/**
	 * Função que esconde todas as cartas, usada no começo da partida
	 * @see CardPanel.paraEsconderCartas()
	 */
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