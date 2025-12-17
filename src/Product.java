

public class Product {
    private int productId ;
    private String productName ;
    private String material ;
    private static int counter=1 ;

    //constructor
    public Product(String productName, String material) {
        this.productName = productName;
        this.material = material;
        counter++ ;
        this.productId = counter ;
    }
    


}
