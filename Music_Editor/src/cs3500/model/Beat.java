package cs3500.model;

import java.util.ArrayList;

/**
 * Created by Andrew Mark Hamilton Singley on 11/2/2015.
 */
public class Beat {

    ArrayList<ANote> notes = new ArrayList<ANote>();

    int hiKey;
    int hiOct;
    int loOct;
    int loKey;

    /**
     * Constructor for Beat
     * @param notes ArrayList of Notes
     */
    protected Beat(ArrayList<ANote> notes) {
        this.notes = notes;
    }

    /**
     * Constructor for Beat
     * @param ns Collection of Notes
     */
    protected Beat(ANote... ns) {
        for (ANote n : ns) {
            notes.add(n);
        }
    }

    /**
     * Getter for notes
     * @return
     */
    public ArrayList<ANote> getNotes() {
        return notes;
    }

    protected void setNote(ANote note) {
        notes.add(note);
    }
}
