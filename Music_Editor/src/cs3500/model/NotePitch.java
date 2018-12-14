package cs3500.model;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Claire on 2015/11/3.
 */

/**
 * represents the types of notes in the music Editor
 */
public enum NotePitch {
  C("C", 0), CSharp("C#", 1),
  D("D", 2), DSharp("D#", 3),
  E("E", 4), F("F", 5), FSharp("F#", 6),
  G("G", 7), GSharp("G#", 8),
  A("A", 9), ASharp("A#", 10), B("B", 11),
  Rest("S", 12);

  // the type of note
  private final String type;
  // the order of the note in octave
  private final int order;
  // get a pitch from a int value
  private static final Map<Integer, NotePitch> valueLookup = new HashMap<Integer, NotePitch>();
  static {
    for (NotePitch t : NotePitch.values()) {
      valueLookup.put(t.orderOfPitch(), t);
    }
  }

  /**
   * @param type  the type of note
   * @param order the order of the note
   */
  NotePitch(String type, int order) {
    this.type = type;
    this.order = order;
  }

  /**
   * Represents the enum as a string
   *
   * @return the string note
   */
  @Override
  public String toString() {
    return type;
  }

  /**
   * get the order of pitch
   *
   * @return the order of pitch
   */
  public int orderOfPitch() {
    return order;
  }

  /**
   * Returns the NoteType based on value
   *
   * @param val the value of the NoteType in the order
   * @return the NoteType
   */
  public static NotePitch valueLookup(int val) {
    return valueLookup.get(val);
  }

  /**
   * Takes an int from [0,127] and which note type it represents
   *
   * @param p is an int value for a pitch
   * @returns a note type
   */
  public static NotePitch getPitch(int p) {
    // calculate the note type of given pitch
    int num = p>=12?(p % 12):p;

    switch (num) {
      case 0:
        return NotePitch.C;
      case 1:
        return NotePitch.CSharp;
      case 2:
        return NotePitch.D;
      case 3:
        return NotePitch.DSharp;
      case 4:
        return NotePitch.E;
      case 5:
        return NotePitch.F;
      case 6:
        return NotePitch.FSharp;
      case 7:
        return NotePitch.G;
      case 8:
        return NotePitch.GSharp;
      case 9:
        return NotePitch.A;
      case 10:
        return NotePitch.ASharp;
      case 11:
        return NotePitch.B;
      default:
        throw new IllegalArgumentException("Please give a correct pitch");
    }
  }
}
