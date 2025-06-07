package testsMain;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.lang.reflect.Field;

import javax.swing.Timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.CardPanel;
import game.Deck;
import main.GameFrame;
import menu.Menu;
import menu.WinMenu;
import util.BackgroundPanel;
import util.MusicPlayer;

public class GameFrameTest {

    private GameFrame gameFrame;

    @BeforeEach
    void setUp() throws Exception {
        gameFrame = new GameFrame();
        setField(Deck.class, "viradas", 0);
        setField(GameFrame.class, "jogoTerminado", false);
    }

    @Test
    void testConstructorInitializesFrame() throws Exception {
        assertEquals("A INCRIVEL Card Game XD", gameFrame.getTitle());
        assertEquals(new java.awt.Dimension(1000, 600), gameFrame.getSize());
        assertTrue(gameFrame.getLayout() instanceof java.awt.GridBagLayout);
        assertFalse(gameFrame.isResizable());
        assertNotNull(gameFrame.getLocation());
        assertNotNull(getField(gameFrame, "mainMenu"));
        GridBagConstraints gbcM = (GridBagConstraints) getField(GameFrame.class, "gbcM");
        assertEquals(2, gbcM.gridwidth);
        assertEquals(2, gbcM.gridheight);
        assertEquals(1.0, gbcM.weightx);
        assertEquals(1.0, gbcM.weighty);
        assertEquals(GridBagConstraints.BOTH, gbcM.fill);
        assertEquals(1, gameFrame.getContentPane().getComponentCount());
        assertTrue(gameFrame.getContentPane().getComponent(0) instanceof BackgroundPanel);
    }

    @Test
    void testCheckShowsWinMenuWhenGameEnds() throws Exception {
        BackgroundPanel tela = new BackgroundPanel("/background/slaFundo.jpg");
        setField(gameFrame, "tela", tela);
        setField(GameFrame.class, "jogoTerminado", true);
        gameFrame.getContentPane().removeAll();
        invokeMethod(gameFrame, "check", gameFrame.getContentPane(), gameFrame);
        Timer endGameTimer = (Timer) getField(gameFrame, "endGameTimer");
        endGameTimer.getActionListeners()[0].actionPerformed(new java.awt.event.ActionEvent(endGameTimer, java.awt.event.ActionEvent.ACTION_PERFORMED, null));
        assertEquals(1, gameFrame.getContentPane().getComponentCount());
        assertTrue(gameFrame.getContentPane().getComponent(0) instanceof WinMenu);
        assertEquals(GridBagConstraints.CENTER, CardPanel.gbc.fill);
    }

    @Test
    void testBackToMenuResetsFrame() throws Exception {
        BackgroundPanel tela = new BackgroundPanel("/background/slaFundo.jpg");
        setField(gameFrame, "tela", tela);
        Menu mainMenu = new Menu(gameFrame, tela);
        setField(gameFrame, "mainMenu", mainMenu);
        setField(CardPanel.class, "restart", new ui.RestartButton(gameFrame) {
            public void actionPerformed(java.awt.event.ActionEvent e) {}
        });
        gameFrame.getContentPane().add(tela);
        gameFrame.backToMenu(gameFrame);
        assertNull(getField(gameFrame, "tela"));
        assertNotNull(getField(gameFrame, "mainMenu"));
        assertEquals(1, gameFrame.getContentPane().getComponentCount());
        assertTrue(gameFrame.getContentPane().getComponent(0) instanceof BackgroundPanel);
        assertFalse((boolean) getField(GameFrame.class, "jogoTerminado"));
        assertEquals(0, getField(Deck.class, "viradas"));
        GridBagConstraints gbcM = (GridBagConstraints) getField(GameFrame.class, "gbcM");
        assertEquals(2, gbcM.gridwidth);
        assertEquals(2, gbcM.gridheight);
        assertEquals(1.0, gbcM.weightx);
        assertEquals(1.0, gbcM.weighty);
        assertEquals(GridBagConstraints.BOTH, gbcM.fill);
    }

    @Test
    void testRestartGameStartsNewGame() throws Exception {
        Menu mainMenu = new Menu(gameFrame, null);
        setField(gameFrame, "mainMenu", mainMenu);
        BackgroundPanel tela = new BackgroundPanel("/background/slaFundo.jpg");
        setField(gameFrame, "tela", tela);
        gameFrame.getContentPane().add(tela);
        gameFrame.getContentPane().add(mainMenu.painel);
        int difficulty = 3;
        gameFrame.restartGame(difficulty, gameFrame);
        assertNull(getField(gameFrame, "mainMenu"));
        assertNotNull(getField(gameFrame, "tela"));
        assertFalse((boolean) getField(GameFrame.class, "jogoTerminado"));
        assertEquals(0, getField(Deck.class, "viradas"));
        assertEquals(1, gameFrame.getContentPane().getComponentCount());
        assertTrue(gameFrame.getContentPane().getComponent(0) instanceof BackgroundPanel);
        GridBagConstraints gbcM = (GridBagConstraints) getField(GameFrame.class, "gbcM");
        assertEquals(2, gbcM.gridwidth);
        assertEquals(2, gbcM.gridheight);
        assertEquals(1.0, gbcM.weightx);
        assertEquals(1.0, gbcM.weighty);
        assertEquals(GridBagConstraints.BOTH, gbcM.fill);
    }

    @Test
    void testWindowClosingStopsMusicAndTimer() throws Exception {
        Timer endGameTimer = new Timer(100, e -> {});
        setField(gameFrame, "endGameTimer", endGameTimer);
        MusicPlayer mockMusicPlayer = new MusicPlayer() {
            boolean stopped = false;
            //this need to be remade
            public void endBackgroundMeusic() {
                stopped = true;
            }
        };
        setField(MusicPlayer.class, "backgroundClip", new Object());
        gameFrame.getWindowListeners()[0].windowClosing(new java.awt.event.WindowEvent(gameFrame, java.awt.event.WindowEvent.WINDOW_CLOSING));
        assertFalse(endGameTimer.isRunning());
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

    private void setField(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
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

    private void invokeMethod(Object obj, String methodName, Object... args) {
        try {
            java.lang.reflect.Method method = obj.getClass().getDeclaredMethod(methodName, Container.class, GameFrame.class);
            method.setAccessible(true);
            method.invoke(obj, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}