package cs3500.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs3500.util.CompositionBuilder;

/**
 * Created by Claire on 2015/11/12.
 */
// the class that implements the model
public class MusicEditorImpl implements MusicEditorModel {

  public void setFirst(boolean first) {
    this.isFirst = first;
  }

  // Initializes that next note added will be the first.
  private boolean isFirst;

  // The ArrayList of notes for each beat
  ArrayList<Beat> music;
  private int hiOct;
  private int loOct;
  private NotePitch hiPitch;
  private NotePitch loPitch;
  private Map<String, String> PitchMap;
  private int current_Beat;
  int tempo;
  public Map<String, String> getPitchMap(){
	  return this.PitchMap;
  }
  /**
   * musicalArray starts empty and can be changed either by adding a printedscore or by
   * individually adding notes
   */
  protected MusicEditorImpl() {
    current_Beat = 0;
    // the initial music list is empty, it can be changed by adding notes
    music = new ArrayList<Beat>();

    this.isFirst = true;
    PitchMap=new HashMap<String, String>();

    this.hiOct = 99;
    this.loOct = 99;
    this.hiPitch = NotePitch.Rest;
    this.loPitch = NotePitch.Rest;
  }

  @Override
  public void changeInstrument(ANote note, int instrument) {
    note.changeInstrument(instrument);
  }

  @Override
  public void changeVolume(ANote note, int volume) {
    note.changeVolume(volume);
  }

  public ArrayList<Beat> getMusic() {
    return music;
  }

  public void setMusic(ArrayList<Beat> music) {
    this.music = music;
  }

  public int getHighOctave() {
    return hiOct;
  }

  public int getTempo() {
    return this.tempo;
  }

  public int getLowOctave() {
    return loOct;
  }

  public NotePitch getHighPitch() {
    return hiPitch;
  }

  public NotePitch getLowPitch() {
    return loPitch;
  }

  public void setHighOctave(int hiOct) {
    this.hiOct = hiOct;
  }

  public void setLowOctave(int loOct) {
    this.loOct = loOct;
  }

  public void setHighPitch(NotePitch hiPitch) {
    this.hiPitch = hiPitch;
  }

  public void setLowPitch(NotePitch initLowPitch) {
    this.loPitch = initLowPitch;
  }

  private NotePitch numToPitch(int pitch) {
    switch (pitch) {
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
      case 12:
        return NotePitch.Rest;
      default:
        throw new Note.IllegalNoteException("No corresponding note");
    }
  }

  @Override
  public ANote createNote(NotePitch pitch, int octave, int start, int end, int volume,int oldpitch) {
    return Note.createNote(pitch, octave, start, end, 1, volume,oldpitch);
  }

  @Override
  public ArrayList<Beat> getScore() {
    return this.music;
  }

  @Override
  public void changeStart(ANote note, int newStart) {
    ANote origin = note;
    int originStart = note.getStart();
    note.changeStart(newStart);
    extendBoard(note);
    // if the new start beat is before the original one
    for (int j = originStart; j <= origin.getEnd(); j += 1) {
      this.music.get(j).getNotes().remove(origin);
    }
    if (!illegalEdit(note)) {
      for (int j = originStart; j <= origin.getEnd(); j += 1) {
        this.music.get(j).getNotes().add(note);
      }
      if (newStart < originStart) {
        for (int i = newStart; i < originStart; i += 1) {
          this.music.get(i).getNotes().add(note);
        }
      } else {
        for (int i = originStart; i < newStart; i += 1) {
          this.music.get(i).getNotes().remove(origin);

        }
      }
    } else {
      for (int i = originStart; i < newStart; i += 1) {
        this.music.get(i).getNotes().remove(origin);
      }
      throw new IllegalArgumentException("can't change");
    }
  }

  @Override
  public void changeEnd(ANote note, int newEnd) {
    int originEnd = note.getEnd();
    ANote origin = note;
    note.changeEnd(newEnd);
    extendBoard(note);
    // if the new end is later than origin one
    for (int j = origin.getStart(); j <= originEnd; j += 1) {
      this.music.get(j).getNotes().remove(origin);
    }
    if (!illegalEdit(note)) {
      for (int j = origin.getStart(); j <= originEnd; j += 1) {
        this.music.get(j).getNotes().add(note);
      }
      if (newEnd > originEnd) {
        for (int i = note.getEnd(); i < newEnd; i += 1) {
          this.music.get(i).getNotes().add(note);
        }
      } else {
        for (int i = newEnd; i < originEnd; i += 1) {
          this.music.get(i).getNotes().remove(origin);
        }
      }
    } else {
      for (int j = origin.getStart(); j <= originEnd; j += 1) {
        this.music.get(j).getNotes().add(origin);
      }
      throw new IllegalArgumentException("can't change!");
    }
  }

  @Override
  public void changeOctave(ANote note, int newOctave) {
    ANote origin = note;
    int originOctave = note.getOctave();
    if (newOctave < 1 && newOctave > 10) {
      throw new IllegalArgumentException("Illegal octave!");
    }
    note.changeOctave(newOctave);
    for (int j = note.getStart(); j <= note.getEnd(); j++) {
      this.music.get(j).getNotes().remove(origin);
    }
    if (illegalEdit(note)) {
      for (int j = note.getStart(); j <= note.getEnd(); j++) {
        this.music.get(j).getNotes().add(origin);
      }
      throw new IllegalArgumentException("can't change!");
    }
    for (int j = note.getStart(); j <= note.getEnd(); j++) {
      this.music.get(j).getNotes().add(note);
    }
    initialChange(note);
  }

  @Override
  public void changePitch(ANote note, NotePitch newPitch) {
    ANote origin = note;
    NotePitch originPitch = note.getPitch();
    note.changePitch(newPitch);
    for (int j = note.getStart(); j <= note.getEnd(); j++) {
      this.music.get(j).getNotes().remove(origin);
    }
    if (illegalEdit(note)) {
      for (int j = note.getStart(); j <= note.getEnd(); j++) {
        this.music.get(j).getNotes().add(origin);
      }
      throw new IllegalArgumentException("can't change!");
    }
    for (int j = note.getStart(); j <= note.getEnd(); j++) {
      this.music.get(j).getNotes().add(note);
    }
    initialChange(note);
  }

  /**
   * Adds or changes note to a beat
   *
   * @param note Note object
   * @param time unit of time
   */
  @Override
  public void setNote(ANote note, int time) {
	  //look2
	  extendBoard(note);
	  music.get(time).setNote(note);
  }

  @Override
  public void addNote(ANote moment) {
	  String picth=moment.getPitch().toString()+moment.getOctave();
	if(PitchMap.get(picth)==null){
		PitchMap.put(picth, picth);
	}
	//look1
    this.initialChange(moment);

    int head = moment.getStart();
    int tail = moment.getEnd();

    int i;
    for (i = head; i < tail; i++) {
      this.setNote(moment, i);
    }

  }

  /**
   * change the initial low/high Octave/NotePitch
   *
   * @param note the new note to be determined if it has lower/higher Octave/NotePitch than the
   *             initials
   */
  void initialChange(ANote note) {
    if (this.isFirst) {
      this.setHighPitch(note.getPitch());
      this.setLowPitch(note.getPitch());
      this.setHighOctave(note.getOctave());
      this.setLowOctave(note.getOctave());
      this.setFirst(false);
    } else {
      if (note.getOctave() >= this.hiOct) {
        this.setHighOctave(note.getOctave());
        if (note.getPitch().orderOfPitch() > this.hiPitch.orderOfPitch()) {
          this.setHighPitch(note.getPitch());
        }
      }
      if (note.getOctave() <= this.loOct) {
        this.setLowOctave(note.getOctave());
        if (note.getPitch().orderOfPitch() < this.loPitch.orderOfPitch()) {
          this.setLowPitch(note.getPitch());
        }
      }
    }
  }

  @Override
  public ANote getNote(NotePitch pitch, int octave, int beat) {
    ArrayList<ANote> beats = this.music.get(beat).getNotes();
    for (ANote n : beats) {
      if (n.getOctave() == octave && n.getPitch() == pitch) {
        return n;
      }
    }
    throw new IllegalArgumentException("No such note exists!");
  }
  
  public ANote getNote(String pitch,int beat){
	  ArrayList<ANote> beats = this.music.get(beat).getNotes();
	    for (ANote n : beats) {
	    	String _pitch=n.getPitch().toString()+n.getOctave();
	      if (_pitch.equals(pitch)) {
	        return n;
	      }
	    }
	    return null;
  }

  @Override
  public void changeCurrentBeat(int newBeat) {
    if (newBeat < 0) {
      throw new IllegalArgumentException("Illegal beat!");
    }
    current_Beat = newBeat;
  }

  @Override
  public void removeNote(ANote note) {
    for (int i = note.getStart(); i < note.getEnd(); i += 1) {
      music.get(i).getNotes().remove(note);
    }
  }

  @Override
  public Beat playMusic(int time) {
    current_Beat = time;

    this.current_Beat += 1;

    return music.get(current_Beat);
  }

  @Override
  public void combineScore(ArrayList<Beat> newScore) {
    music.addAll(newScore);
  }

  /**
   * to check if the edit is legal
   *
   * @param note the expected note
   * @return weather this edit can work
   */
  private boolean illegalEdit(ANote note) {
    extendBoard(note);
    for (int i = note.getStart(); i <= note.getEnd(); i += 1) {
      for (ANote n : this.music.get(i).getNotes()) {
        if (note.isOccupied(n)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * to see if the given note is out of bounds and needs more beats
   *
   * @param note a note need to be determined
   */
  private void extendBoard(ANote note) {
    int end = note.getEnd();
    int beatsize=music.size();
    //System.out.println(beatsize+" - "+end+":"+note.getPitch().toString());
    if (end > beatsize) {
      // create skeleton list
    int addl=end-beatsize;
      // Generate blank beats in tmp
      int j;
      for (j = 0; j < addl; j++) {
        this.music.add(new Beat());
      }

      // Fill in existing notes
      //for (Beat b : this.getMusic()) {
      //  tmp.add(b);
      //}

      // assign completed tmp to music
      //this.setMusic(tmp);
    }
  }

  @Override
  public ArrayList<String> notesInRange() {
    ArrayList<String> acc = new ArrayList<>();
    // String representation of the highest note
    String highestNote = this.hiPitch.toString() + Integer.toString(this.hiOct);
    String curNote = "";
    int curNoteVal = this.loPitch.orderOfPitch();
    int curNoteOct = this.loOct;
    while (!curNote.equals(highestNote)) {
      if (curNoteVal > 11) {
        curNoteVal = 0;
        curNoteOct += 1;
      }
      // Gets the Note string name and adds it to the current octave plus a space
      curNote = NotePitch.valueLookup(curNoteVal).toString()
              + Integer.toString(curNoteOct);
      acc.add(0, curNote);
      curNoteVal += 1;
    }
    // adds a new line character before returning it
    return acc;
  }

  @Override
  public int scoreLength() {
    return this.music.size();
  }

  @Override
  public int scoreHeight() {
    ANote highNote = createNote(this.hiPitch, hiOct, 0, 1, 100);
    ANote lowNote = createNote(this.loPitch, loOct, 0, 1, 100);
    return highNote.midiValue() - lowNote.midiValue() + 1;
  }

  @Override
  public void setTempo(int newTempo) {
    if (newTempo <= 0) {
      throw new IllegalArgumentException("Not a valid tempo");
    }
    this.tempo = newTempo;
  }

  public MusicEditorImpl createMusicEditor() {
    return new MusicEditorImpl();
  }
  // Builder to convert MusicEditorModel to CompositionBuilder interface
  public static final class Builder implements CompositionBuilder<MusicEditorModel> {

    MusicEditorModel m = new MusicEditorImpl();

    @Override
    public MusicEditorModel build() {
      return m;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> setTempo(int t) {
      m.setTempo(t);
      return this;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> addNote(int start, int end,
                                                        int instrument, int pitch,
                                                        int volume) {
      NotePitch p = NotePitch.getPitch(pitch);
      int octave = (pitch - 24) / 12;
//      System.out.println(pitch+":"+p.ordinal()+","+octave);
      ANote n = Note.createNote(p, octave, start, end-1, instrument, volume,pitch);
      m.addNote(n);
      return this;
    }
  }
@Override
public ANote createNote(NotePitch pitch, int octave, int start, int end,
		int volume) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public String numToString(Integer j) {
	// TODO Auto-generated method stub
	switch (j) {
	    case 9:
	        return "A";
	    case 10:
	        return "A#";
	    case 11:
	        return "B";
	    case 12:
	        return "S";
	    case 0:
	        return "C";
	    case 1:
	        return "C#";
	    case 2:
	        return "D";
	    case 3:
	        return "D#";
	    case 4:
	        return "E";
	    case 5:
	        return "F";
	    case 6:
	        return "F#";
	    case 7:
	        return "G";
	    case 8:
	        return "G#";
	    default:
	        throw new Note.IllegalNoteException("No note equivalent to int");
	}
}

}
