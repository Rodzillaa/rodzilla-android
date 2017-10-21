package rodzillaa.github.io.rodzilla.model;

public enum LocationType {
    FAMILYDWELLING("1-2 Family Dwelling"),
    FAMILYAPT("3+ Family Apt. Building"),
    FAMILYMIXEDUSE("3+ Family Mixed Use Building"),
    COMMERCIALBUILDING("Commercial Building"),
    VACANTLOT("Vacant Lot"),
    CONSTRUCTIONSITE("Construction Site"),
    HOSPITAL("Hospital"),
    SEWER("Catch Basin/Sewer");

    private final String DESCRIPT;

    LocationType(final String DESCRIPT) {
        this.DESCRIPT = DESCRIPT;
    }

    public String toString() {
        return DESCRIPT;
    }
}
