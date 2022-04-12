package shipping;

import java.util.*;

public class ShippingService {

    // --- attributes ---------------------------------------------------------

    private List<Transportable> packages;

    // --- constructors -------------------------------------------------------

    public ShippingService() {
        packages = new ArrayList<>();
    }

    // --- getters and setters ------------------------------------------------

    public List<Transportable> getPackages() { return packages; }

    // --- public methods -----------------------------------------------------

    public void addPackage(Transportable transportable) {
        packages.add(transportable);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return packages.stream()
            .filter(transportable -> breakable == transportable.isBreakable())
            .filter(transportable -> transportable.getWeight() >= weight)
            .toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> numberOfPackagesToCountry = new HashMap<>();
        for (Transportable transportable : packages) {
            updateNumberOfPackagesToCountry(transportable, numberOfPackagesToCountry);
        }
        return numberOfPackagesToCountry;
    }

    // --- private methods ----------------------------------------------------

    private void updateNumberOfPackagesToCountry(
        Transportable transportable, Map<String, Integer> numberOfPackagesToCountry) {

        String destinationCountry = transportable.getDestinationCountry();
        if (!numberOfPackagesToCountry.containsKey(destinationCountry)) {
            numberOfPackagesToCountry.put(destinationCountry, 0);
        }
        numberOfPackagesToCountry.put(
            destinationCountry, numberOfPackagesToCountry.get(destinationCountry) + 1);
    }

    List<Transportable> sortInternationalPackagesByDistance() {
        return new ArrayList<>(packages).stream()
            .filter(transportable -> !transportable.getDestinationCountry().equals("Hungary"))
            .sorted(Comparator.comparingInt(transportable -> ((InternationalPackage) transportable).getDistance()))
            .toList();
    }
}