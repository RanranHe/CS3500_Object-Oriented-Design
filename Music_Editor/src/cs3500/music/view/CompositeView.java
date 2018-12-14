package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.IMusic;

/**
 * Created by Bryan on 11/21/2015.
 */
public class CompositeView implements GuiView {

    private MidiViewImpl midi;
    private GuiViewFrame gui;
    private int delay;
    private int beat;
    private final int finalBeat;
    private boolean paused;

    public CompositeView(IMusic music, MidiViewImpl midi, GuiViewFrame gui) {
        this.midi = midi;
        this.gui = gui;
        this.beat = 0;
        this.delay = music.getTempo() / 1000;
        this.finalBeat = music.getTotalBeats();
        this.paused = true;
    }

    @Override
    public void view() throws IOException {
        gui.view();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if ((beat < finalBeat) && !paused) {
                    gui.updateDrawHead();
                    midi.playAtBeat(beat);
                    beat++;
                }
            }
        }, 0, delay);
    }

    @Override
    public void setKeyHandler(KeyListener k) {
        gui.setKeyHandler(k);
    }

    @Override
    public void setMouseHandler(MouseListener m) {
        gui.setMouseHandler(m);
    }

    @Override
    public void removeKeyHandler(KeyListener k) {
        gui.removeKeyHandler(k);
    }

    @Override
    public void removeMouseHandler(MouseListener m) {
        gui.removeMouseHandler(m);
    }

    @Override
    public void goToEnd() {
        gui.goToEnd();
    }

    @Override
    public void goToStart() {
        gui.goToStart();
    }

    @Override
    public void scrollRight() {
        gui.scrollRight();
    }

    public void swapState() {
        this.paused = !this.paused;
    }

    @Override
    public void update() {
        gui.update();
    }

    @Override
    public ArrayList<AbstractNote> getSelected() {
        return gui.getSelected();
    }

    @Override
    public void select(AbstractNote n) {
        gui.select(n);
    }

    @Override
    public void removeSelected() {
        gui.removeSelected();
    }

    @Override
    public void swapScrolling() {
        gui.swapScrolling();
    }

}
