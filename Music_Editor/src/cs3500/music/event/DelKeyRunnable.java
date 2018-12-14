package cs3500.music.event;

import java.util.ArrayList;

import cs3500.music.model.AbstractNote;
import cs3500.music.view.GuiView;

public class DelKeyRunnable implements Runnable {
  GuiView musicController;

  public DelKeyRunnable(GuiView musicController) {
    this.musicController = musicController;
  }

  @Override
  public void run() {
    System.out.println("del: " + this.musicController.getSelected());
    this.musicController.removeSelected();
    this.musicController.update();
  }
}