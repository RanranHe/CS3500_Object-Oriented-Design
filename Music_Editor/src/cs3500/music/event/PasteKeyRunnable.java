package cs3500.music.event;

import java.util.ArrayList;

import cs3500.music.model.AbstractNote;
import cs3500.music.view.GuiView;

public class PasteKeyRunnable implements Runnable {
  GuiView musicController;

  public PasteKeyRunnable(GuiView musicController) {
    this.musicController = musicController;
  }

  @Override
  public void run() {
    ArrayList<AbstractNote> notes = this.musicController.getSelected();
    AbstractNote n = notes.get(notes.size() - 1);
    //this.musicController.update();
  }
}