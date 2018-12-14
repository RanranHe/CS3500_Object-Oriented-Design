package cs3500.music.event;

import cs3500.music.view.GuiView;

public class PausedKeyRunnable implements Runnable {
  GuiView musicController;

  public PausedKeyRunnable(GuiView musicController) {
    this.musicController = musicController;
  }

  @Override
  public void run() {
    this.musicController.swapState();
  }
}