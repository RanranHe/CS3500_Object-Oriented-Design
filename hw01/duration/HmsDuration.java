package cs3500.hw01.duration;

/**
 * Durations represented as hours, minutes, and seconds.
 */
public final class HmsDuration extends AbstractDuration {
    /**
     * Constructs a duration in terms of its length in hours, minutes, and seconds.
     *
     * @param hours   the number of hours
     * @param minutes the number of minutes
     * @param seconds the number of seconds
     * @throws IllegalArgumentException if any argument is negative
     */
    public HmsDuration(int hours, int minutes, int seconds) {
        this(inSeconds(hours, minutes, seconds));
        ensureHms(hours, minutes, seconds);
    }

    /**
     * Constructs a duration in terms of its length in seconds.
     *
     * @param inSeconds the number of seconds (non-negative)
     * @throws IllegalArgumentException {@code inSeconds} is negative
     */
    public HmsDuration(long inSeconds) {
        if (inSeconds < 0) {
            throw new IllegalArgumentException("must be non-negative");
        }

        seconds = secondsOf(inSeconds);
        minutes = minutesOf(inSeconds);
        hours = hoursOf(inSeconds);
    }

    private final int hours;
    private final int minutes;
    private final int seconds;

    @Override
    protected AbstractDuration fromSeconds(long seconds) {
        return new HmsDuration(seconds);
    }

    @Override
    public long inSeconds() {
        return inSeconds(hours, minutes, seconds);
    }

    @Override
    public String asHms() {
        return asHms(hours, minutes, seconds);
    }

    public String format(String template) {
        /**
         * Gets the HmsDuration in the form asked
         * @return a string that describes the duration in the form
         */
        String str = "";
        for (int i = 0; i < template.length(); i++) {
            if (template.substring(i, i + 1).compareTo("%") == 0) {
                if (template.substring(i + 1, i + 2).compareTo("t") == 0) {
                    str = str + String.format("%d", inSeconds());
                }
                if (template.substring(i + 1, i + 2).compareTo("h") == 0) {
                    str = str + String.format("%d", hours);
                }
                if (template.substring(i + 1, i + 2).compareTo("H") == 0) {
                    str = str + String.format("%02d", hours);
                }
                if (template.substring(i + 1, i + 2).compareTo("m") == 0) {
                    str = str + String.format("%d", minutes);
                }
                if (template.substring(i + 1, i + 2).compareTo("M") == 0) {
                    str = str + String.format("%02d", minutes);
                }
                if (template.substring(i + 1, i + 2).compareTo("s") == 0) {
                    str = str + String.format("%d", seconds);
                }
                if (template.substring(i + 1, i + 2).compareTo("%") == 0) {
                    str = str + "%";
                }
                if (template.substring(i + 1, i + 2).compareTo("S") == 0) {
                    str = str + String.format("%02d", seconds);
                }
                i += 1;
            } else {
                str = str + template.substring(i, i + 1);
            }
        }
        return str;
    }
}
