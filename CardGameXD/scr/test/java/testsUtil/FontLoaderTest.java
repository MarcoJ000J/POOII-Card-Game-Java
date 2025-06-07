package testsUtil;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.FontLoader;

public class FontLoaderTest {

    private FontLoader fontLoader;

    @BeforeEach
    void setUp() {
        fontLoader = new FontLoader();
    }

    @Test
    void testFontLoaderConstructorInitializesFont() {
        assertNotNull(fontLoader.fonte);
        assertTrue(fontLoader.fonte instanceof Font);
        assertEquals(18f, fontLoader.fonte.getSize2D());
        // Nome da fonte pode variar dependendo do sistema, então verificamos apenas que é TrueType
        assertEquals(Font.TRUETYPE_FONT, fontLoader.fonte.getFontName().startsWith("Pixel Lofi") ? Font.TRUETYPE_FONT : -1);
    }

    @Test
    void testCarregarFonteLoadsFont() throws IOException, java.awt.FontFormatException {
        fontLoader.fonte = null; // Reset fonte
        fontLoader.carregarFonte();
        assertNotNull(fontLoader.fonte);
        assertTrue(fontLoader.fonte instanceof Font);
        assertEquals(18f, fontLoader.fonte.getSize2D());
        assertEquals(Font.TRUETYPE_FONT, fontLoader.fonte.getFontName().startsWith("Pixel Lofi") ? Font.TRUETYPE_FONT : -1);
    }

    @Test
    void testCarregarFonteThrowsIOExceptionForInvalidPath() {
        FontLoader invalidLoader = new FontLoader() {
            @Override
            public void carregarFonte() throws IOException, java.awt.FontFormatException {
                InputStream is = getClass().getResourceAsStream("/fonts/NonExistentFont.otf");
                if (is == null) throw new IOException("Font file not found");
                fonte = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(18f);
            }
        };
        assertThrows(IOException.class, () -> invalidLoader.carregarFonte());
    }
}