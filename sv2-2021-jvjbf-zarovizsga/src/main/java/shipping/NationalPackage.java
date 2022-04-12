package shipping;

public class NationalPackage implements Transportable {

    // --- attributes ---------------------------------------------------------

    private int weight;
    private boolean isBreakable;

    private static final int STANDARD_PRICE = 1_000;

    // --- constructors -------------------------------------------------------

    public NationalPackage(int weight, boolean isBreakable) {
        this.weight = weight;
        this.isBreakable = isBreakable;
    }

    // --- getters and setters ------------------------------------------------

    @Override
    public int getWeight() { return weight; }

    @Override
    public boolean isBreakable() { return isBreakable; }

    // --- public methods -----------------------------------------------------

    @Override
    public int calculateShippingPrice() {
        return isBreakable ? STANDARD_PRICE * 2 : STANDARD_PRICE;
    }
}