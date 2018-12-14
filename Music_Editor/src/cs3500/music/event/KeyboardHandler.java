package cs3500.music.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.TreeMap;

public class KeyboardHandler implements KeyListener  {
//	private IView newView;
	Map<Integer, Runnable> released = new TreeMap<Integer, Runnable>();
//	public KeyboardHandler(IView nv) {
//		this.newView=nv;
//		this.released = new TreeMap<Integer, Runnable>();
//	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("keyTyped:"+String.valueOf(e.getKeyChar()));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("keyPressed:"+String.valueOf(e.getKeyChar()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("keyReleased:"+String.valueOf(e.getKeyChar()));
		if (released.containsKey(e.getKeyCode())) {
	      released.get(e.getKeyCode()).run();
	    }
	}
	public void addRunnable(int keyCode, Runnable runnable) {
	   this.released.put(keyCode, runnable);
	}

}
