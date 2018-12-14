package cs3500.music.model;

import java.util.ArrayList;

/**
 * Created by Bryan on 11/2/2015.
 *
 * Interface for editing a Song.
 */
public interface IMusic {

    /**
     * Adds the Given Abstract Note to the Song.  Will allocate enough space if the Note
     * does not fit in this Music.
     *
     * @param note the AbstractNote to be added to the Music piece
     */
    void addNote(AbstractNote note);

    /**
     * Removes the given AbstractNote  from the music. Will throw an IllegalArgumentException if
     * the given AbstractNote is not in the MusicPiece
     * @param note the note to be removed.
     */
    void removeNote(AbstractNote note);

    /**
     * Changes the pitch of a note.  Will throw an IllegalArgumentException if Note to change is
     * not in the Music
     *
     * @param note the note to be changed.
     * @param startBeat the startbeat to change to
     * @param pitch the pitch to change to
     * @throws IllegalArgumentException Note is not in music, or invalid pitch.
     */
    void changeNote(AbstractNote note, int startBeat, int pitch);

    /**
     * Changes the duration of given AbstractNote to the given Duration.
     *
     * @param note the note to be changed
     * @param duration the duration to change to
     *
     */
    void changeDuration(AbstractNote note, int duration);

    /**
     * Returns all of the ABstract Notes in this music piece and returns it as an ArrayList
     * @return an ArrayList of all AbstractNotes
     */
    ArrayList<AbstractNote> flatten();

    /**
     * Gets the tempo of the Music
     * @return the tempo of the Music.
     */
    int getTempo();

    /**
     * Sets the tempo of the Music to the given tempo
     * @param tempo the tempo to change to.
     * @throws IllegalArgumentException for invalid tempo
     */
    void setTempo(int tempo);

    /**
     * Gets all of the pitches for the given Range and returns it as a String
     * @param range the range
     * @return a String of the pitches
     */
    String displayPitches(int range);

    /**
     * Returns the String pitch equivalent of the given index
     * @param ind the index to get
     * @return a String of the index in pitch form
     */
    String getPitchFromInt(int ind);

    /**
     * Gets the index of the lowest pitch in this Music
     * @return lowest index
     */
    int getLowestPitch();

    /**
     * Gets the index of the highest pitch in this Music
     * @return highest index
     */
    int getHighestPitch();

    /**
     * gets the total number of beats played in this music, including duration
     * @return the total number of beats
     */
    int getTotalBeats();

    /**
     * checks if a given beat has any Notes beginning on it
     * @param beat beat to check
     * @return if there are any Notes starting at beat
     */
    boolean checkBeat(int beat);

    /**
     * Gets and returns an ArrayList of AbstractNotes playing at given beat
     * @param beat the beat to get all notes
     * @return Arraylist of all notes at beat
     */
    ArrayList<AbstractNote> getNotesAtBeat(int beat);

    /**
     * Gets the note at the given Pitch and Beat value.  Throws an ArgumentException  if there is
     * no note at the given beat.
     * @param beat the starting beat note.
     * @param pitch the pitch in int value
     * @return The note with the beat and pitch.
     */
    AbstractNote getNote(int beat, int pitch);
}
