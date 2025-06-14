package util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

    private static Clip backgroundClip;

    public void startBackgroundMusic() throws IOException {
        try {
            String caminho = "/musicFiles/Origins.wav";

            //cristo
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(caminho));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audio);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();

            //nossaaaa
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de arquivo não suportado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de música.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Linha de áudio não disponível.");
            e.printStackTrace();
        }
    }

    /**
     * função respnsavel por terminar a musica, parando-a e liberando o espaço na memoria
     */
    public static void endBackgroundMusic() {
            backgroundClip.stop();
            backgroundClip.close();
    }
}