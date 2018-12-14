package cs3500.music.event;

import cs3500.music.view.GuiView;

public class HomeKeyRunnable implements Runnable {
  GuiView musicController;

  public HomeKeyRunnable(GuiView musicController) {
    this.musicController = musicController;
  }

  @Override
  public void run() {
    this.musicController.goToStart();
  }
}