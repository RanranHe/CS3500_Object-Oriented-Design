package cs3500.music.event;

import cs3500.music.view.GuiView;

public class EndKeyRunnable implements Runnable {
  GuiView musicController;

  public EndKeyRunnable(GuiView musicController) {
    this.musicController = musicController;
  }

  @Override
  public void run() {
    this.musicController.goToEnd();
  }
}