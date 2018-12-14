package cs3500.model;

import java.util.ArrayList;

/**
 * Created by Claire on 2015/11/1.
 */
// a class represents notes
public class Note extends ANote {
  private NotePitch pitchtype;
  private int octave;
  private int start;
  private int end;
  private int pitch;

  public static class IllegalNoteException extends IllegalArgumentException {
    public IllegalNoteException() {
      super();
    }
    public IllegalNoteException(String msg) {
      super(msg);
    }
  }

  /**
   * constructor for a note
   *
   * @param type       the note type
   * @param octave     pitch of a note
   * @param start      start beat ot a note
   * @param end        the end beat of a note
   * @param instrument the instrument played the note
   * @param volume     volume of the note
   */
  private Note(NotePitch type, int octave, int start, int end, int instrument, int volume,int pitch) {
    super(type, octave, start, end, instrument, volume,pitch);
    this.pitchtype=type;
    this.octave=octave;
    this.start=start;
    this.end=end;
    this.pitch=pitch;
  }

  /**
   * give a method to create a new note outside this class
   *
   * @param type       the note type
   * @param octave     pitch of a note
   * @param start      start beat ot a note
   * @param end        the end beat of a note
   * @param instrument the instrument played the note
   * @param volume     volume of the note
   * @throws IllegalArgumentException if one of octave, start, end is negative, or end is less than
   *                                  start, or octave is larger than 10
   */
  public static ANote createNote(NotePitch type, int octave, int start, int end,
                         int instrument, int volume,int pitch) {
    if (octave < 0 || octave > 10 || start < 0 || end < 0 || end < start ||
            type == null || volume < 0 || instrument < 0) {
    }
    return new Note(type, octave, start, end, instrument, volume,pitch);
  }

  @Override
  public int midiValue() {
    return (this.octave + 1) * 12 + this.pitchtype.orderOfPitch();
  }

  /**
   * Turn this note into string
   * @return String combines note type and octave
   */
  @Override
  public String toString() {
    return this.pitchtype.toString() + Integer.toString(this.octave);
  }

  /**
   * Overrides the equals method
   *
   * @param o object (hopefully a note to be checked)
   * @return true or false if it matches
   */
  @Override
  public boolean sameNote(ANote an) {
      return this.octave == an.octave &&
              this.start == an.start &&
              this.end == an.end &&
              this.pitchtype == an.pitchtype &&
              this.instrument == an.instrument &&
              this.volume == an.volume;

  }
}