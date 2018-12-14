package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.IMusic;

/**
 * Represents a Panel for the Gui Display
 */
public class ConcreteGuiViewPanel extends JPanel {

    JPanel contentPane;
    private IMusic music;
    private int beats, range;
    protected int curBeat;
    private static final int SCALE_X = 10, SCALE_Y = 10, START_X = 30, START_Y = 20;
    protected ArrayList<AbstractNote> selected;

    public ConcreteGuiViewPanel(IMusic music, int beat) {
        this.contentPane = new JPanel(new BorderLayout());
        this.music = music;
        this.beats = music.getTotalBeats();
        this.range = music.getHighestPitch() - music.getLowestPitch() + 1;
        this.curBeat = beat;
        this.setFocusable(true);
        selected = new ArrayList<>();
    }

    public ArrayList<AbstractNote> getSelected() {
        return selected;
    }

    public void setSelected(AbstractNote note) {
        if (selected.contains(note)) {
            selected.remove(note);
        }
        else {
            selected.add(note);
        }
    }

    public void removeSelected() {
        selected.removeAll(selected);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.setGrid(g);
        this.setBeats(g);
        this.displayPitches(g);
        this.displayNotes(g);
        this.drawRedLine(g);
    }

    /**
     * displays the pitches
     */
    private void displayPitches(Graphics g) {
        for (int k = 0; k < range; k++) {
        	String ps=music.getPitchFromInt(music.getHighestPitch() - k);
            g.drawString(ps, START_X - 25,
                    k * SCALE_Y + START_Y + (START_Y / 2) );
//        	System.out.println(music.getHighestPitch()+" - "+k+" : "+ps);
        }

    }

    /**
     * Displays the grid for this song
     */
    private void setGrid(Graphics g) {
        int width = beats * SCALE_X, height = range * SCALE_Y;
        g.drawRect(START_X, START_Y, width, height);

        for (int i = 1; i <= range; i++) {
            g.drawLine(START_X, START_Y + i * SCALE_Y, START_X + width, START_Y + i * SCALE_Y);
        }

        for (int i = 1; i <= beats; i++) {
            if (i % 4 == 0) {
                g.drawLine(START_X + i * SCALE_X, START_Y,
                        START_X + i * SCALE_X, START_Y + height);
            }
        }
    }

    /**
     * Displays all of the beats
     */
    private void setBeats(Graphics g) {
        for (int k = 0; k < beats; k++) {
            if (k % 4 == 0) {
                g.drawString(Integer.toString(k), k * SCALE_X + START_X, START_Y - 2);
            }
        }
    }

    /**
     * Displays all of the Notes, with Black as the start, and Green as the duration
     */
    private void displayNotes(Graphics g) {
        ArrayList<AbstractNote> notes = this.music.flatten();
        int high = music.getHighestPitch();
        for (AbstractNote k : notes) {

            g.setColor(Color.black);
            int xval = (k.getStart() * SCALE_X) + START_X;
            int yval = ((high - k.getPitch()) * SCALE_Y) + START_Y;

            if (selected.contains(k)){
                g.setColor(Color.red);
            }
            g.fillRect(xval, yval, SCALE_X, SCALE_Y);

            if(k.getDuration() > 1) {
                g.setColor(Color.green);
                for (int j = k.getDuration() - 1; j > 0; j--) {
                    g.fillRect(((k.getStart() + j) * SCALE_X) + START_X, yval, SCALE_X, SCALE_Y);
                }
                g.setColor(Color.black);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((beats * SCALE_X) + (START_X * 2) + 10,
                (range * SCALE_Y) + (START_Y * 4));
    }

    public void drawRedLine(Graphics g) {
        g.setColor(Color.red);
        g.drawLine(START_X + (curBeat * SCALE_X), START_Y, START_X + (curBeat * SCALE_X),
                (range * SCALE_Y) + START_Y);
    }

    public void updateRedLine() {
        this.curBeat++;
    }

    public static int getScaleX() {
        return SCALE_X;
    }

    public static int getScaleY() {
        return SCALE_Y;
    }

    public void recalcRange() {
        this.range = music.getHighestPitch() - music.getLowestPitch() + 1;
    }

    public void recalcEnd() {
        this.beats = music.getTotalBeats();
    }

    public int getMaxWidth() { return START_X + (beats * SCALE_X); }
}
