package testsGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game.Card;
/**
 * Testes para a classe Card
 * @author Marco AFR.Jr.
 * @since 13/05/2025
 * @see Card
 */
class CardTest {

	ArrayList<Card> Test = new ArrayList<>();

	@BeforeAll
	void iniTest() {
		Card generic = new Card(0);
		assertEquals(90, generic.cardWidth);
		assertEquals(128, generic.cardHeight);

		assertEquals(generic.isUp, true);
	}

	@Test
	void testCard() {
		for(int i = 0; i < Card.getMaxCards(); i++) {
			Card temp = new Card(i);

			Test.add(temp);

			//corrigir
			//assertEquals(Card.cards[i], temp.nomeCarta);

			assertNotNull(temp.atrasCarta);
			assertNotNull(temp.frenteCarta);
		}
	}

	@Test
	void testGetCardId() {
		for(Card i : Test) {
			assertEquals(i.nomeCarta, i.getCardId());
		}
	}

	@Test
	void testGetMaxCards() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUpFace() {
		fail("Not yet implemented");
	}

}
