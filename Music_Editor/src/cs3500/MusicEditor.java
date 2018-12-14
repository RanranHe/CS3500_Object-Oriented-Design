package cs3500;

import cs3500.model.MusicEditorImpl;
import cs3500.model.MusicEditorModel;
import cs3500.model.MusicFactory;
import cs3500.util.MusicReader;
import cs3500.music.event.DelKeyRunnable;
import cs3500.music.event.EndKeyRunnable;
import cs3500.music.event.HomeKeyRunnable;
import cs3500.music.event.KeyboardHandler;
import cs3500.music.event.MouseEventRunnable;
import cs3500.music.event.MouseHandler;
import cs3500.music.event.PasteKeyRunnable;
import cs3500.music.event.PausedKeyRunnable;
import cs3500.music.model.Song;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import javax.sound.midi.InvalidMidiDataException;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;

public class MusicEditor {
  /**
   * play a piece of music by the desired view type console: output in text form midi: output in
   * audio form
   *
   * @param args the name of a piece, and the desired view
   * @throws IOException invalid inputs
   */

  public static void main(String[] args) throws IOException,
          InvalidMidiDataException, InterruptedException {
    //Define the songs, and a initial model
    MusicFactory initz = new MusicFactory();
    String basepath = "C:/Users/Claire/MusicTest/";
    Readable mary = new FileReader(basepath + "mary-little-lamb.txt");
    Readable mystery1 = new FileReader(basepath + "mystery-1.txt");
    Readable mystery2 = new FileReader(basepath + "mystery-2.txt");
    MusicEditorModel model = initz.makeSong();

    model = MusicReader.parseFile(mystery1, new MusicEditorImpl.Builder());
    String view = "controller";
    Song s = new Song(model);
    GuiView v = null;
    switch (view) {
      case ("gui"):
        v = new GuiViewFrame(s);
        break;
      case ("console"):
        v = (GuiView) new ConsoleView(s);
        break;
      case ("midi"):
        v = (GuiView) new MidiViewImpl(s);
        break;
      case ("controller"):
        GuiViewFrame v2 = new GuiViewFrame(s);
        MidiViewImpl v1 = new MidiViewImpl(s);
        v = new CompositeView(s, v1, v2);
        //Events
        KeyboardHandler keyListener = new KeyboardHandler();
        MouseHandler mouseListener = new MouseHandler();
        keyListener.addRunnable(KeyEvent.VK_HOME, new HomeKeyRunnable(v));
        keyListener.addRunnable(KeyEvent.VK_END, new EndKeyRunnable(v));
        keyListener.addRunnable(KeyEvent.VK_P, new PausedKeyRunnable(v));
        //copy
        keyListener.addRunnable(KeyEvent.VK_V, new PasteKeyRunnable(v));
        //Delete (I finished dealing with this event,
        //but the connector of deleting in their view actually doesn't work
        keyListener.addRunnable(KeyEvent.VK_D, new DelKeyRunnable(v));

        mouseListener.addRunnableOnMouse(MouseEvent.MOUSE_CLICKED, new MouseEventRunnable(v, s));

        v.setKeyHandler(keyListener);
        v.setMouseHandler(mouseListener);
        break;
      default:
        throw new IllegalArgumentException("Invalid input.");
    }
    if (v != null) v.view();
    System.out.println(view);
    //System.out.println(v.toString());
  }
}
