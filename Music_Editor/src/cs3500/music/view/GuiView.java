package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import cs3500.music.model.AbstractNote;

/**
 * Represents a GuiView for our Controller
 */
public interface GuiView extends IView {

    /**
     * Sets the Frame's key Listener to the given KeyListener
     * @param k The KeyListener to set to
     */
    void setKeyHandler(KeyListener k);

    /**
     * Sets the Frame's Mouse Listener to the given MouseListener
     * @param m The MouseListener to set to
     */
    void setMouseHandler(MouseListener m);

    /**
     * Removes the installed KeyListener from this GuiView.
     * Throws an IllegalArgumentException if not found
     * @param k the KeyListener to remove
     */
    void removeKeyHandler(KeyListener k);

    /**
     * Removes the installed MouseListener from this GuiView.
     * Throws an IllegalArgumentException if not found
     * @param m the MouseListener to remove
     */
    void removeMouseHandler(MouseListener m);

    /**
     * Jumps to the end of this GuiView
     */
    void goToEnd();

    /**
     * Jumps to the beginning of a GuiView
     */
    void goToStart();

    /**
     * Scrolls the GuiView to the right
     */
    void scrollRight();

    void swapState();

    /**
     * Updates the State of the GuiView against the Model
     */
    void update();

    /**
     * Gets the list of All selected AbstractNotes in a GuiView
     * @return ArrayList of all Selected Abstract Notes
     */
    ArrayList<AbstractNote> getSelected();

    /**
     * Selects a given AbstractNote for the Controller.
     * Throws an IllegalArgumentException if note not found
     * @param n the note to be selected
     */
    void select(AbstractNote n);

    /**
     * Removes all Selected Notes
     */
    void removeSelected();

    /**
     * swaps between Auto-Scroll and Manual-Scrolling
     */
    void swapScrolling();
}
