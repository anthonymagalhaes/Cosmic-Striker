package meujogo.Modelo;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SoundPlayer {
    private Clip clip;

    public void setFile(URL resource) {
    	try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	
    public void play(boolean loop) {
    	if (clip != null) {
            if (clip.isRunning()) {
                return;  
            }
            
            clip.setFramePosition(0); 
            
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }

    
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    
    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
}