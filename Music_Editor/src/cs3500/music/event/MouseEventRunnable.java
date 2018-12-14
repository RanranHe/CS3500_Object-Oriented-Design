package cs3500.music.event;

import java.awt.event.MouseEvent;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.Song;
import cs3500.music.view.GuiView;

public class MouseEventRunnable implements Runnable {
  GuiView musicController;
  Song song;
  private static final int SCALE_X = 10, SCALE_Y = 10, START_X = 30, START_Y = 20;

  public MouseEventRunnable(GuiView musicController, Song s) {
    this.musicController = musicController;
    this.song = s;
  }

  public void runFun(MouseEvent e) {
    System.out.println("MouseEventRunnable");
    AbstractNote note = this.getNoteByXY(e.getX(), e.getY());
    if (note != null) {
      System.out.println("Copy: " + note);
      musicController.select(note);
    }
  }

  private AbstractNote getNoteByXY(int x, int y) {
    AbstractNote note = null;
    int beat = 0;
    y = y - START_Y;
    x = x - START_X;
    int ix = x / SCALE_X;
    int iy = y / SCALE_Y;
    String pitch = song.getPitchByIdx(iy);
    note = song.getBeatByX(ix, pitch);
    System.out.println("pitch: " + pitch + "," + note);
    return note;
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
  }
}