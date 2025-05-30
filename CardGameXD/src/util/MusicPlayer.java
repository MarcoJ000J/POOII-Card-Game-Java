package util;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class MusicPlayer {

    private static Clip backgroundClip;

    public void startBackgroundMusic() {
        try {
            File caminho = new File("res\\musicFiles\\Origins.wav");
            
            //cristo
            AudioInputStream audio = AudioSystem.getAudioInputStream(caminho);
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

    /*
     * função respnsavel por terminar a musica, parando-a e liberando o espaço na memoria
     */
    public static void endBackgroundMusic() {
            backgroundClip.stop();
            backgroundClip.close();
    }
}