package controller.dialog;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import com.darkprograms.speech.synthesiser.Synthesiser;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import net.sourceforge.javaflacencoder.FLACFileWriter;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DialogController {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Player player;
    private String API_KEY = "AIzaSyA92J6VyM-PmpUyl0NgU56IvXTizyRG-Uc";
    private Recognizer.Languages LANGUAGE = Recognizer.Languages.POLISH;
    private String LANGUAGE_CODE = "pl";

    private List<String> responseTextList = new ArrayList<>();

    public List<String> recordAndGetTextList(){
        Microphone mic = new Microphone(FLACFileWriter.FLAC);
        File file = recordToFile(mic);
        List<String> textList = soundFileToText(file, mic);
        return textList;
    }

    private List<String> soundFileToText(File file, Microphone mic) {
        responseTextList = new ArrayList<>();
        String responseText = "";
        Recognizer recognizer = new Recognizer(LANGUAGE, API_KEY);
        try {
            int maxNumOfResponses = 4;
            GoogleResponse response = recognizer.getRecognizedDataForFlac(file, maxNumOfResponses, (int)mic.getAudioFormat().getSampleRate());
            if(response.getResponse() != null){
                responseText = response.getResponse();
                System.out.println("Google Response: " + responseText);
                System.out.println("Google is " + Double.parseDouble(response.getConfidence())*100 + "% confident in the reply");
                System.out.println("Other Possible responses are: ");
                for(String s: response.getOtherPossibleResponses()){
                    System.out.println("\t" + s);
                    responseTextList.add(s);
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE,"ERROR: Google cannot be contacted");
            ex.printStackTrace();
        }
        file.deleteOnExit();
        responseTextList.add(responseText);
        return responseTextList;
    }

    private File recordToFile(Microphone mic) {
        File file = new File("testfile2.flac");
        try {
            mic.captureAudioToFile(file);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "ERROR: Microphone is not availible.", ex);
            ex.printStackTrace();
        }

	/* User records the voice here. Microphone starts a separate thread so do whatever you want
	 * in the mean time. Show a recording icon or whatever.
	 */
        try {
            System.out.println("Recording...");
            Thread.sleep(5000);//In our case, we'll just wait 5 seconds.
            mic.close();
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    mic.close();
//                }
//            }, 5000);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "ERROR: While recording.", ex);
            ex.printStackTrace();
        }

        mic.close();//Ends recording and frees the resources
        System.out.println("Recording stopped.");
        return file;
    }

    public void speechText(String text){
        logger.info("Speech text:  \"" + text + "\"");
        Synthesiser synthesiser = new Synthesiser();
        try {
            synthesiser.setLanguage(LANGUAGE_CODE);
            InputStream mp3Data = synthesiser.getMP3Data(text);
            player = new Player(mp3Data);
            player.play();
        } catch (IOException | JavaLayerException e) {
            logger.info("Speech text error");
            logger.info("Check internet connection");
            e.printStackTrace();
        }
    }
}
