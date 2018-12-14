package cs3500.music.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;
import java.util.TreeMap;

public class MouseHandler implements MouseListener, MouseMotionListener {
  //	private IView newView;
  Map<Integer, Runnable> released = new TreeMap<Integer, Runnable>();
  ;

  //	public MouseHandler(IView nv) {
//		this.newView=nv;
//		this.released = new TreeMap<Integer, Runnable>();
//	}
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
//		System.out.println("mouseClicked:"+e.getX()+","+e.getY());
    if (released.containsKey(e.getID())) {
      MouseEventRunnable mrun = (MouseEventRunnable) released.get(e.getID());
      mrun.runFun(e);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
//		System.out.println("mousePressed:"+e.getX()+","+e.getY());
    if (released.containsKey(e.getID())) {
      released.get(e.getID()).run();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
//		System.out.println("mouseReleased:"+e.getX()+","+e.getY());
    if (released.containsKey(e.getID())) {
      MouseEventRunnable mrun = (MouseEventRunnable) released.get(e.getID());
      mrun.runFun(e);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
//		System.out.println("mouseEntered:"+e.getX()+","+e.getY());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
//		System.out.println("mouseExited:"+e.getX()+","+e.getY());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  public void addRunnableOnMouse(int mouseClicked, Runnable runnable) {
    this.released.put(mouseClicked, runnable);
  }

}