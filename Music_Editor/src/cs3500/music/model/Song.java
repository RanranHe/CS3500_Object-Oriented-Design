package cs3500.music.model;

import java.util.ArrayList;

import cs3500.model.ANote;
import cs3500.model.Beat;
import cs3500.model.MusicEditorModel;
import cs3500.model.Note;
import cs3500.model.NotePitch;

// An Adaptor class that connect Song in their view
// and MusicEditorImpl in our model
public class Song implements IMusic {
  private MusicEditorModel model;
  private ArrayList<String> pitchs;
  private static final int SCALE_X = 10, SCALE_Y = 10, START_X = 30, START_Y = 20;
  private int range;

  public Song(MusicEditorModel model) {
    this.model = model;
    this.initPitchs();
    this.range = this.getHighestPitch() - this.getLowestPitch() + 1;
  }

  @Override
  public void addNote(AbstractNote note) {
    int pitch = note.getPitch();
    int start = note.getStart();
    int end = note.getDuration() + start;
    int instrument = note.getInstrument();
    int volume = note.getVolume();
    NotePitch p = NotePitch.getPitch(pitch);
    int octave = (pitch - 24) / 12;
    ANote n = Note.createNote(p, octave, start, end - 1, instrument, volume, pitch);
    model.addNote(n);
  }

  @Override
  public void removeNote(AbstractNote note) {
    int pitch = note.getPitch();
    int start = note.getStart();
    int end = note.getDuration() + start;
    int instrument = note.getInstrument();
    int volume = note.getVolume();
    NotePitch p = NotePitch.getPitch(pitch);
    int octave = (pitch - 24) / 12;
    ANote n = Note.createNote(p, octave, start, end - 1, instrument, volume, pitch);
    model.removeNote(n);
  }

  @Override
  public void changeNote(AbstractNote note, int startBeat, int pitch) {
    NotePitch p = NotePitch.getPitch(pitch);
    int octave = (pitch - 24) / 12;
    ANote n = model.getNote(p, octave, startBeat);
    model.changeEnd(n, note.getDuration() + note.getStart());
    model.changeInstrument(n, note.getInstrument());
    model.changeOctave(n, octave);
    model.changePitch(n, p);
    model.changeStart(n, note.getStart());
    model.changeVolume(n, note.getVolume());

  }

  @Override
  public void changeDuration(AbstractNote note, int duration) {
    ANote n = this.getNoteBySEP(note);
    if (n != null) {
      model.changeEnd(n, duration + n.getStart());
    }
  }

  private ANote getNoteBySEP(AbstractNote note) {
    ANote anote = null;
    int i = 0, l = model.getMusic().size();
    for (; i < l; i++) {
      Beat b = model.getMusic().get(i);
      for (ANote n : b.getNotes()) {
        if (n.getOldPitch() == note.getPitch() && n.getStart() == note.getStart()) {
          anote = n;
          break;
        }
      }
    }
    return anote;
  }

  @Override
  public ArrayList<AbstractNote> flatten() {
    ArrayList<AbstractNote> r = new ArrayList<AbstractNote>();
    int i = 0, l = model.getMusic().size();
    for (; i < l; i++) {
      Beat b = model.getMusic().get(i);
      for (ANote n : b.getNotes()) {
        AbstractNote note = new AbstractNote(n);
        r.add(note);
      }
    }
    return r;
  }

  @Override
  public int getTempo() {
    return model.getTempo();
  }

  @Override
  public void setTempo(int tempo) {
    model.setTempo(tempo);
  }

  // inite pitches
  private void initPitchs() {
    pitchs = new ArrayList<String>();
    Integer i, j;
    //Map<String,String> PM=model.getPitchMap();
    // lowest key of lowest octave
    int loKey = model.getLowPitch().orderOfPitch();
    // highest key of highest octave
    int hiKey = model.getHighPitch().orderOfPitch();
    // highest octave
    int hiOct = model.getHighOctave();
    // lowest octave
    int loOct = model.getLowOctave();

//	    System.out.println(loOct+","+loKey+","+hiOct+","+hiKey);
    // Iterate through octaves
    for (i = loOct; i <= hiOct; ++i) {
      // initializes pitches in lowest octave
      int init;
      if (i == loOct) {
        init = loKey;
      } else {
        init = 0;
      }

      // initializes pitches in highest octave
      int term;
      if (i == hiOct) {
        term = hiKey;
      } else {
        term = 11;
      }
      // Iterate through keys
      for (j = init; j <= term; j++) {
        String pitch = model.numToString(j);
        String key = pitch + i.toString();
        //if(PM.get(key)!=null){
//	            	System.out.println(pitch+","+i);
        pitchs.add(key);
        //}
      }
    }


  }

  @Override
  public String displayPitches(int range) {
    String staff = "";
    for (String p : pitchs) {
      staff = (staff == "" ? "" : staff + " ") + p;
    }
    staff = staff + "\n";
    return staff;
  }

  @Override
  public String getPitchFromInt(int ind) {
    //System.out.println("getPitchFromInt:"+ind+","+pitchs.get(ind));
    NotePitch p = NotePitch.getPitch(ind);
    int octave = (ind - 24) / 12;
    return p.toString() + Integer.toString(octave);
  }

  public String getPitchByIdx(int idx) {
    String pitch = "";
    if (idx >= 0 && idx < range) {
      pitch = this.getPitchFromInt(this.getHighestPitch() - idx);
    }
    return pitch;
  }

  public AbstractNote getBeatByX(int beat, String pitch) {
    AbstractNote note = null;
    Beat b = model.getMusic().get(beat);
    for (ANote k : b.getNotes()) {
      if (pitch.equals(this.getPitchFromInt(k.getOldPitch()))) {
        System.out.println(pitch + ":::" + this.getPitchFromInt(k.getOldPitch()));
        return this.getNote(beat, k.getOldPitch());
      }
    }
    return note;
  }

  @Override
  public int getLowestPitch() {
//		return model.getLowPitch().orderOfPitch()+model.getLowOctave();
    int pitch = 127;
    int i = 0, l = model.getMusic().size();
    for (; i < l; i++) {
      Beat b = model.getMusic().get(i);
      for (ANote n : b.getNotes()) {
        if (n.getOldPitch() < pitch) {
          pitch = n.getOldPitch();
        }
      }
    }
    return pitch;
  }

  @Override
  public int getHighestPitch() {
//		return model.getHighPitch().orderOfPitch()+model.getHighOctave();
    int pitch = 0;
    int i = 0, l = model.getMusic().size();
    for (; i < l; i++) {
      Beat b = model.getMusic().get(i);
      for (ANote n : b.getNotes()) {
        if (n.getOldPitch() > pitch) {
          pitch = n.getOldPitch();
        }
      }
    }
    return pitch;
  }

  @Override
  public int getTotalBeats() {
    return model.getMusic().size();
  }

  @Override
  public boolean checkBeat(int beat) {
    Beat b = model.getMusic().get(beat);
    return b == null ? false : true;
  }

  @Override
  public ArrayList<AbstractNote> getNotesAtBeat(int beat) {
    ArrayList<AbstractNote> r = new ArrayList<AbstractNote>();
    Beat b = model.getMusic().get(beat);
    for (ANote n : b.getNotes()) {
      AbstractNote note = new AbstractNote(n);
      r.add(note);
    }
    return r;
  }

  @Override
  public AbstractNote getNote(int beat, int pitch) {
    AbstractNote note = null;
    Beat b = model.getMusic().get(beat);
    for (ANote n : b.getNotes()) {
      if (n.getOldPitch() == pitch) {
        note = new AbstractNote(n);
      }
    }
    return note;
  }

}
