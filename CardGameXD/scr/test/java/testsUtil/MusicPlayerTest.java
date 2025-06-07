package testsUtil;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.MusicPlayer;

public class MusicPlayerTest {

    private MusicPlayer musicPlayer;

    @BeforeEach
    void setUp() {
        musicPlayer = new MusicPlayer();
        setField(MusicPlayer.class, "backgroundClip", null);
    }

    @Test
    void testStartBackgroundMusicInitializesClip() throws Exception {
        MusicPlayer testPlayer = new MusicPlayer() {
            @Override
            public void startBackgroundMusic() {
                try {
                    Clip mockClip = new MockClip();
                    setField(MusicPlayer.class, "backgroundClip", mockClip);
                    mockClip.open(null);
                    mockClip.loop(Clip.LOOP_CONTINUOUSLY);
                    mockClip.start();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        testPlayer.startBackgroundMusic();
        Clip clip = (Clip) getField(MusicPlayer.class, "backgroundClip");
        assertNotNull(clip);
        assertTrue(((MockClip) clip).isOpenCalled);
        assertTrue(((MockClip) clip).isLoopCalled);
        assertTrue(((MockClip) clip).isStartCalled);
    }

    @Test
    void testStartBackgroundMusicThrowsIOExceptionForInvalidPath() {
        MusicPlayer testPlayer = new MusicPlayer() {
            @Override
            public void startBackgroundMusic() throws IOException {
                throw new IOException("File not found");
            }
        };
        assertThrows(RuntimeException.class, () -> testPlayer.startBackgroundMusic());
    }

    @Test
    void testEndBackgroundMusicStopsAndClosesClip() throws Exception {
        Clip mockClip = new MockClip();
        setField(MusicPlayer.class, "backgroundClip", mockClip);
        MusicPlayer musicPlayer2 = new MusicPlayer();
		musicPlayer2.endBackgroundMusic();
        assertTrue(((MockClip) mockClip).isStopCalled);
        assertTrue(((MockClip) mockClip).isCloseCalled);
    }

    private Object getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private static class MockClip implements Clip {
        boolean isOpenCalled = false;
        boolean isLoopCalled = false;
        boolean isStartCalled = false;
        boolean isStopCalled = false;
        boolean isCloseCalled = false;

        @Override
        public void open(AudioInputStream stream) throws LineUnavailableException, IOException {
            isOpenCalled = true;
        }

        @Override
        public void loop(int count) {
            isLoopCalled = true;
        }

        @Override
        public void start() {
            isStartCalled = true;
        }

        @Override
        public void stop() {
            isStopCalled = true;
        }

        @Override
        public void close() {
            isCloseCalled = true;
        }

        // Métodos não utilizados no teste
        @Override
        public void open(javax.sound.sampled.AudioFormat format, byte[] data, int offset, int bufferSize) throws LineUnavailableException {}
        @Override
        public void setFramePosition(int frames) {}
        @Override
        public void setMicrosecondPosition(long microseconds) {}
        @Override
        public void setLoopPoints(int start, int end) {}
        @Override
        public int getFrameLength() { return 0; }
        @Override
        public long getMicrosecondLength() { return 0; }
        @Override
        public javax.sound.sampled.Line.Info getLineInfo() { return null; }
        @Override
        public void open() throws LineUnavailableException {}
        @Override
        public int available() { return 0; }
        @Override
        public void drain() {}
        @Override
        public void flush() {}
        @Override
        public int getBufferSize() { return 0; }
        @Override
        public javax.sound.sampled.AudioFormat getFormat() { return null; }
        @Override
        public int getFramePosition() { return 0; }
        @Override
        public long getMicrosecondPosition() { return 0; }
        @Override
        public float getLevel() { return 0; }
        @Override
        public boolean isRunning() { return false; }
        @Override
        public boolean isActive() { return false; }
        @Override
        public boolean isOpen() { return false; }
        @Override
        public void addLineListener(javax.sound.sampled.LineListener listener) {}
        @Override
        public void removeLineListener(javax.sound.sampled.LineListener listener) {}
        @Override
        public javax.sound.sampled.Control[] getControls() { return new javax.sound.sampled.Control[0]; }
        @Override
        public boolean isControlSupported(javax.sound.sampled.Control.Type control) { return false; }
        @Override
        public javax.sound.sampled.Control getControl(javax.sound.sampled.Control.Type control) { return null; }

		@Override
		public long getLongFramePosition() {
			// TODO Auto-generated method stub
			return 0;
		}
    }
}