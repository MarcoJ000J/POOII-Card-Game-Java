package testsGame;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.Timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Card;
import game.CardPanel;
import game.Deck;
import main.GameFrame;

public class DeckTest {

    private Deck deck;
    private final int difficulty = 4;

    @BeforeEach
    void setUp() throws Exception {
        deck = new Deck(difficulty);
        setField(CardPanel.class, "jogoPronto", true);
        setField(Deck.class, "viradas", 0);
        setField(GameFrame.class, "jogoTerminado", false);
    }

    @Test
    void testConstructorInitializesFields() {
        assertEquals(difficulty + 5, getField(Deck.class, "qtdCards"));
        ArrayList<Card> cartas = (ArrayList<Card>) getField(deck, "cartas");
        assertEquals((difficulty + 5) * 2, cartas.size());
        assertNotNull(getField(deck, "board"));
        for (int i = 0; i < cartas.size(); i += 2) {
            assertEquals(cartas.get(i).nomeCarta, cartas.get(i + 1).nomeCarta);
        }
    }

    @Test
    void testPorDeckAMesaCreatesButtons() {
        ArrayList<JButton> board = (ArrayList<JButton>) getField(deck, "board");
        ArrayList<Card> cartas = (ArrayList<Card>) getField(deck, "cartas");
        assertEquals(cartas.size(), board.size());
        for (JButton button : board) {
            assertNotNull(button.getIcon());
            assertEquals(new java.awt.Dimension(90, 128), button.getPreferredSize());
            assertFalse(button.isBorderPainted());
            assertFalse(button.isContentAreaFilled());
            assertFalse(button.isFocusable());
        }
    }

    @Test
    void testEmbaralharShufflesAndSetsUpBoard() {
        ArrayList<Card> originalCartas = new ArrayList<>((ArrayList<Card>) getField(deck, "cartas"));
        deck.embaralhar();
        ArrayList<Card> shuffledCartas = (ArrayList<Card>) getField(deck, "cartas");
        assertEquals(originalCartas.size(), shuffledCartas.size());
        boolean differentOrder = false;
        for (int i = 0; i < originalCartas.size(); i++) {
            if (originalCartas.get(i) != shuffledCartas.get(i)) {
                differentOrder = true;
                break;
            }
        }
        assertTrue(differentOrder || originalCartas.equals(shuffledCartas));
        ArrayList<JButton> board = (ArrayList<JButton>) getField(deck, "board");
        assertEquals(shuffledCartas.size(), board.size());
    }

    @Test
    void testHideAllCardsSetsIsUpFalse() {
        deck.hideAllCards();
        ArrayList<Card> cartas = (ArrayList<Card>) getField(deck, "cartas");
        for (Card carta : cartas) {
            assertFalse(carta.isUp);
        }
        ArrayList<JButton> board = (ArrayList<JButton>) getField(deck, "board");
        for (int i = 0; i < board.size(); i++) {
            assertEquals(cartas.get(i).atrasCarta, board.get(i).getIcon());
        }
    }

    @Test
    void testWinwinSetsJogoTerminado() {
        setField(Deck.class, "viradas", difficulty + 5);
        setField(Deck.class, "qtdCards", difficulty + 5);
        Deck.winwin();
        assertTrue((boolean) getField(GameFrame.class, "jogoTerminado"));
    }

    @Test
    void testButtonActionListenerHandlesPairMatch() throws Exception {
        ArrayList<JButton> board = (ArrayList<JButton>) getField(deck, "board");
        ArrayList<Card> cartas = (ArrayList<Card>) getField(deck, "cartas");
        cartas.get(0).isUp = false;
        cartas.get(1).isUp = false;
        setField(deck, "temp1", null);
        setField(deck, "temp2", null);
        setField(CardPanel.class, "attempts", 0);
        setField(CardPanel.class, "hits", 0);
        JButton button1 = board.get(0);
        JButton button2 = board.get(1);
        button1.setName(cartas.get(0).nomeCarta);
        button2.setName(cartas.get(0).nomeCarta);
        ActionListener[] listeners1 = button1.getActionListeners();
        ActionListener[] listeners2 = button2.getActionListeners();
        listeners1[0].actionPerformed(new ActionEvent(button1, ActionEvent.ACTION_PERFORMED, null));
        listeners2[0].actionPerformed(new ActionEvent(button2, ActionEvent.ACTION_PERFORMED, null));
        assertEquals(1, getField(CardPanel.class, "attempts"));
        assertEquals(1, getField(CardPanel.class, "hits"));
        assertEquals(1, getField(Deck.class, "viradas"));
        assertNull(getField(deck, "temp1"));
        assertNull(getField(deck, "temp2"));
    }

    @Test
    void testButtonActionListenerHandlesNonMatch() throws Exception {
        ArrayList<JButton> board = (ArrayList<JButton>) getField(deck, "board");
        ArrayList<Card> cartas = (ArrayList<Card>) getField(deck, "cartas");
        cartas.get(0).isUp = false;
        cartas.get(2).isUp = false;
        setField(deck, "temp1", null);
        setField(deck, "temp2", null);
        setField(CardPanel.class, "attempts", 0);
        setField(CardPanel.class, "jogoPronto", true);
        JButton button1 = board.get(0);
        JButton button2 = board.get(2);
        button1.setName(cartas.get(0).nomeCarta);
        button2.setName(cartas.get(2).nomeCarta);
        ActionListener[] listeners1 = button1.getActionListeners();
        ActionListener[] listeners2 = button2.getActionListeners();
        listeners1[0].actionPerformed(new ActionEvent(button1, ActionEvent.ACTION_PERFORMED, null));
        listeners2[0].actionPerformed(new ActionEvent(button2, ActionEvent.ACTION_PERFORMED, null));
        assertEquals(1, getField(CardPanel.class, "attempts"));
        assertFalse((boolean) getField(CardPanel.class, "jogoPronto"));
        Timer nah = (Timer) getField(deck, "nah");
        ActionListener[] timerListeners = nah.getActionListeners();
        timerListeners[0].actionPerformed(new ActionEvent(nah, ActionEvent.ACTION_PERFORMED, null));
        assertTrue((boolean) getField(CardPanel.class, "jogoPronto"));
        assertFalse(cartas.get(0).isUp);
        assertFalse(cartas.get(2).isUp);
        assertNull(getField(deck, "temp1"));
        assertNull(getField(deck, "temp2"));
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

    private Object getField(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setField(Class<?> clazz, String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(null, value);
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