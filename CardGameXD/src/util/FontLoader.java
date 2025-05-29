package util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;


/*
 * Classe responsavel por criar a fonte 
 * @since 21/05/2025
 */
public class FontLoader{

	public Font fonte;

	public void carregarFonte() throws IOException, FontFormatException {
		InputStream is = getClass().getResourceAsStream("/fonts/Pixel Lofi.otf");
		fonte = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(18f);
	}
	
	public FontLoader() {
		/*
		 * função para carragar a fonte
		 * @trows IOException
		 * @trows FontFormatException
		 */
		try {
			carregarFonte();
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
}
