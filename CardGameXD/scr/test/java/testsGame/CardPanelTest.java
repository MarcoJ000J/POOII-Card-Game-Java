package testsGame;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.CardPanel;
import main.GameFrame;
import util.BackgroundPanel;
import util.FontLoader;

public class CardPanelTest {

    private CardPanel cardPanel;
    private GameFrame gameFrame;
    private int difficulty = 4; // Example difficulty level
    private FontLoader fontLoader;
    private BackgroundPanel backgroundPanel;

    @BeforeEach
    void setUp() throws Exception {
        // Create real instances for dependencies
        fontLoader = new FontLoader() {
            public Font fonte = new Font("Arial", Font.PLAIN, 12); // Stub FontLoader
        };
        gameFrame = new GameFrame() {
            // Stub GameFrame with minimal implementation
            public GridBagConstraints gbcM = new GridBagConstraints();
            @Override
            public void add(java.awt.Component comp, Object constraints) {
                // No-op for testing
            }
        };
        backgroundPanel = new BackgroundPanel("/background/slaFundo.jpg") {
            // Stub BackgroundPanel
            @Override
            public void setLayout(java.awt.LayoutManager mgr) {
                super.setLayout(mgr);
            }
        };

        // Initialize CardPanel
        cardPanel = new CardPanel(difficulty, gameFrame);

        // Set FontLoader's font using reflection
        setField(cardPanel, "fonte", fontLoader.fonte);

        // Set BackgroundPanel
        setField(cardPanel, "panel", backgroundPanel);
    }

    @Test
    void testConstructorInitializesVariables() {
        assertEquals(0, getField(cardPanel, "attempts"), "Attempts should be initialized to 0");
        assertEquals(0, getField(cardPanel, "hits"), "Hits should be initialized to 0");
        assertEquals(difficulty, getField(cardPanel, "difficulty"), "Difficulty should be set correctly");
        assertEquals(2, getField(cardPanel, "rows"), "Rows should be initialized to 2");
        assertNotNull(getField(cardPanel, "paraEsconderCartas"), "Timer should be initialized");
        assertNotNull(getField(cardPanel, "textPanel"), "Text panel should be initialized");
        assertNotNull(getField(CardPanel.class, "textLabel"), "Text label should be initialized");
        assertNotNull(getField(CardPanel.class, "restart"), "Restart button should be initialized");

        // Verify GameFrame's gbcM configuration
        GridBagConstraints gbcM = (GridBagConstraints) getField(gameFrame, "gbcM");
        assertEquals(0, gbcM.gridx, "gbcM.gridx should be 0");
        assertEquals(0, gbcM.gridy, "gbcM.gridy should be 0");
        assertEquals(1, gbcM.gridwidth, "gbcM.gridwidth should be 1");
        assertEquals(1, gbcM.gridheight, "gbcM.gridheight should be 1");
        assertEquals(0.0, gbcM.weightx, "gbcM.weightx should be 0");
        assertEquals(0.0, gbcM.weighty, "gbcM.weighty should be 0");
        assertEquals(GridBagConstraints.NORTHEAST, gbcM.anchor, "gbcM.anchor should be NORTHEAST");
        assertEquals(GridBagConstraints.NONE, gbcM.fill, "gbcM.fill should be NONE");
    }

    @Test
    void testGameCardSetSetsUpLayoutAndComponents() throws Exception {
        // Create a stubbed board panel
        JPanel boardPanel = new JPanel();
        setField(cardPanel, "boardPanel", boardPanel);

        // Call gameCardSet
        cardPanel.gameCardSet();

        // Verify panel layout
        assertTrue(backgroundPanel.getLayout() instanceof java.awt.GridBagLayout, "Panel should use GridBagLayout");

        // Verify board panel setup
        assertTrue(boardPanel.getLayout() instanceof java.awt.GridBagLayout, "Board panel should use GridBagLayout");
        assertFalse(boardPanel.isOpaque(), "Board panel should be transparent");

        // Verify GridBagConstraints setup
        GridBagConstraints gbc = (GridBagConstraints) getField(CardPanel.class, "gbc");
        assertEquals(GridBagConstraints.HORIZONTAL, gbc.fill, "GBC fill should be HORIZONTAL");
        assertEquals(GridBagConstraints.CENTER, gbc.anchor, "GBC anchor should be CENTER");
        assertEquals(new Insets(15, 5, 15, 5), gbc.insets, "GBC insets should be set");

        // Verify text panel setup
        JPanel textPanel = (JPanel) getField(cardPanel, "textPanel");
        assertFalse(textPanel.isOpaque(), "Text panel should be transparent");

        // Verify text label setup
        JLabel textLabel = (JLabel) getField(CardPanel.class, "textLabel");
        assertEquals(fontLoader.fonte, textLabel.getFont(), "Text label font should be set");
        assertEquals(java.awt.Color.WHITE, textLabel.getForeground(), "Text label foreground should be WHITE");
        assertEquals(javax.swing.SwingConstants.LEFT, textLabel.getHorizontalAlignment(), "Text label should be left-aligned");
        assertFalse(textLabel.isOpaque(), "Text label should be transparent");
        assertEquals("Tentativas: 0 Acertos: 0", textLabel.getText(), "Text label should show initial attempts and hits");

        // Verify timer initialization
        Timer timer = (Timer) getField(cardPanel, "paraEsconderCartas");
        assertFalse(timer.isRepeats(), "Timer should not repeat");
        assertEquals(1500, timer.getDelay(), "Timer delay should be 1500ms");
    }

    @Test
    void testUpDateTextLabelUpdatesText() {
        // Set attempts and hits
        setField(CardPanel.class, "attempts", 5);
        setField(CardPanel.class, "hits", 3);

        // Call the method
        CardPanel.upDateTextLabel();

        // Verify the text label is updated
        JLabel textLabel = (JLabel) getField(CardPanel.class, "textLabel");
        assertEquals("Tentativas: 5 Acertos: 3", textLabel.getText(), "Text label should show updated attempts and hits");
    }

    @Test
    void testTimerSetsJogoPronto() throws Exception {
        // Set jogoPronto to false
        setField(CardPanel.class, "jogoPronto", false);

        // Get the timer and trigger its action
        Timer timer = (Timer) getField(cardPanel, "paraEsconderCartas");
        ActionListener[] listeners = timer.getActionListeners();
        assertEquals(1, listeners.length, "Timer should have one action listener");
        listeners[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        // Verify jogoPronto is true
        assertTrue((boolean) getField(CardPanel.class, "jogoPronto"), "jogoPronto should be true after timer fires");
    }

    // Helper methods to access private fields
    private Object getField(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access field: " + fieldName, e);
        }
    }

    private void setField(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field: " + fieldName, e);
        }
    }

    private Object getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access static field: " + fieldName, e);
        }
    }

    private void setField(Class<?> clazz, String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(null, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set static field: " + fieldName, e);
        }
    }
}