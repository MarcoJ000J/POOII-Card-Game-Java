package cardForNow;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;

public class Deck {
	ArrayList<Card> cartas;
	// as mesmas cartas só que botões
	ArrayList<JButton> board;

	JButton temp1;/* primeira catar escolhida a ser comparada */
	JButton temp2;/* segunda carta escolhida a ser comparada */

	int qtdCards;

	public Deck(int difficulty) {
		// quantidade de cartas relativa a dificuldade selecionada
		// fazer ser modular por que eu posso :)
		qtdCards = difficulty + 5;

		// a quantidade de cartas na mesa é em dobro;
		cartas = new ArrayList<Card>();

		for (int i = 0; i < qtdCards; i++) {
			cartas.add(new Card(i));
			cartas.add(new Card(i));
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
			x.setPreferredSize(new Dimension(cartas.get(0).cardWidth, cartas.get(0).cardHeight));

			// mudar? updateDEck faz isso.
			x.setIcon(cartas.get(i).getUpFace());

			x.setName(Integer.toString(i));

			// why?
			x.setFocusable(false);

			x.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!GameFrame.jogoPronto) {
						return;
					} else {
						JButton temp = (JButton) e.getSource();
						int index = board.indexOf(temp);

						if (temp.getIcon() == cartas.get(index).atrasCarta) {

							if (temp1 == null) {
								temp1 = temp;
							} else if (temp2 == null) {
								temp2 = temp;
								GameFrame.attempts++;
								GameFrame.textLabel.setText("Tentativas: " + Integer.toString(GameFrame.attempts));

								// MUITO temporario, por favor deletar
								temp1 = null;
								temp2 = null;
							}

							cartas.get(index).isUp = true;

							updateBoard();
						} /*
							 * else { cartas.get(index).isUp = false; GameFrame.attempts++;
							 * GameFrame.textLabel.setText("Tentativas: " +
							 * Integer.toString(GameFrame.attempts)); updateBoard(); }
							 */
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