package meujogo.Modelo;
import java.io.*;

public class SalvarJogo {
    private static final String FILE_NAME = "highscore.txt";

    public static void saveHighScore(int highScore) {
    	try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))){
            writer.println(highScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadHighScore() {
    	File file = new File(FILE_NAME);
        if (!file.exists()) {
            return 0; 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
		return 0;
    }

    
}

