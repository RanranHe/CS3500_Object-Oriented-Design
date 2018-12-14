package cs3500.music.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.*;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.IMusic;
import cs3500.music.test.MockMidiReceiver;
import cs3500.music.test.MockMidiSynth;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements IView {
    private final Synthesizer synth;
    private final Receiver receiver;
    private final IMusic music;
    private final boolean test;

    public MidiViewImpl(IMusic music) {
        Synthesizer synth;
        Receiver receiver;
        try {
            synth = MidiSystem.getSynthesizer();
            receiver = synth.getReceiver();
            synth.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            synth = null;
            receiver = null;
        }

        this.music = music;
        this.synth = synth;
        this.receiver = receiver;
        this.test = false;
    }

    public MidiViewImpl(IMusic music, boolean test) {
        Synthesizer synth;
        Receiver receiver;

        if(test) {
            synth = new MockMidiSynth();
            receiver = new MockMidiReceiver(new StringBuilder());
        }
        else {
            try {
                synth = MidiSystem.getSynthesizer();
                receiver = synth.getReceiver();
                synth.open();
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
                synth = null;
                receiver = null;
            }
        }

        this.receiver = receiver;
        this.synth = synth;
        this.music = music;
        this.test = test;
    }
    /**
     * Relevant classes and methods from the javax.sound.midi library:
     * <ul>
     *  <li>{@link MidiSystem#getSynthesizer()}</li>
     *  <li>{@link Synthesizer}
     *    <ul>
     *      <li>{@link Synthesizer#open()}</li>
     *      <li>{@link Synthesizer#getReceiver()}</li>
     *      <li>{@link Synthesizer#getChannels()}</li>
     *    </ul>
     *  </li>
     *  <li>{@link Receiver}
     *    <ul>
     *      <li>{@link Receiver#send(MidiMessage, long)}</li>
     *      <li>{@link Receiver#close()}</li>
     *    </ul>
     *  </li>
     *  <li>{@link MidiMessage}</li>
     *  <li>{@link ShortMessage}</li>
     *  <li>{@link MidiChannel}
     *    <ul>
     *      <li>{@link MidiChannel#getProgram()}</li>
     *      <li>{@link MidiChannel#programChange(int)}</li>
     *    </ul>
     *  </li>
     * </ul>
     * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
     *   https://en.wikipedia.org/wiki/General_MIDI
     *   </a>
     */

    private void playNote(AbstractNote note, int tempo) throws InvalidMidiDataException {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument() - 1,
                note.getPitch(), note.getVolume());
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
                note.getPitch(), note.getVolume());
        this.receiver.send(start, note.getStart() * tempo);
        this.receiver.send(stop, this.synth.getMicrosecondPosition()
                + tempo * (note.getDuration() - 1));
        //this.receiver.close(); // Only call this once you're done playing *all* notes
    }

    public void view() throws IOException {
        ArrayList<AbstractNote> notes = music.flatten();

        for (AbstractNote n : notes) {
            try {
                playNote(n, music.getTempo());
            }
            catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }
        }

        if(!test) {
            try {
                Thread.sleep(music.getTotalBeats() * music.getTempo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void playAtBeat(int beat) {
        ArrayList<AbstractNote> notes = music.getNotesAtBeat(beat);

        for (AbstractNote n : notes) {
            try {
                playNote(n, music.getTempo());
            }
            catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        return this.receiver.toString();
    }
}
