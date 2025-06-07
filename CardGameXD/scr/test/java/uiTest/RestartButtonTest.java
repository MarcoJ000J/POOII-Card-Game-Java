package uiTest;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GameFrame;
import menu.Menu;
import ui.RestartButton;

public class RestartButtonTest {

    private RestartButton restartButton;
    private GameFrame gameFrame;

    @BeforeEach
    void setUp() {
        gameFrame = new GameFrame() {
            @Override
            public void restartGame(int difficulty, GameFrame frame) {}
        };
        restartButton = new RestartButton(gameFrame);
    }

    @Test
    void testConstructorInitializesButton() {
        assertNotNull(restartButton.getIcon());
        assertTrue(restartButton.isVisible());
        assertFalse(restartButton.isBorderPainted());
        assertFalse(restartButton.isContentAreaFilled());
        assertEquals(java.awt.Color.WHITE, restartButton.getForeground());
    }

    @Test
    void testActionListenerCallsRestartGame() {
        Menu.difficulty = 3;
        GameFrame mockFrame = new GameFrame() {
            boolean restartCalled = false;

            @Override
            public void restartGame(int difficulty, GameFrame frame) {
                restartCalled = true;
                assertEquals(3, difficulty);
                assertEquals(this, frame);
            }
        };
        restartButton = new RestartButton(mockFrame);
        java.awt.event.ActionListener[] listeners = restartButton.getActionListeners();
        listeners[0].actionPerformed(new ActionEvent(restartButton, ActionEvent.ACTION_PERFORMED, null));
    }
}