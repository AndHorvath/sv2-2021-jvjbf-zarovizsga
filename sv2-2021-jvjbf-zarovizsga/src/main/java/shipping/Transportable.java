package shipping;

public interface Transportable {

    int getWeight();
    boolean isBreakable();
    int calculateShippingPrice();

    default String getDestinationCountry() {
        return "Hungary";
    }
}