package cs3500.model;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Claire on 2015/11/3.
 */

/**
 * represents the types of notes in the music Editor
 */
public enum Pitch {
  C("C", 0), CSharp("C#", 1),
  D("D", 2), DSharp("D#", 3),
  E("E", 4), F("F", 5), FSharp("F#", 6),
  G("G", 7), GSharp("G#", 8),
  A("A", 9), ASharp("A#", 10), B("B", 11);

  // the type of note
  private final String type;
  // the order of the note in octave
  private final int order;
  // get a pitch from a int value
  private static final Map<Integer, Pitch> valueLookup = new HashMap<Integer, Pitch>();
  static {
    for (Pitch t : Pitch.values()) {
      valueLookup.put(t.orderOfPitch(), t);
    }
  }

  /**
   * @param type  the type of note
   * @param order the order of the note
   */
  Pitch(String type, int order) {
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
  public static Pitch valueLookup(int val) {
    return valueLookup.get(val);
  }

  /**
   * Takes an int from [0,127] and which note type it represents
   *
   * @param p is an int value for a pitch
   * @returns a note type
   */
  public static Pitch getPitch(int p) {
    // calculate the note type of given pitch
    int num = p % 12;

    switch (num) {
      case 0:
        return Pitch.C;
      case 1:
        return Pitch.CSharp;
      case 2:
        return Pitch.D;
      case 3:
        return Pitch.DSharp;
      case 4:
        return Pitch.E;
      case 5:
        return Pitch.F;
      case 6:
        return Pitch.FSharp;
      case 7:
        return Pitch.G;
      case 8:
        return Pitch.GSharp;
      case 9:
        return Pitch.A;
      case 10:
        return Pitch.ASharp;
      case 11:
        return Pitch.B;
      default:
        throw new IllegalArgumentException("Please give a correct pitch");
    }
  }
}
