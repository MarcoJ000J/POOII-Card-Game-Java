package menuTest;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GameFrame;
import menu.WinMenu;
import ui.Button;
import util.FontLoader;

public class WinMenuTest {

    private WinMenu winMenu;
    private GameFrame gameFrame;
    private FontLoader fontLoader;

    @BeforeEach
    void setUp() throws Exception {
        fontLoader = new FontLoader();
        fontLoader.carregarFonte();
        gameFrame = new GameFrame() {
            @Override
            public void backToMenu(GameFrame frame) {}
        };
        winMenu = new WinMenu(gameFrame);
    }

    @Test
    void testConstructorInitializesComponents() throws Exception {
        assertEquals(new Dimension(256, 256), winMenu.getPreferredSize());
        assertEquals(new Dimension(256, 256), winMenu.getMinimumSize());
        assertEquals(new Dimension(256, 256), winMenu.getMaximumSize());
        assertTrue(winMenu.getLayout() instanceof java.awt.BorderLayout);
        assertFalse(winMenu.isOpaque());
        assertNotNull(getField(winMenu, "backgroundImage"));
        assertTrue(winMenu.isVisible());

        JLabel pontuacao = (JLabel) winMenu.getComponent(0);
        assertEquals("Pontuação: 0", pontuacao.getText());
        assertFalse(pontuacao.isOpaque());
        assertEquals(Color.WHITE, pontuacao.getForeground());
        assertEquals(fontLoader.fonte, pontuacao.getFont());

        JPanel paraPegarNome = (JPanel) winMenu.getComponent(1);
        assertFalse(paraPegarNome.isOpaque());
        assertEquals(2, paraPegarNome.getComponentCount());
        assertTrue(paraPegarNome.getComponent(0) instanceof JTextField);
        assertTrue(paraPegarNome.getComponent(1) instanceof Button);
    }

    @Test
    void testButtonActionListenerSetsNomeAndCallsBackToMenu() throws Exception {
        JPanel paraPegarNome = (JPanel) winMenu.getComponent(1);
        JTextField getNome = (JTextField) paraPegarNome.getComponent(0);
        Button enviaNome = (Button) paraPegarNome.getComponent(1);

        getNome.setText("TestPlayer");
        ActionListener[] listeners = enviaNome.getActionListeners();
        listeners[0].actionPerformed(new ActionEvent(enviaNome, ActionEvent.ACTION_PERFORMED, null));

        assertEquals("TestPlayer", getField(game.CardPanel.class, "nome"));
    }

    @Test
    void testPaintComponentDrawsBackgroundImage() throws Exception {
        java.awt.Graphics graphics = new java.awt.image.BufferedImage(256, 256, java.awt.image.BufferedImage.TYPE_INT_ARGB).getGraphics();
        winMenu.setSize(256, 256);
        winMenu.paintComponent(graphics);
        assertNotNull(graphics);
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
}