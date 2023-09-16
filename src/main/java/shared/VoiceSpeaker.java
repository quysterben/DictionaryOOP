package shared;

import javafx.scene.control.Alert;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class VoiceSpeaker implements AutoCloseable {

    Synthesizer synthesizer;

    public VoiceSpeaker() {
        try {
            // set properties directly instead of placing property file on home
            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral("com.sun.speech.freetts"
                    + ".jsapi.FreeTTSEngineCentral");

            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

            synthesizer.allocate();
            synthesizer.resume();

        } catch (Exception ignored) {}
    }

    /**
     * Remember to check if the object of this class has been properly initiated.
     * @param args words
     */
    public void speak(String args) {
        try {
            synthesizer.speakPlainText(args, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

        } catch (Exception ignored) {}
    }

    /** Automatically deallocate synthesizer. */
    @Override
    public void close() {
        try {
            synthesizer.deallocate();
        } catch (Exception ignored) {}
    }

    public Alert getAlertInfo(String content, Alert.AlertType type) {
        final var notification = "Speech: " + content;
        final var a = new Alert(type);
        a.setContentText(notification);
        return a;
    }
}
