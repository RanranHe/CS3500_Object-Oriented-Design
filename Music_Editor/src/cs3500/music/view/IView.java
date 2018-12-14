package cs3500.music.view;

import java.io.IOException;


/**
 * Represents A view for A piece of Music
 */
public interface IView {

    /**
     * Displays the IView to where it needs to be.  Will throw an IOexception for invalid file.
     * @throws IOException
     */
    void view() throws IOException;
}

