package testsGame;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.lang.reflect.Field;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Card;

public class CardTest {

    private Card card;
    private final int cardIndex = 0;

    @BeforeEach
    void setUp() {
        card = new Card(cardIndex);
    }

    @Test
    void testConstructorInitializesFields() {
        assertEquals("frontTemp", card.nomeCarta);
        assertTrue(card.isUp);
        assertNotNull(card.frenteCarta);
        assertNotNull(card.frenteCarta.getImage());
        assertEquals(90, card.frenteCarta.getIconWidth());
        assertEquals(128, card.frenteCarta.getIconHeight());
        assertNotNull(card.atrasCarta);
        assertNotNull(card.atrasCarta.getImage());
        assertEquals(90, card.atrasCarta.getIconWidth());
        assertEquals(128, card.atrasCarta.getIconHeight());
    }

    @Test
    void testGetCardIdReturnsNomeCarta() {
        assertEquals("frontTemp", card.getCardId());
    }

    @Test
    void testGetMaxCardsReturnsCorrectValue() throws Exception {
        String[] cardsArray = (String[]) getField(Card.class, "cards");
        assertEquals(cardsArray.length, Card.getMaxCards());
        assertEquals(9, Card.getMaxCards());
    }

    @Test
    void testGetUpFaceReturnsCorrectImage() {
        assertTrue(card.isUp);
        assertEquals(card.frenteCarta, card.getUpFace());
        setField(card, "isUp", false);
        assertFalse(card.isUp);
        assertEquals(card.atrasCarta, card.getUpFace());
    }

    private Object getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setField(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}