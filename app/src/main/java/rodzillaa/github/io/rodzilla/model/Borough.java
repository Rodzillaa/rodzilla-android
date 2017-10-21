package rodzillaa.github.io.rodzilla.model;

public enum Borough {
    MANHATTAN("Manhattan"),
    STATENISLAND("Staten Island"),
    QUEENS("Queens"),
    BROOKLYN("Brooklyn"),
    BRONX("Bronx");

    private final String ABBREV;

    Borough(final String ABBREV) {
        this.ABBREV = ABBREV;
    }

    public String toString() {
        return ABBREV;
    }
}
