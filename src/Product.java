
import java.util.ArrayList;
import java.util.List;



public class Product {
    private int productId ;
    private String productName ;
   // Map<Item,Integer> Items = new HashMap<Item,Integer >();
   List Items = new ArrayList();
    private static int counter = 1 ;

    //constructor
    
    public Product() {
    }
    
    public Product(String productName, List Items ) {
        this.productName = productName;
        this.Items = Items;
        counter++ ;
        this.productId = counter ;
    }

    public String getProductName() {
        return productName;
    }

    


}
