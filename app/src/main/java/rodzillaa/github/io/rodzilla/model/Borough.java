package rodzillaa.github.io.rodzilla.model;

/**
 * Enum class consisting of New York Borough
 * classifications.
 */
public enum Borough {
    MANHATTAN("Manhattan"),
    STATENISLAND("Staten Island"),
    QUEENS("Queens"),
    BROOKLYN("Brooklyn"),
    BRONX("Bronx");

    private final String NAME;

    /**
     * Constructor for Borough enum.
     * @param NAME full name of the Borough.
     */
    Borough(final String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return NAME;
    }
}
