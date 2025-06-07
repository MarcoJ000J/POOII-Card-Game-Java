package uiTest;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.Button;
import util.FontLoader;

public class ButtonTest {

    private Button button;
    private FontLoader fontLoader;
    private final String imagePath = "/ui/spr_banner_hud.png";

    @BeforeEach
    void setUp() throws Exception {
        fontLoader = new FontLoader();
        fontLoader.carregarFonte();
        button = new Button(imagePath);
    }

    @Test
    void testConstructorWithImageOnly() {
        assertNotNull(button.getIcon());
        ImageIcon icon = (ImageIcon) button.getIcon();
        assertEquals(icon.getIconWidth(), button.getMinimumSize().width);
        assertEquals(icon.getIconHeight(), button.getMinimumSize().height);
        assertEquals(button.getMinimumSize(), button.getSize());
        assertEquals(button.getSize(), button.getPreferredSize());
        assertFalse(button.isBorderPainted());
        assertFalse(button.isContentAreaFilled());
        assertTrue(button.isVisible());
        assertEquals(Color.WHITE, button.getForeground());
    }

    @Test
    void testConstructorWithImageAndText() {
        button = new Button(imagePath, "PLAY");
        assertNotNull(button.getIcon());
        ImageIcon icon = (ImageIcon) button.getIcon();
        assertEquals(icon.getIconWidth(), button.getMinimumSize().width);
        assertEquals(icon.getIconHeight(), button.getMinimumSize().height);
        assertEquals(button.getMinimumSize(), button.getSize());
        assertEquals(button.getSize(), button.getPreferredSize());
        assertEquals("PLAY", button.getText());
        assertEquals(SwingConstants.CENTER, button.getHorizontalTextPosition());
        assertEquals(SwingConstants.HORIZONTAL, button.getVerticalTextPosition());
        assertFalse(button.isBorderPainted());
        assertFalse(button.isContentAreaFilled());
        assertTrue(button.isVisible());
        assertEquals(fontLoader.fonte, button.getFont());
        assertEquals(Color.WHITE, button.getForeground());
    }

    @Test
    void testConstructorWithImageTextAndScale() {
        int scaleWidth = 5;
        int scaleHeight = 30;
        button = new Button(imagePath, "PLAY", scaleWidth, scaleHeight);
        assertNotNull(button.getIcon());
        ImageIcon icon = (ImageIcon) button.getIcon();
        assertEquals(icon.getIconWidth(), button.getMinimumSize().width);
        assertEquals(icon.getIconHeight(), button.getMinimumSize().height);
        assertEquals(button.getMinimumSize(), button.getSize());
        assertEquals(button.getSize(), button.getPreferredSize());
        assertEquals("PLAY", button.getText());
        assertEquals(SwingConstants.CENTER, button.getHorizontalTextPosition());
        assertEquals(SwingConstants.HORIZONTAL, button.getVerticalTextPosition());
        assertFalse(button.isBorderPainted());
        assertFalse(button.isContentAreaFilled());
        assertTrue(button.isVisible());
        assertEquals(fontLoader.fonte, button.getFont());
        assertEquals(Color.WHITE, button.getForeground());
    }
}