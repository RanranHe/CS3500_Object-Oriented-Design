package cs3500.model;

import cs3500.MusicEditor;
import cs3500.model.*;

/**
 * Created by Claire on 2015/11/13.
 */
public class MusicFactory {

  public MusicEditorModel makeSong(ANote... notes) {

    MusicEditorImpl song = new MusicEditorImpl();
    boolean isFirst = true;

    for(ANote n: notes) {
      // Adds new note
      song.addNote(n);

      // updates hi/lo pitch and octave
      song.initialChange(n);
    }
    return song;
  }
}
