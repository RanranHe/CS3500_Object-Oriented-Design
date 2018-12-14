
package cs3500.music.view;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.IMusic;


/**
 * A skeleton Frame (i.e., a window) in Swing
 */

public class GuiViewFrame extends javax.swing.JFrame implements GuiView {

    private final ConcreteGuiViewPanel displayPanel;
    private IMusic music;
    private JScrollPane scroll;
    private boolean scrolling = false;

     /**
     * Creates new GuiView
     */

    public GuiViewFrame(IMusic music) {
        this.music = music;

        this.displayPanel = new ConcreteGuiViewPanel(music, 0);

        scroll = new JScrollPane(this.displayPanel);

        scroll.getHorizontalScrollBar().setUnitIncrement(ConcreteGuiViewPanel.getScaleX());
        scroll.getVerticalScrollBar().setUnitIncrement(ConcreteGuiViewPanel.getScaleY());

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(scroll);

        this.pack();
    }

    @Override
    public void update() {
        this.displayPanel.recalcEnd();
        this.displayPanel.recalcRange();

        this.repaint();
    }

    @Override
    public void select(AbstractNote n) {
        displayPanel.setSelected(n);
    }

    @Override
    public void removeSelected() { displayPanel.removeSelected(); }

    public void initialize() {
        this.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return displayPanel.getPreferredSize();
    }


    @Override
    public void view() throws IOException {
        this.initialize();
    }


    public void updateDrawHead() {
        boolean needScrolling = false;

        if (displayPanel.getMaxWidth() > this.getWidth()) {
            needScrolling = true;
        }

        if (needScrolling && scrolling) {
            this.scrollRight();
        }

        this.displayPanel.updateRedLine();

        this.repaint();
    }

    @Override
    public void setKeyHandler(KeyListener k) {
        this.displayPanel.setFocusable(true);
        this.displayPanel.addKeyListener(k);
    }

    @Override
    public void setMouseHandler(MouseListener m) {
        this.displayPanel.setFocusable(true);
        this.displayPanel.addMouseListener(m);
    }

    @Override
    public void removeKeyHandler(KeyListener k) {
        this.removeKeyListener(k);
    }

    @Override
    public void removeMouseHandler(MouseListener m) {
        this.removeMouseListener(m);
    }

    @Override
    public void goToEnd() {
        scroll.getHorizontalScrollBar().setValue(
                scroll.getHorizontalScrollBar().getMaximum());
    }

    @Override
    public void goToStart() {
        scroll.getHorizontalScrollBar().setValue(0);
    }

    @Override
    public void scrollRight() {
        int point = scroll.getHorizontalScrollBar().getValue();

        scroll.getHorizontalScrollBar().setValue(point + 10);
    }

    @Override
    public void swapState() {

    }

    @Override
    public ArrayList<AbstractNote> getSelected() {
        return displayPanel.getSelected();
    }

    @Override
    public void swapScrolling() { scrolling = !scrolling; }
}

