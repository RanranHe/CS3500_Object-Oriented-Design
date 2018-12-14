package cs3500.model;

/**
 * Created by Claire on 2015/11/12.
 */
/**
 * Abstract representation of a Note Created by Jonathan on 11/8/2015.
 */
public abstract class ANote {
  protected NotePitch pitchtype;
  protected int octave;
  protected int start;
  protected int end;
  protected int instrument;
  protected int volume;
  protected int pitch;

  /**
   * @param type       is the type of note that it is
   * @param octave     is the pitch of the note
   * @param start  is the beat the note starts on
   * @param end    is the beat the note ends on
   * @param instrument this is the instrument number
   * @param volume     the volume of the node (not used right now)
   */
  protected ANote(NotePitch type, int octave, int start, int end, int instrument, int volume, int pitch) {
    if (type == null) {
      throw new IllegalArgumentException("Invalid Note");
    }
    this.pitchtype = type;
    this.octave = octave;
    this.start = start;
    this.end = end;
    this.instrument = instrument;
    this.volume = volume;
    this.pitch=pitch;
  }

  /**
   * The value of the note that MIDI takes
   * @return the int value between 0 and 127 of this note
   */
  public abstract int midiValue();

  /**
   * Get the type of the note
   * @return the type of the note
   */
  public NotePitch getPitch() {
    return this.pitchtype;
  }

  /**
   * get the start beat of the note
   * @return the start beat of the note
   */
  public int getStart() {
    return this.start;
  }

  /**
   * get the end beat of this note
   * @return the end beat of this note
   */
  public int getEnd() {
    return this.end;
  }

  /**
   * Get the octave of the note
   * @return the octave of the note
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Returns the instrument of the note
   * @return the instrument of this note
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Returns the volume of the note
   * @return the volume of this note
   */
  public int getVolume() {
    return this.volume;
  }



  /**
   * determine weather the two notes will overlap
   * @param note another note
   * @return weather there is already exist a note
   */
  public boolean isOccupied(ANote note) {
    boolean result = this.octave == note.getOctave() && this.getPitch() == note.getPitch();
    if (this.start <= note.getStart()) {
      return result && this.end >= note.getStart();
    } else {
      return result && note.getEnd() >= this.getStart();
    }
  }

  /**
   * determine whether two notes are exactly the same
   *
   * @param o another note
   * @return whether two notes are exactly the same
   */
  public abstract boolean sameNote(ANote an);

  /**
   * change the start of the note
   *
   * @param newStart a new start beat
   */
  void changeStart(int newStart) {
    if (newStart < 0 || newStart > end) {
      throw new IllegalArgumentException("Invalid start beat");
    }
    this.start = newStart;
  }

  /**
   * change the end beat of the note
   *
   * @param newEnd a new end beat
   */
  void changeEnd(int newEnd) {
    if (newEnd < 0 || newEnd < start) {
      throw new IllegalArgumentException("Invalid end beat");
    }
    this.end = newEnd;
  }

  /**
   * change the type of the note
   *
   * @param newType a new type of note
   */
  void changePitch(NotePitch newType) {
    this.pitchtype = newType;
  }

  /**
   * change the instrument of note
   * @param ins the new instrument
   */
  void changeInstrument(int ins) {
    if(ins<0) {
      throw new IllegalArgumentException("Invalid instrument.");
    }
    this.instrument = ins;
  }

  /**
   * change the volume of the note
   * @param newVolume the new volume
   */
  void changeVolume(int newVolume) {
    if (volume<0) {
      throw new IllegalArgumentException("Invalid volume.");
    }
    this.volume = newVolume;
  }

  /**
   * change the octave of a note
   *
   * @param newOctave a new note octave
   */
  void changeOctave(int newOctave) {
    if (newOctave <= 0 || newOctave > 10) {
      throw new IllegalArgumentException("Invalid octave");
    }
    this.octave = newOctave;
  }

public int getOldPitch() {
	// TODO Auto-generated method stub
	return this.pitch;
}

public boolean isSameNote(String pitch,int start){
	boolean same=false;
	String _pitch=this.pitchtype.toString()+this.octave;
	if(_pitch.equals(pitch)&&this.start==start){
		return true;
	}
	return same;
}

}