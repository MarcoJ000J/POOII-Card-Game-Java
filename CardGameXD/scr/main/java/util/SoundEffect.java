package util;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
	 public static void playSoundEffect(String soundPath) {
	        try {
	            // Obter o URL do recurso, ideal para arquivos dentro do JAR
	            URL soundUrl = SoundEffect.class.getResource(soundPath);
	            if (soundUrl == null) {
	                System.err.println("Erro: Arquivo de som não encontrado no caminho: " + soundPath);
	                return;
	            }

	            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioStream);

	            // Adiciona um Listener para fechar o Clip após a reprodução
	            clip.addLineListener(event -> {
	                if (event.getType() == LineEvent.Type.STOP) {
	                    clip.close();
	                }
	            });

	            clip.start(); // Toca o som
	        } catch (UnsupportedAudioFileException e) {
	            System.err.println("Formato de arquivo de som não suportado: " + soundPath);
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.err.println("Erro de I/O ao carregar o arquivo de som: " + soundPath);
	            e.printStackTrace();
	        } catch (LineUnavailableException e) {
	            System.err.println("Linha de áudio não disponível para reproduzir o som: " + soundPath);
	            e.printStackTrace();
	        }
	    }
}
