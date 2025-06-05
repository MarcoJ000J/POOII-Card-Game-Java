package testsGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.CardPanel;

class CardPanelTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CardPanel test = new CardPanel(0, null);
	}
	
	@BeforeEach
	void setUp() {
		
	    //CardPanel.jogoPronto = false;
	    //CardPanel.attempts = 0;
	    //CardPanel.hits = 0;
	}

	@Test
	void testCardPanel() {
		fail("Not yet implemented");
	}

	@Test
	void testGameCardSet() {
		fail("Not yet implemented");
	}

	@Test
	void testUpDateTextLabel() {
		fail("Not yet implemented");
	}

}
