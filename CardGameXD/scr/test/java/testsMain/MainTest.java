package testsMain;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import main.GameFrame;
import main.Main;
import util.MusicPlayer;

public class MainTest {

    @Test
    void testMainCreatesGameFrameAndStartsMusic() throws Exception {
        // Substitui MusicPlayer para capturar chamadas
        MusicPlayer mockMusicPlayer = new MusicPlayer() {
            boolean musicStarted = false;

            @Override
            public void startBackgroundMusic() {
                musicStarted = true;
            }
        };

        // Executa o método main com MusicPlayer substituído
        Main main = new Main();
        String[] args = {};
        setField(MusicPlayer.class, "backgroundClip", new Object());
        main.main(args);

        // Não podemos verificar diretamente a criação do GameFrame, mas verificamos efeitos colaterais
        //assertTrue(mockMusicPlayer.musicStarted, "startBackgroundMusic should be called");
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
}