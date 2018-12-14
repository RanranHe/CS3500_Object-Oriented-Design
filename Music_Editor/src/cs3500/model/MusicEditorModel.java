package cs3500.model;import java.awt.RenderingHints;
import java.lang.IllegalArgumentException;import java.util.ArrayList;

/**
 * Created by Claire on 2015/11/3.
 */
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Claire on 2015/11/3.
 */
public interface MusicEditorModel {
  /**
   * Makes a new note invariants: octave, start and end cannot be negative octave is less than or
   * equal to ten start is less than end
   *
   * @param pitch  which pitch of Note to be played
   * @param octave the pitch of the Note to be played
   * @param start  the start beat in the music of the Note
   * @param end    the end beat of the Note
   * @return a new Note created by the given information
   * @throws IllegalArgumentException if one of octave, startBeat and endBeat is negative
   * @throws IllegalArgumentException if octave is larger than ten
   * @throws IllegalArgumentException if start is larger than or equal to the end
   */
  ANote createNote(NotePitch pitch, int octave, int start, int end, int volume);

  /**
   * Changes the Start beat of a note
   *
   * @param note     the note to be changed
   * @param newStart the new start beat
   * @throws IllegalArgumentException if start is negative or larger than end if there are notes
   *                                  occupied at the newStart beat
   */
  void changeStart(ANote note, int newStart);

  /**
   * Changes the End beat of a note
   *
   * @param note   the note to be changed
   * @param newEnd the new end beat
   * @throws IllegalArgumentException if end is negative or less than start if the new note will
   *                                  overlap others
   */
  void changeEnd(ANote note, int newEnd);

  /**
   * Changes the octave of a note
   *
   * @param note      the note to be changed
   * @param newOctave the new octave
   * @throws IllegalArgumentException if octave is negative or octave is larger than ten if the new
   *                                  note will overlap others
   */
  void changeOctave(ANote note, int newOctave);

  /**
   * Changes the pitch of a note
   *
   * @param note     the note to be changed
   * @param newPitch the new pitch for the note
   */
  void changePitch(ANote note, NotePitch newPitch);

  /**
   * Gets the note based on pitch and octave during a certain beat
   *
   * @param pitch  the pitch of the note
   * @param octave the octave of the note
   * @param beat   the beat the note is on
   * @return the Note wanted
   * @throws IllegalArgumentException if no note matching those arguments
   */
  ANote getNote(NotePitch pitch, int octave, int beat);

  /**
   * Adds a Note into the board
   *
   * @param note the note to be added
   */
  void addNote(ANote note);

  /**
   * Outputs the musical array
   *
   * @return the musical array
   */
  ArrayList<Beat> getMusic();

  /**
   * Updates the current beat of the editor
   *
   * @param newBeat is the new beat to start on
   * @throws IllegalArgumentException if the beat is negative
   */
  void changeCurrentBeat(int newBeat);

  /**
   * Deletes a certain note from the board
   *
   * @param note is the note to be deleted
   * @throws IllegalArgumentException if no such note
   */
  void removeNote(ANote note);

  /**
   * get the list of notes needed to be played at this beat
   *
   * @param time current beat
   * @return the Array of notes to be played at this beat
   */
  Beat playMusic(int time);

  /**
   * combine the two scores together and just ignoring the same notes
   *
   * @param newScore score needed to be combined with current score
   */
  void combineScore(ArrayList<Beat> newScore);

  /**
   * Inserts note into beat
   *
   * @param note note object
   * @param time index of music
   */
  public void setNote(ANote note, int time);

  /**
   * change the instrument of the note
   *
   * @param note       the note need to be changed
   * @param instrument the instrument need to change to
   */
  void changeInstrument(ANote note, int instrument);

  /**
   * Sets the tempo
   *
   * @throws IllegalArgumentException if tempo is negative or 0
   */
  void setTempo(int newTempo);

  /**
   * All of the notes in the range
   *
   * @return List of the notes in the range
   */
  ArrayList<String> notesInRange();

  /**
   * change the volume of the note
   *
   * @param note   the note need to be change
   * @param volume the volume need to be change to
   */
  void changeVolume(ANote note, int volume);

  /**
   * The amount of beats in the array
   *
   * @return the length of the score in beats
   */
  int scoreLength();

  /**
   * The length of the range of notes
   *
   * @return the number of notes in the range
   */
  int scoreHeight();

  /**
   * Outputs the musical array
   *
   * @return the musical array
   */
  ArrayList<Beat> getScore();

  /**
   * Returns the tempo of the score
   *
   * @return the tempo
   */
  int getTempo();

ANote createNote(NotePitch pitch, int octave, int start, int end, int volume,
		int oldpitch);

Map<String, String> getPitchMap();

NotePitch getLowPitch();

NotePitch getHighPitch();

int getHighOctave();

int getLowOctave();

String numToString(Integer j);


}
