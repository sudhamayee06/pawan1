package ai.glider;

/**
 * WHOLEFOODS_TYPE("Wholefoods Name", floating-point cost)
 */
public enum Wholefoods {
    
    CEREAL("Cereal", 14.00f), BANANA("Banana", 7.50f), APPLE("Apple", 10.00f);

    private final String label;
    private final float cost;

    /**
     * @param label The plain text name of the wholefoods * @param cost The wholefood's cost
     */
    Wholefoods(String label, float cost) {
        this.label = label;
        this.cost = cost;
    }

    /**
     * @return The plain text name of the wholefoods 
     */
    public String label() {
        return this.label;
    }

    /**
     * @return The wholefoods's cost 
     */
    public float cost() {
        return this.cost;
    }
}

