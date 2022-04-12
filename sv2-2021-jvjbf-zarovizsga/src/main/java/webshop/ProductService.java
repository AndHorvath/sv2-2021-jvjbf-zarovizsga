package webshop;

public class ProductService {

    // --- attributes ---------------------------------------------------------

    private ProductRepository productRepository;

    // --- constructors -------------------------------------------------------

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // --- getters and setters ------------------------------------------------

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    // --- public methods -----------------------------------------------------

    public void saleProduct(long id, int amount) {
        validateAmount(productRepository.findProductById(id), amount);
        productRepository.updateProductStock(id, amount);
    }

    // --- private methods ----------------------------------------------------

    private void validateAmount(Product product, int amount) {
        if (product.getStock() < amount) {
            throw new IllegalArgumentException("Not enough products in stock.");
        }
    }
}