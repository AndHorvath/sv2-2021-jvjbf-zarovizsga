package shipping;

public class InternationalPackage implements Transportable {

    // --- attributes ---------------------------------------------------------

    private int weight;
    private boolean isBreakable;
    private String destinationCountry;
    private int distance;

    private static final int STANDARD_PRICE = 1200;

    // --- constructors -------------------------------------------------------

    public InternationalPackage(int weight, boolean isBreakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.isBreakable = isBreakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    // --- getters and setters ------------------------------------------------

    @Override
    public int getWeight() { return weight; }

    @Override
    public boolean isBreakable() { return isBreakable; }

    @Override
    public String getDestinationCountry() { return destinationCountry; }

    public int getDistance() { return distance; }

    // --- public methods -----------------------------------------------------

    @Override
    public int calculateShippingPrice() {
        int baseShippingPrice = isBreakable ? STANDARD_PRICE * 2 : STANDARD_PRICE;
        return baseShippingPrice + 10 * distance;
    }
}