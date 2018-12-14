package cs3500.hw01.publication;

/**
 * Created by Claire on 2015/9/24.
 */

public class Webpage implements Publication {

    private final String title, url, retrieved;

    /**
     * Constructs a (@codeWebpage) object
     *
     * @param title     the title of webpage
     * @param url       the url of webpage
     * @param retrieved the url of webpage
     */
    public Webpage(String title, String url, String retrieved) {
        this.title = title;
        this.url = url;
        this.retrieved = retrieved;
    }

    /**
     * formats the current citation in APA style
     *
     * @return the formatted citation
     */
    @Override
    public String citeApa() {
        return this.title + ". (" + this.retrieved + "). " + this.url;
    }

    /**
     * formates the current citation in Mla style
     *
     * @return the formatted citation
     */
    @Override
    public String citeMla() {
        return "\"" + this.title + "\". " + this.retrieved + ". <" + this.url + ">.";
    }
}
