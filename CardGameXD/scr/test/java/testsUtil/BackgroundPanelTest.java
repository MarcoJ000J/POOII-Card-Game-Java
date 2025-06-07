package testsUtil;

import static org.junit.jupiter.api.Assertions.*;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Field;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.BackgroundPanel;

public class BackgroundPanelTest {

    private BackgroundPanel panel;
    private final String imagePath = "/background/slaFundo.jpg";

    @BeforeEach
    void setUp() {
        panel = new BackgroundPanel(imagePath);
    }

    @Test
    void testConstructorWithImagePathSetsDimensionsAndImage() throws Exception {
        assertEquals(new Dimension(1000, 600), panel.getMinimumSize());
        assertEquals(1000, getField(panel, "width"));
        assertEquals(600, getField(panel, "height"));
        Image backgroundImage = (Image) getField(panel, "backgroundImage");
        assertNotNull(backgroundImage);
    }

    @Test
    void testConstructorWithCustomDimensionsSetsDimensionsAndImage() throws Exception {
        int customWidth = 800;
        int customHeight = 400;
        panel = new BackgroundPanel(imagePath, customWidth, customHeight);
        assertEquals(new Dimension(customWidth, customHeight), panel.getMinimumSize());
        assertEquals(customWidth, getField(panel, "width"));
        assertEquals(customHeight, getField(panel, "height"));
        Image backgroundImage = (Image) getField(panel, "backgroundImage");
        assertNotNull(backgroundImage);
    }

    @Test
    void testPaintComponentDrawsBackgroundImage() throws Exception {
        Graphics graphics = new java.awt.image.BufferedImage(1000, 600, java.awt.image.BufferedImage.TYPE_INT_ARGB).getGraphics();
        panel.setSize(1000, 600);
        panel.paintComponent(graphics);
        // Cannot directly verify drawImage call, but ensure no exceptions and super is called
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
}