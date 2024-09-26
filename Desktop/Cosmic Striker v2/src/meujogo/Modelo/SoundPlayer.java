package meujogo.Modelo;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip clip;
    
    public SoundPlayer(String soundFilePath) {
        try {

            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            
          
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
   
    public void play(boolean loop) {
        if (clip != null) {
           
            if (clip.isRunning()) {
                clip.stop();
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
