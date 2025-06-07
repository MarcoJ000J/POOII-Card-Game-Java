package menuTest;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JComboBox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GameFrame;
import menu.Menu;
import ui.Button;
import util.BackgroundPanel;

public class MenuTest {

    private Menu menu;
    private GameFrame gameFrame;
    private BackgroundPanel framePanel;

    @BeforeEach
    void setUp() throws Exception {
        gameFrame = new GameFrame() {
            @Override
            public void restartGame(int difficulty, GameFrame frame) {}
        };
        framePanel = new BackgroundPanel("/background/raw.png");
        menu = new Menu(gameFrame, framePanel);
        setField(Menu.class, "difficulty", 0);
    }

    @Test
    void testConstructorInitializesComponents() throws Exception {
        assertNotNull(menu.painel);
        assertTrue(menu.painel.getLayout() instanceof java.awt.GridBagLayout);
        assertEquals(new java.awt.Dimension(1000, 600), menu.painel.getSize());
        assertEquals(4, menu.painel.getComponentCount());
        assertTrue(menu.painel.getComponent(0) instanceof JComboBox);
        assertTrue(menu.painel.getComponent(1) instanceof Button);
        assertTrue(menu.painel.getComponent(2) instanceof Button);
        assertTrue(menu.painel.getComponent(3) instanceof Button);
    }

    @Test
    void testSetUpButtonConfiguresButtons() throws Exception {
        GridBagConstraints gbc = (GridBagConstraints) getField(menu, "gbc");
        assertEquals(2, gbc.gridx);
        assertEquals(1, gbc.gridwidth);
        assertEquals(1, gbc.gridheight);
        assertEquals(0, gbc.weightx);
        assertEquals(0, gbc.weighty);
        assertEquals(GridBagConstraints.VERTICAL, gbc.fill);
        assertEquals(GridBagConstraints.WEST, gbc.anchor);
        assertEquals(new Insets(10, 10, 10, 10), gbc.insets);

        Button start = (Button) menu.painel.getComponent(1);
        Button placar = (Button) menu.painel.getComponent(2);
        Button sair = (Button) menu.painel.getComponent(3);
        assertEquals("PLAY", start.getText());
        assertEquals("PLACAR", placar.getText());
        assertEquals("SAIR", sair.getText());
    }

    @Test
    void testComboBoxActionListenerSetsDifficulty() throws Exception {
        JComboBox<Integer> chooseDif = (JComboBox<Integer>) menu.painel.getComponent(0);
        chooseDif.setSelectedItem(3);
        java.awt.event.ActionListener[] listeners = chooseDif.getActionListeners();
        listeners[0].actionPerformed(new java.awt.event.ActionEvent(chooseDif, java.awt.event.ActionEvent.ACTION_PERFORMED, null));
        assertEquals(3, getField(Menu.class, "difficulty"));
    }

    @Test
    void testStartButtonActionListenerCallsRestartGame() throws Exception {
        GameFrame mockFrame = new GameFrame() {
            boolean restartCalled = false;

            @Override
            public void restartGame(int difficulty, GameFrame frame) {
                restartCalled = true;
                assertEquals(2, difficulty);
                assertEquals(this, frame);
            }
        };
        menu = new Menu(mockFrame, framePanel);
        setField(Menu.class, "difficulty", 2);
        Button start = (Button) menu.painel.getComponent(1);
        java.awt.event.ActionListener[] listeners = start.getActionListeners();
        listeners[0].actionPerformed(new java.awt.event.ActionEvent(start, java.awt.event.ActionEvent.ACTION_PERFORMED, null));
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

    private Object getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
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
}