package webshop;

public class Product {

    // --- attributes ---------------------------------------------------------

    private long id;
    private String productName;
    private int price;
    private int stock;

    // --- constructors -------------------------------------------------------

    public Product(long id, String productName, int price, int stock) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    // --- getters and setters ------------------------------------------------

    public long getId() { return id; }
    public String getProductName() { return productName; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }
}