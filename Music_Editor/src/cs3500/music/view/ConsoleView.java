package cs3500.music.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.IMusic;

/**
 * Created by Bryan on 11/13/2015.
 */
public class ConsoleView implements IView {

    private Appendable output;
    private IMusic music;
    private int range, endBeat, low;

    public ConsoleView(IMusic music) {
        this.music = music;
        this.output = System.out;
        range = music.getHighestPitch() - music.getLowestPitch();
        endBeat = music.getTotalBeats();
        low = music.getLowestPitch();
    }

    public ConsoleView(StringBuilder s, IMusic music) {
        this.music = music;
        this.output = s;
        range = music.getHighestPitch() - music.getLowestPitch();
        endBeat = music.getTotalBeats();
        low = music.getLowestPitch();
    }

    @Override
    public void view() throws IOException {
        ArrayList<ArrayList<String>> lines = new ArrayList<>();

        output.append(music.displayPitches(range));
        output.append("\n");

        for (int k = 0; k < endBeat; k++) {
//            lines.add(k, new ArrayList<>());

            lines.get(k).add(String.format("%-5s", Integer.toString(k) + "  "));
            for (int j = 0; j < range + 1; j ++) {
                lines.get(k).add("   ");
            }
        }

        for (int l = 0; l < lines.size(); l++) {
            if (music.checkBeat(l)) {
                for (AbstractNote n : music.getNotesAtBeat(l)) {
                    int ind = n.getPitch() - low + 1;
                    String change = " X ";
                    lines.get(l).set(ind, change);

                    if (n.getDuration() > 1) {
                        for (int k = n.getDuration() - 1; k > 0; k--) {
                            lines.get(l+ k).set(ind, " | ");
                        }
                    }
                }
            }
        }

        for (ArrayList<String> beats : lines) {
            for (String set : beats) {
                output.append(set);
            }
            output.append("\n");
        }
    }

    public String toString() {
        return this.output.toString();
    }
}
